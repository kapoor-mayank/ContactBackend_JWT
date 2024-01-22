package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.dtos.SearchResult;
import com.example.entities.Contact;
import com.example.entities.User;

public interface ContactUserService {
	


    public User getByPhoneNumber(String phoneNumber);
    
    public void markNumberAsSpam(String phoneNumber);


    public List<Contact> searchContactsByName(String name);


    public List<Contact> searchContactsByPhoneNumber(String phoneNumber);
    
    public SearchResult depthSearch(String phoneNumber, String name, String phoneOfSearcher);

}
