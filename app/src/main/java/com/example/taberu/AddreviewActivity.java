package com.example.taberu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddreviewActivity extends AppCompatActivity {
    ImageView backImage;
    ArrayList<ImageView> templates = new ArrayList<>();
    ArrayList<String> feedback = new ArrayList<>();
    TextView nameLabel;
    EditText reviewText;
    Button sendButton;

    Retrofit retrofit;
    RestaurantApi restaurantApi;
    ReviewPost reviewPost;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Bundle bundle;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreview);

        bundle = getIntent().getExtras();

        initView();
        initRetrofit();
        initFirebaseAuth();
        initFeedback();

        setOnClick();
        setDisplayName();
    }

    private void initView() {
        backImage = findViewById(R.id.login_imageview_backbutton);
        nameLabel = findViewById(R.id.addreview_textview_irvan);
        reviewText = findViewById(R.id.addreview_edittext_masukkanreview);
        sendButton = findViewById(R.id.addreview_imageview_plus);
        templates.add(findViewById(R.id.template0));
        templates.add(findViewById(R.id.template1));
        templates.add(findViewById(R.id.template2));
        templates.add(findViewById(R.id.template3));
        templates.add(findViewById(R.id.template4));
        templates.add(findViewById(R.id.template5));
        templates.add(findViewById(R.id.template6));
        templates.add(findViewById(R.id.template7));
        templates.add(findViewById(R.id.template8));
    }

    private void initFeedback() {
        feedback.add("Bagus sekali");
        feedback.add("Aku suka Restorannya");
        feedback.add("OK");
        feedback.add("Suka banget");
        feedback.add("Underrated");
        feedback.add("Terlalu Keren");
        feedback.add("Bakal Balik Lagi Sih ini");
        feedback.add("Aku Suka");
        feedback.add("Wow");
    }

    // Intent
    private void homeIntent() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    // On Click
    private void setOnClick() {
        backImage.setOnClickListener(backIntent);
        sendButton.setOnClickListener(sendListener);


        for (int i = 0; i < templates.size(); i++) {
            int index = i;
            templates.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reviewText.setText(feedback.get(index));
                }
            });
        }
    }

    public View.OnClickListener backIntent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            homeIntent();
        }
    };

    public View.OnClickListener sendListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addReview();
        }
    };

    // Edit Text Method
    private String getReviewText() {
        return reviewText.getText().toString();
    }

    // Bundle
    private String getIDBundle() {
        return bundle.getString("RESTAURANT_ID");
    }

    // Firebase Init
    public void initFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Firebase Auth
    public void setDisplayName() {
        firebaseUser = firebaseAuth.getCurrentUser();
        name = firebaseUser.getDisplayName();
        nameLabel.setText(name);
    }

    // Retrofit Init
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://restaurant-api.dicoding.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restaurantApi = retrofit.create(RestaurantApi.class);
    }

    private void addReview() {
        reviewPost = new ReviewPost(getIDBundle(), name, getReviewText());

        Call<ReviewPost> call = restaurantApi.postReview(reviewPost);

        call.enqueue(new Callback<ReviewPost>() {
            @Override
            public void onResponse(Call<ReviewPost> call, Response<ReviewPost> response) {
                Toast.makeText(getApplicationContext(),
                        "Thank you for your feedback",
                        Toast.LENGTH_SHORT
                ).show();

                homeIntent();
            }

            @Override
            public void onFailure(Call<ReviewPost> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
    public void pindahsplashactivity(View view){
        Intent intent = new Intent(this, splashActivity.class);
        startActivity(intent);
    }
}