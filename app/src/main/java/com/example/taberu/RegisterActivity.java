package com.example.taberu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {
    Button register_daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_daftar = findViewById(R.id.register_button_daftar);
        register_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                methoddaftar();
            }
        });
    }
    public void methoddaftar(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void pindahsplashactivity(View view) {
        Intent intent = new Intent(this, splashActivity.class);
        startActivity(intent);
    }
    public void pindahloginactivity(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}