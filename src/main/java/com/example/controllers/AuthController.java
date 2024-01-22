package com.example.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.*;
import com.example.entities.JwtResponse;
import com.example.entities.LoginRequest;
import com.example.entities.SignUpRequest;
import com.example.entities.User;
import com.example.repositories.UserRepository;
import com.example.security.JwtUtils;
import com.example.services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;



  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	  //For Sign In of already present user, which would generate a JSON Web Token
	  //which is used for authorizing all subsequent requests
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail()
                         ));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
	 //for signing up a new user[registration]
    if (userRepository.findByPhoneNumber(signUpRequest.getPhoneNumber()).isPresent()) {
      return ResponseEntity
          .badRequest()
          .body("Error: Phone Number is already present in the record!");
      //checks if the user with same number already present or not
    }

    // Create new user's account
    User user = new User(signUpRequest.getName(), signUpRequest.getPhoneNumber(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));
    
    userRepository.save(user);

    return ResponseEntity.ok("User registered successfully!");
  }
}
