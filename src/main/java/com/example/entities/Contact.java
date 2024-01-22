package com.example.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String phoneNumber;
	private Boolean isSpam;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Long id, String name, String phoneNumber, Boolean isSpam, User user) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.isSpam = isSpam;
		this.user = user;
	}

	public Long getId() {
		return id;
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

	public Boolean getIsSpam() {
		return isSpam;
	}

	public void setIsSpam(Boolean isSpam) {
		this.isSpam = isSpam;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", isSpam=" + isSpam
				+ ", user=" + user + "]";
	}

	public void setUser(User user) {
		this.user = user;
	}
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
