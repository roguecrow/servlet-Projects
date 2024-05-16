package com.samweb.manager;

import java.util.ArrayList;

import com.samweb.model.UserInfo;

public class DataSaver {

	ArrayList<UserInfo> userRegister= new ArrayList<>();
    public void addUser(String fullName,String email,String password,String profession,int age,long phoneNumber) {
    	UserInfo userData =new UserInfo(fullName,email,password,profession,age,phoneNumber);
    	userRegister.add(userData);
        System.out.println(userRegister);
         
    }
    public ArrayList<UserInfo> getUserRegister() {
        return userRegister;
    }

}
