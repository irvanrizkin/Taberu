package com.example.taberu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        TextView titleLabel;
        ImageView backImage;
        titleLabel = findViewById(R.id.restaurantdetail_textview_Yoshinoya);
        backImage = findViewById(R.id.restaurantdetail_imageview_backbutton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            titleLabel.setText(bundle.getString("RESTAURANT_ID"));
        }

        backImage.setOnClickListener(backIntent);
    }

    public View.OnClickListener backIntent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
    };
}