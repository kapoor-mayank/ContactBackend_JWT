package com.example.entities;


import java.util.Set;

import jakarta.validation.constraints.*;

public class SignUpRequest {


  @NotBlank
  @Size(max = 13)
  private String phoneNumber;

  private String name;
  public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@NotBlank
  @Size(min = 6, max = 40)
  private String password;

  @Email
  private String email;

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

public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
