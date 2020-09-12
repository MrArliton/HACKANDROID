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

public class LastRegular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_regular);
        TextView postText = findViewById(R.id.postText);
        postText.setText(getIntent().getExtras().get("post").toString());
        ImageView image = findViewById(R.id.imagePE2);
        ProgressBar pr = findViewById(R.id.progress2);
        pr.setProgress(100/4);
        TextView name = findViewById(R.id.nameP2);
        name.setText(getIntent().getExtras().get("name").toString());
        TextView text = findViewById(R.id.monetT2);
        text.setText("Собрано "+Integer.toString(Integer.parseInt(getIntent().getExtras().get("money").toString())/4) +" из "+Integer.toString(Integer.parseInt(getIntent().getExtras().get("money").toString())));
        TextView t = findViewById(R.id.Reg1);
        Button m = findViewById(R.id.Next);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent post = new Intent(LastRegular.this,Final.class);
                post.putExtra("name", getIntent().getExtras().get("name").toString());
                post.putExtra("money", getIntent().getExtras().get("money").toString());
                post.putExtra("chel", getIntent().getExtras().get("chel").toString());
                post.putExtra("desc", getIntent().getExtras().get("desc").toString());
                post.putExtra("post", getIntent().getExtras().get("post").toString());
                post.putExtra("date",getIntent().getExtras().get("date").toString());
                post.putExtra("check",(boolean)getIntent().getExtras().get("check"));
                post.putExtra("uri", (Uri)getIntent().getExtras().get("uri"));
                startActivity(post);
            }
        });
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