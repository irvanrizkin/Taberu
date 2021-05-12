package com.example.taberu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    Button login_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_login = findViewById(R.id.login_button_login);
        login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                methodlogin();
            }
        });
    }
    public void methodlogin(){
        Intent intent = new Intent(this, AddreviewActivity.class);
        startActivity(intent);
    }

    public void pindahsplashactivity(View view) {
        Intent intent = new Intent(this, splashActivity.class);
        startActivity(intent);
    }

    public void pindahregisteractivity(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}