package com.example.taberu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RestaurantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
    }
    public void pindahsplashactivity(View view) {
        Intent intent = new Intent(this, splashActivity.class);
        startActivity(intent);
    }
}