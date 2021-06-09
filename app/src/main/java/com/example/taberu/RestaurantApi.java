package com.example.taberu;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestaurantApi {
  @GET("/list")
  Call<RestaurantResponse> getRestaurants();

  @GET("/detail/{id}")
  Call<RestaurantSingleResponse> getSingleRestaurant(@Path("id") String id);

  @POST("/review")
  Call<ReviewPost> postReview(@Body ReviewPost reviewPost);
}
