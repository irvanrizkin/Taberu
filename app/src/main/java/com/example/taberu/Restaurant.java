package com.example.taberu;

public class Restaurant {
  private String id, name, description, pictureId, city;
  private float rating;

  public Restaurant() {
  }

  public Restaurant(String id, String name, String description, String pictureId, String city,
                    float rating) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.pictureId = pictureId;
    this.city = city;
    this.rating = rating;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getPictureId() {
    return pictureId;
  }

  public void setPictureId(String pictureId) {
    this.pictureId = pictureId;
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
}
