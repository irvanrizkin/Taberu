package com.example.taberu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    ImageView optionsImage;
    TextView userLabel;
    PopupMenu popupMenu;
    RecyclerView restaurantsRecycler;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    RestaurantAdapter.RecyclerListener recyclerListener;

    ArrayList<Restaurant> restaurantList;
    Retrofit retrofit;
    RestaurantApi restaurantApi;
    RestaurantResponse restaurantResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initFirebaseAuth();
        initRetrovit();
        initRecycler();

        setOnClick();
        setDisplayName();
        getRestaurant();
    }

    // View By ID
    private void initView() {
        optionsImage = findViewById(R.id.home_imageview_morebutton);
        userLabel = findViewById(R.id.home_texttview_namautama);
    }

    // On Click
    private void setOnClick() {
        optionsImage.setOnClickListener(optionsListener);
    }

    private View.OnClickListener optionsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkLogin()) {
                showProfilePopUp(v);
            } else {
                showLoginPopUp(v);
            }
        }
    };

    // Firebase Init
    public void initFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Firebase Auth
    public void setDisplayName() {
        String name;
        if (checkLogin()) {
            firebaseUser = firebaseAuth.getCurrentUser();
            name = firebaseUser.getDisplayName();
        } else {
            name = "Guest";
        }
        userLabel.setText("Halo, " + name + "!");
    }

    // Firebase Login Check
    public Boolean checkLogin() {
        if (firebaseAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }

    // Pop Up
    public void showLoginPopUp(View v) {
        popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_login) {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    return true;
                }
                return false;
            }
        });
        popupMenu.inflate(R.menu.popup_login);
        popupMenu.show();
    }

    public void showProfilePopUp(View v) {
        popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_profile) {
                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                    return true;
                }
                return false;
            }
        });
        popupMenu.inflate(R.menu.popup_profile);
        popupMenu.show();
    }

    // Retrofit Init
    private void initRetrovit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://restaurant-api.dicoding.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restaurantApi = retrofit.create(RestaurantApi.class);
    }

    private void getRestaurant() {
        Call<RestaurantResponse> call = restaurantApi.getRestaurants();

        call.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                restaurantResponse = response.body();
                restaurantList = new ArrayList<>(Arrays.asList(restaurantResponse.getRestaurants()));

                showRecycler(restaurantList);
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    //Recycler View
    private void initRecycler() {
        restaurantsRecycler = findViewById(R.id.home_recyclerview_recyclerview);
        restaurantsRecycler.setHasFixedSize(true);
    }

    private void showRecycler(ArrayList<Restaurant> restaurants) {
        setRecyclerOnClick();
        restaurantsRecycler.setLayoutManager(new LinearLayoutManager(
                getApplicationContext()
        ));
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(restaurants, recyclerListener);
        restaurantsRecycler.setAdapter(restaurantAdapter);
    }

    private void setRecyclerOnClick() {
        recyclerListener = new RestaurantAdapter.RecyclerListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), RestaurantDetailActivity.class);
                intent.putExtra("RESTAURANT_ID", restaurantList.get(position).getId());
                startActivity(intent);
            }
        };
    }
}