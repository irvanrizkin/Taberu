package com.example.taberu;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantApi {
  @GET("/list")
  Call<RestaurantResponse> getRestaurants();
}
