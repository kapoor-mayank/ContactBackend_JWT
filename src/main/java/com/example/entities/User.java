package com.example.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	@Column(nullable = false, unique = true)
	private String phoneNumber;
	private String email;
	private String password;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String name, String phoneNumber, String email, String password, List<Contact> contacts) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.contacts = contacts;
	}

	public User(String name, String phoneNumber, String email, String password) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}
	public Long getId() {
		return id;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contact> contacts = new ArrayList<>();

	
}
