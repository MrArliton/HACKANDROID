package com.example.vezdekodn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class regular_last extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_last);
        TextView nameP = findViewById(R.id.nameP);
        nameP.setText(getIntent().getExtras().get("name").toString());
        ImageView image = findViewById(R.id.imagePE);
        Button exit = findViewById(R.id.exit);
        final EditText pos = findViewById(R.id.textPE);
        Button publish = findViewById(R.id.publish);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exit = new Intent(regular_last.this,MainActivity.class);
                startActivity(exit);
            }
        });
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent post = new Intent(regular_last.this,LastRegular.class);
                if(pos.getText().toString().length()>5) {
                    post.putExtra("name", getIntent().getExtras().get("name").toString());
                    post.putExtra("money", getIntent().getExtras().get("money").toString());
                    post.putExtra("chel", getIntent().getExtras().get("chel").toString());
                    post.putExtra("desc", getIntent().getExtras().get("desc").toString());
                    post.putExtra("post", pos.getText().toString());
                    post.putExtra("date",getIntent().getExtras().get("date").toString());
                    post.putExtra("check",(boolean)getIntent().getExtras().get("check"));
                    post.putExtra("uri", (Uri)getIntent().getExtras().get("uri"));
                    startActivity(post);
                }
            }
        });
        TextView t = findViewById(R.id.Reg);

        if((boolean)getIntent().getExtras().get("check")) {
            // text.setText("Собрано в сентябре " + Integer.toString(Integer.parseInt(getIntent().getExtras().get("money").toString()) / 4) + ", Р");
            if(!getIntent().getExtras().get("date").toString().equalsIgnoreCase("-")) {
                t.setText("Матвей Правосудов - Помощь нужна до " + getIntent().getExtras().get("date").toString());
            }
        }
        try {
            final InputStream imageStream = getContentResolver().openInputStream((Uri) getIntent().getExtras().get("uri"));
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            image.setImageBitmap(selectedImage);
        }catch(FileNotFoundException e){

        }
    }
}