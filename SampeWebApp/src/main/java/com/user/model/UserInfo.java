package com.user.model;

public class UserInfo {

	String fullName;
	String email;
	String password;
	String profession;
	int age;

	
	public UserInfo(String fullName, String email, String password, String profession, int age) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.profession = profession;
		this.age = age;
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
