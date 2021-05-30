package com.example.taberu;

public class User {
  String email, fullName, nickName, phoneNumber;

  public User() {
  }

  public User(String email, String fullName, String nickName, String phoneNumber) {
    this.email = email;
    this.fullName = fullName;
    this.nickName = nickName;
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
