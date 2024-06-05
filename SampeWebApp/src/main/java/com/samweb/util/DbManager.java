package com.samweb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.samweb.manager.DataSaver;
import com.samweb.model.UserInfo;



public class DbManager {
	DataSaver saver = new DataSaver();

	DbConnectionUtil connection;
     Connection connect;
     
     public DbManager() throws ClassNotFoundException, SQLException {
         connection = new DbConnectionUtil();
         connect = connection.getConnection();
     }

	 public int createUser(UserInfo userData) throws SQLException {
		String addUser = "INSERT INTO user_details (full_name, email, password) VALUES (?, ?, ?)";
	    PreparedStatement prepareStatement = connect.prepareStatement(addUser);
	    prepareStatement.setString(1 , userData.getFullName());
	    prepareStatement.setString(2 , userData.getEmail());
	    prepareStatement.setString(3 , userData.getPassword());
	    
	    int rows = prepareStatement.executeUpdate();
	    System.out.println("affected row :" + rows);
		return rows;
	}
	 
	 public ArrayList<UserInfo> viewUserDetails() throws SQLException {
			ArrayList<UserInfo> userRegister= new ArrayList<>();
		 String viewUser = "Select user_id,full_name,email,password from user_details where is_active = ?";
		 PreparedStatement prepareStatement = connect.prepareStatement(viewUser);
		 prepareStatement.setBoolean(1,true);
			ResultSet rows = prepareStatement.executeQuery();
	        while (rows.next()) {
	        	int id = rows.getInt("user_id");
	        	String firstName = rows.getString("full_name");
	        	String email = rows.getString("email");
	        	String password = rows.getString("password");
	        	System.out.println(firstName + email + password);
	        	UserInfo userData =new UserInfo(id,firstName,email,password);
	        	userRegister.add(userData);
	        }
	        return userRegister;
	 }
	 
	 public void deleteUserDetails(int id) throws SQLException {
		    String deleteUser = "UPDATE user_details SET is_active = ? WHERE user_id = ?";
		    PreparedStatement prepareStatement = connect.prepareStatement(deleteUser);
		    prepareStatement.setBoolean(1, false);
		    prepareStatement.setInt(2, id);
		    int rowsAffected = prepareStatement.executeUpdate();
		    System.out.println("Affected rows =" + rowsAffected);
		}

		public void updateUserDetails(int id ,String name,String email) throws SQLException {
		    String updateUser = "UPDATE user_details SET full_name = ?, email = ? WHERE user_id = ?";
		    PreparedStatement prepareStatement = connect.prepareStatement(updateUser);
		    prepareStatement.setString(1, name);
		    prepareStatement.setString(2, email);
		    prepareStatement.setInt(3, id);
		    int rowsAffected = prepareStatement.executeUpdate();
		    System.out.println("Affected rows =" + rowsAffected);
		}
		
		public ArrayList<UserInfo> findUserName(String name) throws SQLException {
			ArrayList<UserInfo> searchedUser= new ArrayList<>();
		    String searchUser = "SELECT * FROM user_details WHERE full_name LIKE ?  and is_active= ?";
		    PreparedStatement prepareStatement = connect.prepareStatement(searchUser);
		    prepareStatement.setString(1, name+"%");
		    prepareStatement.setBoolean(2, true);
			ResultSet likeValue = prepareStatement.executeQuery();
	        while (likeValue.next()) {
	        	int id = likeValue.getInt("user_id");
	        	String firstName = likeValue.getString("full_name");
	        	String email = likeValue.getString("email");
	        	String password = likeValue.getString("password");
	        	System.out.println(firstName+" " + email+" " + password);
	        	UserInfo userData =new UserInfo(id,firstName,email,password);
	        	searchedUser.add(userData);
	        }
			return searchedUser;
		}
}
