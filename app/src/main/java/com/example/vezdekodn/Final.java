package com.example.vezdekodn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Final extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        TextView s1 = findViewById(R.id.r16);
        TextView s2 = findViewById(R.id.final1);
        TextView s3 = findViewById(R.id.nameP3);
        Button exit = findViewById(R.id.EXITE);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Final.this,MainActivity.class);
                startActivity(back);
            }
        });
        s2.setText(getIntent().getExtras().get("post").toString());
        s3.setText(getIntent().getExtras().get("name").toString());
        ProgressBar p = findViewById(R.id.progress3);
        TextView s = findViewById(R.id.S2);
        s.setText("Собрано "+Integer.toString(Integer.parseInt(getIntent().getExtras().get("money").toString())/4) +" из "+Integer.toString(Integer.parseInt(getIntent().getExtras().get("money").toString())));
        p.setProgress(100/4);
        ImageView image = findViewById(R.id.ima);
        s1.setText("Помощь нужна каждый месяц");
        if((boolean)getIntent().getExtras().get("check")) {
            // text.setText("Собрано в сентябре " + Integer.toString(Integer.parseInt(getIntent().getExtras().get("money").toString()) / 4) + ", Р");
            if(!getIntent().getExtras().get("date").toString().equalsIgnoreCase("-")) {
                s1.setText("Матвей Правосудов - Помощь нужна до " + getIntent().getExtras().get("date").toString());
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