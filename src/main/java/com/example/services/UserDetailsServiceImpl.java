package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.User;
import com.example.repositories.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;
  //FOR JWT
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
    User user = userRepository.findByPhoneNumber(phoneNumber)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with phoneNumber: " + phoneNumber));

    return UserDetailsImpl.build(user);
  }

}
