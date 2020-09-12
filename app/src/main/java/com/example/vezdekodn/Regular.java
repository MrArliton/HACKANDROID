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

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Regular extends AppCompatActivity {
    ImageView img;
    Uri imageUri = Uri.EMPTY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular);
        Button back = findViewById(R.id.backr);

        final EditText name = findViewById(R.id.nameCbor);
        final EditText money = findViewById(R.id.Money);
        final EditText chel = findViewById(R.id.Chel);
        final ImageView im = findViewById(R.id.imageV);
        final TextView r = findViewById(R.id.por);
        if((boolean)getIntent().getExtras().get("check")){
            im.setImageDrawable(getDrawable(R.drawable.se12));
            r.setText("Сумма, Р");
        }
        final EditText desc = findViewById(R.id.desc);
        final Button getImg = findViewById(R.id.getImg);
        final Button dallee = findViewById(R.id.createOpr3);

        if((boolean)getIntent().getExtras().get("back")){
            name.setText(getIntent().getExtras().get("name").toString());
            money.setText(getIntent().getExtras().get("money").toString());
            chel.setText(getIntent().getExtras().get("chel").toString());
            desc.setText(getIntent().getExtras().get("desc").toString());
            imageUri = (Uri)getIntent().getExtras().get("uri");

        }
        img = findViewById(R.id.image);
        try {
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            img.setImageBitmap(selectedImage);
        }catch(FileNotFoundException e){}
        dallee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((name.getText().toString().length() > 1) & (money.getText().toString().length() > 1) & (chel.getText().toString().length() > 1)) {
                    Intent next = new Intent(Regular.this, regular_next.class);
                    next.putExtra("name", name.getText().toString());
                    next.putExtra("money", money.getText().toString());
                    next.putExtra("chel", chel.getText().toString());
                    next.putExtra("desc", desc.getText().toString());
                    next.putExtra("check",(boolean)getIntent().getExtras().get("check"));
                    next.putExtra("uri", imageUri);
                    startActivity(next);
                }
            }
        });
        getImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                //Тип получаемых объектов - image:
                photoPickerIntent.setType("image/*");
                //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
                startActivityForResult(photoPickerIntent, 1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Regular.this, Activity_two.class);
                startActivity(back);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    try {

                        //Получаем URI изображения, преобразуем его в Bitmap
                        //объект и отображаем в элементе ImageView нашего интерфейса:
                        imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        img.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}