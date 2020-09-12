package com.example.vezdekodn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_two extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Button reg = findViewById(R.id.regul);
        Button back = findViewById(R.id.back);
        Button check = findViewById(R.id.Checl);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(Activity_two.this,Regular.class);
                next.putExtra("back",false);
                next.putExtra("check",true);
                startActivity(next);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Activity_two.this,MainActivity.class);
                startActivity(back);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(Activity_two.this,Regular.class);
                next.putExtra("back",false);
                next.putExtra("check",false);
                startActivity(next);
            }
        });
    }
}