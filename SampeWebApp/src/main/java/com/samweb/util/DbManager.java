package com.samweb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.samweb.model.UserInfo;



public class DbManager {

	DbConnectionUtil connection;
     Connection connect;
     
     public DbManager() throws ClassNotFoundException, SQLException {
         connection = new DbConnectionUtil();
         connect = connection.getConnection();
     }

	 public int insertUserDetails(UserInfo userData) throws SQLException {
		String addUser = "INSERT INTO user_details (full_name, email, password) VALUES (?, ?, ?)";
	    PreparedStatement prepareStatement = connect.prepareStatement(addUser);
	    prepareStatement.setString(1 , userData.getFullName());
	    prepareStatement.setString(2 , userData.getEmail());
	    prepareStatement.setString(3 , userData.getPassword());
	    
	    int rows = prepareStatement.executeUpdate();
	    System.out.println("affected row :" + rows);
		return rows;
	}
}
