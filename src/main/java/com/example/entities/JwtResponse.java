package com.example.entities;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String phoneNumber;
  private String email;

  public JwtResponse(String accessToken, Long id, String phoneNumber, String email) {
    this.token = accessToken;
    this.id = id;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

}