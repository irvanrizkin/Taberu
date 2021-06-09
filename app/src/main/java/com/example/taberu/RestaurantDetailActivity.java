package com.example.taberu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantDetailActivity extends AppCompatActivity {
    TextView titleLabel, descriptionLabel, cityLabel, ratingLabel;
    ImageView backImage, addReviewImage;
    RecyclerView reviewsRecycler;

    Retrofit retrofit;
    RestaurantApi restaurantApi;
    RestaurantSingleResponse restaurantSingleResponse;
    RestaurantSingle restaurantSingle;
    FirebaseAuth firebaseAuth;

    Bundle bundle;
    ArrayList<Review> reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        bundle = getIntent().getExtras();

        initView();
        initRetrofit();
        initRecycler();
        initFirebaseAuth();

        setOnClick();
        getRestaurantDetail();
    }

    private void initView() {
        titleLabel = findViewById(R.id.restaurantdetail_textview_Yoshinoya);
        descriptionLabel = findViewById(R.id.restaurantdetail_textview_longtext);
        cityLabel = findViewById(R.id.restaurantdetail_textview_Jakarta);
        ratingLabel = findViewById(R.id.restaurantdetail_textview_star);
        addReviewImage = findViewById(R.id.restaurantdetail_imageview_plus);
        backImage = findViewById(R.id.login_imageview_backbutton);
        reviewsRecycler = findViewById(R.id.restaurantdetail_recyclerview_review);
    }

    // On Click
    private void setOnClick() {
        backImage.setOnClickListener(backIntent);
        addReviewImage.setOnClickListener(addReviewIntent);
    }

    public View.OnClickListener backIntent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
    };

    public View.OnClickListener addReviewIntent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkLogin()) {
                Intent intent = new Intent(getApplicationContext(), AddreviewActivity.class);
                intent.putExtra("RESTAURANT_ID", getStringBundle(bundle));
                startActivity(intent);
            } else {
                AlertDialog.Builder loginBuilder = loginDialogBuild(v);
                loginDialogShow(loginBuilder);
            }
        }
    };

    // Bundle
    private String getStringBundle(Bundle bundle) {
        return bundle.getString("RESTAURANT_ID");
    }

    // Firebase Init
    public void initFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Firebase Login Check
    public Boolean checkLogin() {
        if (firebaseAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }

    // Retrofit Init
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://restaurant-api.dicoding.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restaurantApi = retrofit.create(RestaurantApi.class);
    }

    private void getRestaurantDetail() {
        Call<RestaurantSingleResponse> call = restaurantApi.getSingleRestaurant(
                getStringBundle(bundle)
        );

        call.enqueue(new Callback<RestaurantSingleResponse>() {
            @Override
            public void onResponse(Call<RestaurantSingleResponse> call,
                                   Response<RestaurantSingleResponse> response) {
                restaurantSingleResponse = response.body();
                restaurantSingle = restaurantSingleResponse.getRestaurant();
                reviewList = new ArrayList<>(Arrays.asList(restaurantSingle.getCustomerReviews()));

                setRestaurantDetail(restaurantSingle);
                showRecycler(reviewList);
            }

            @Override
            public void onFailure(Call<RestaurantSingleResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    // Set Detail
    private void setRestaurantDetail(RestaurantSingle restaurantSingle) {
        titleLabel.setText(restaurantSingle.getName());
        descriptionLabel.setText(restaurantSingle.getDescription());
        cityLabel.setText(restaurantSingle.getCity());
        ratingLabel.setText(restaurantSingle.getRating() + " Star");
    }

    // Recycler View
    private void initRecycler() {
        reviewsRecycler = findViewById(R.id.restaurantdetail_recyclerview_review);
        reviewsRecycler.setHasFixedSize(true);
    }

    private void showRecycler(ArrayList<Review> reviews) {
        reviewsRecycler.setLayoutManager(new LinearLayoutManager(
                getApplicationContext()
        ));
        ReviewAdapter reviewAdapter = new ReviewAdapter(reviews);
        reviewsRecycler.setAdapter(reviewAdapter);
    }

    // Dialog Box
    private AlertDialog.Builder loginDialogBuild(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        builder.setTitle("Anda belum login");
        builder.setMessage("Harap login sebelum menambahkan review");

        builder.setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                dialog.dismiss();
                startActivity(intent);
            }
        });

        builder.setNegativeButton("CANCEL", (dialog, which) -> dialog.dismiss());

        return builder;
    }

    private void loginDialogShow(AlertDialog.Builder builder) {
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}