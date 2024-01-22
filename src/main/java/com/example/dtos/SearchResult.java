package com.example.dtos;




public class SearchResult {
	private String name;
	private String phoneNumber;
	private Boolean isSpam;
	private String email;
	
	//DTO made for the purpose of sending data over as a result of search by number
	//As it included more fields that any of the Entity/Model classes already present
	
	public SearchResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchResult(String name, String phoneNumber, Boolean isSpam, String email) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.isSpam = isSpam;
		this.email = email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
