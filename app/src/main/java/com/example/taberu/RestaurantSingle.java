package com.example.taberu;

public class RestaurantSingle {
  private String name, description, city;
  private float rating;
  private Review[] customerReviews;

  public RestaurantSingle() {
  }

  public RestaurantSingle(String name, String description, String city, float rating,
                          Review[] customerReviews) {
    this.name = name;
    this.description = description;
    this.city = city;
    this.rating = rating;
    this.customerReviews = customerReviews;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public Review[] getCustomerReviews() {
    return customerReviews;
  }

  public void setCustomerReviews(Review[] customerReviews) {
    this.customerReviews = customerReviews;
  }
}
