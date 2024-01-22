package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.dtos.SearchResult;
import com.example.entities.Contact;
import com.example.entities.User;
import com.example.repositories.ContactRepository;
import com.example.repositories.UserRepository;
@Service
public class ContactUserServiceImpl implements ContactUserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private ContactRepository contactRepository;
	


	@Override
	public void markNumberAsSpam(String phoneNumber) {
		// TODO Auto-generated method stub
		List<Contact> contacts = contactRepository.findByPhoneNumber(phoneNumber);	//List would contain all occurances of the number, as it can be with different names setting them all as spam
		if (contacts != null) {
			contacts.forEach(contact -> {contact.setIsSpam(true);});
			contactRepository.saveAll(contacts);
		}
		else {
			Contact contact = new Contact();
			contact.setPhoneNumber(phoneNumber);
			contact.setIsSpam(true);
			contactRepository.save(contact);	//If number not already present, inserted a new entry and marked as spam
		}
	}

	@Override
	public List<Contact> searchContactsByName(String name) {
		// TODO Auto-generated method stub
		return contactRepository.findByNameContainingIgnoreCase(name);	//returns the most matching first, then all other including search query
	}

	@Override
	public List<Contact> searchContactsByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		List<Contact> list = new ArrayList<Contact>();
		Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			Contact contact = new Contact();
			contact.setName(user.getName());
			contact.setPhoneNumber(user.getPhoneNumber());
			list.add(contact);
			return list;		//If Number is of registered user only 1 record returned
		}
		else {
			return contactRepository.findByPhoneNumberIsLike(phoneNumber);		//else all records matching query string returned
		}
	}

	@Override
	public SearchResult depthSearch(String phoneNumber, String name, String phoneOfSearcher) {
		//along with the number searched - upon clicking of one of the search results
		//name and phone number of searcher also passed along[will be maintained in FE session storage]
		//as the requirement was that e-mail is to be displayed only if searcher is a contact of searched
		Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
		List<Contact> contactOptional = contactRepository.findByPhoneNumber(phoneNumber);
		System.out.println(userOptional.isPresent());
		if (userOptional.isPresent()) {
			System.out.println("in if");
			User user = userOptional.get();
			Long userId = user.getId();
			Contact getContact = contactRepository.findContactsByNameAndPhoneNumberAndUserId(name, phoneOfSearcher, userId);
			System.out.println(userId+" "+ name+" "+ phoneOfSearcher+" "+ getContact);
			SearchResult result = new SearchResult();
			result.setName(user.getName());
			result.setPhoneNumber(user.getPhoneNumber());
			if (contactOptional.isEmpty()) {		//checking if searched number is spam or not, which is in other table, therefore check applied only if present in contacts table then get its value and pass ahead or spam = false
				
				result.setIsSpam(false);
				
				
			}
			else {
				result.setIsSpam(contactOptional.get(0).getIsSpam());
				
			}
			if (getContact != null) {	//setting email in DTO only if searcher is in contacts of searched
				
				result.setEmail(user.getEmail());
				
			}
			else {
				
				result.setEmail(null);
				
			}
			return result;
		}
		else {		//if searched number is not of registered user checking for getting email is of no use, therefore written in else
			
			Contact contact = contactOptional.get(0);
			SearchResult result = new SearchResult();
			result.setName(contact.getName());
			result.setPhoneNumber(contact.getPhoneNumber());
			result.setIsSpam(contact.getIsSpam());
			result.setEmail(null);
			return result;
		}
	}

	@Override
	public User getByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			return user;
		}
		else {
			return null;
		}
	}

}
