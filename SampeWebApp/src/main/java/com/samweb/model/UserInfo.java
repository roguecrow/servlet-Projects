package com.samweb.model;

public class UserInfo {

	String fullName;
	String email;
	String password;
	String profession;
	int age;
	long phoneNumber;


	public UserInfo(String fullName, String email, String password, String profession, int age, long phoneNumber) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.profession = profession;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
