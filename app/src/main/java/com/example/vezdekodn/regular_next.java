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
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class regular_next extends AppCompatActivity {
    Uri imageUri = Uri.EMPTY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_next);
        final TextView name = findViewById(R.id.nameCborN);
        name.setText(getIntent().getExtras().get("name").toString());
        final TextView money = findViewById(R.id.MoneyN);
        money.setText(getIntent().getExtras().get("money").toString());
        final TextView chel = findViewById(R.id.ChelN);
        chel.setText(getIntent().getExtras().get("chel").toString());
        final TextView desc = findViewById(R.id.descN);
        desc.setText(getIntent().getExtras().get("desc").toString());
        final ImageView image = findViewById(R.id.imageN);
        final ImageView im = findViewById(R.id.imageCN);
        final ImageView im1 = findViewById(R.id.imageCN1);
        Button back = findViewById(R.id.backr2);
        imageUri = (Uri)getIntent().getExtras().get("uri");
        Button create = findViewById(R.id.createOpr2);
        if((boolean)getIntent().getExtras().get("check")){
            im.setImageDrawable(getDrawable(R.drawable.sr12));
            im1.setImageDrawable(getDrawable(R.drawable.d1));
            create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent post = new Intent(regular_next.this,check.class);
                    post.putExtra("name", name.getText().toString());
                    post.putExtra("money", money.getText().toString());
                    post.putExtra("chel", chel.getText().toString());
                    post.putExtra("desc", desc.getText().toString());
                    post.putExtra("uri", imageUri);
                    startActivity(post);
                }
            });
        }else{
            create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent post = new Intent(regular_next.this,regular_last.class);
                    post.putExtra("name", name.getText().toString());
                    post.putExtra("money", money.getText().toString());
                    post.putExtra("chel", chel.getText().toString());
                    post.putExtra("desc", desc.getText().toString());
                    post.putExtra("check",false);
                    post.putExtra("uri", imageUri);
                    startActivity(post);
                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(regular_next.this,Regular.class);
                back.putExtra("name", name.getText().toString());
                back.putExtra("money", money.getText().toString());
                back.putExtra("chel", chel.getText().toString());
                back.putExtra("desc", desc.getText().toString());
                back.putExtra("check",(boolean)getIntent().getExtras().get("check"));
                back.putExtra("back",true);
                back.putExtra("uri", imageUri);
                startActivity(back);
            }
        });
        try {
            final InputStream imageStream = getContentResolver().openInputStream((Uri) getIntent().getExtras().get("uri"));
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            image.setImageBitmap(selectedImage);
        }catch(FileNotFoundException e){

        }
    }
}