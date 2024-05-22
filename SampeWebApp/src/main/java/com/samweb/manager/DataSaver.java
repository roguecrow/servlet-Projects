package com.samweb.manager;

import java.util.ArrayList;

import com.samweb.model.UserInfo;

public class DataSaver {

	ArrayList<UserInfo> userRegister= new ArrayList<>();
    public void addUser(String fullName,String email,String password) {
    	UserInfo userData =new UserInfo(0, fullName,email,password);
    	userRegister.add(userData);
   	 System.out.println( "userReg from addUser:" +userRegister);        
    }
    public ArrayList<UserInfo> getUserRegister() {
    	 System.out.println( "userReg from getUserRegister:" +userRegister);        
    	 return userRegister;
    }
}
