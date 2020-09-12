package com.example.vezdekodn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class check extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        final CheckBox c1 = findViewById(R.id.ch1);
        final CheckBox c2 = findViewById(R.id.ch2);
        final Button nex = findViewById(R.id.createCbor5);
        final TextView t = findViewById(R.id.getData);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(check.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Calendar cal=Calendar.getInstance();
                SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
                String month_name = month_date.format(cal.getTime());
                String date = day +" "+ month_name;
                t.setText(date);
            }
        };

        Button back = findViewById(R.id.back5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent post = new Intent(check.this,regular_next.class);
                post.putExtra("name", getIntent().getExtras().get("name").toString());
                post.putExtra("money", getIntent().getExtras().get("money").toString());
                post.putExtra("chel", getIntent().getExtras().get("chel").toString());
                post.putExtra("desc", getIntent().getExtras().get("desc").toString());
                post.putExtra("check",true);
                post.putExtra("back",true);
                post.putExtra("uri", (Uri)getIntent().getExtras().get("uri"));
                startActivity(post);
            }
        });
        nex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(c2.isChecked()){
                    if(!t.getText().toString().trim().equalsIgnoreCase("Выберите дату")){
                        Intent post = new Intent(check.this,regular_last.class);
                        post.putExtra("name", getIntent().getExtras().get("name").toString());
                        post.putExtra("money", getIntent().getExtras().get("money").toString());
                        post.putExtra("chel", getIntent().getExtras().get("chel").toString());
                        post.putExtra("desc", getIntent().getExtras().get("desc").toString());
                        post.putExtra("check",true);
                        post.putExtra("date",t.getText());
                        post.putExtra("uri", (Uri)getIntent().getExtras().get("uri"));
                        startActivity(post);
                    }
                }else{
                    Intent post = new Intent(check.this,regular_last.class);
                    post.putExtra("name", getIntent().getExtras().get("name").toString());
                    post.putExtra("money", getIntent().getExtras().get("money").toString());
                    post.putExtra("chel", getIntent().getExtras().get("chel").toString());
                    post.putExtra("desc", getIntent().getExtras().get("desc").toString());
                    post.putExtra("check",true);
                    post.putExtra("date","-");
                    post.putExtra("uri", (Uri)getIntent().getExtras().get("uri"));
                    startActivity(post);
                }
            }
        });
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                c2.setChecked(!b);
                if(t.getText().length()<3){
                    nex.setAlpha(0.7f);
                }
            }
        });
        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                c1.setChecked(!b);
                if(t.getText().toString().trim().equalsIgnoreCase("Выберите дату")){
                    nex.setAlpha(0.7f);
                }
            }
        });
    }
}