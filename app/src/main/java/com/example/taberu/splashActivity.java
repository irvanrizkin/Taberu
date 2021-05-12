package com.example.taberu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class splashActivity extends AppCompatActivity {
    Button buttonmulai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        buttonmulai = findViewById(R.id.splash_button_mulai);
        buttonmulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                methodbuttonmulai();
            }
        });
    }
    public void methodbuttonmulai(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}