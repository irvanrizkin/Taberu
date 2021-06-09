package com.example.taberu;

public class Review {
  String name, review, date;

  public Review() {
  }

  public Review(String name, String review, String date) {
    this.name = name;
    this.review = review;
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
