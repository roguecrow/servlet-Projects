package com.manage.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.manage.encrypt.PasswordEncryption;
import com.manage.model.ExamDetails;
import com.manage.model.UserDetails;




public class DbManager {
	ConnectionUtil connection;
    Connection connect;
    
    public DbManager() throws ClassNotFoundException, SQLException {
        connection = new ConnectionUtil();
        connect = connection.getConnection();
    }
    
	 public int userRegisteration(String fullName, String email, String password) throws SQLException {
		String addUser = "INSERT INTO user_credentials (full_name, email, password) VALUES (?, ?, ?)";
	    PreparedStatement prepareStatement = connect.prepareStatement(addUser);
	    prepareStatement.setString(1 , fullName);
	    prepareStatement.setString(2 , email);
	    prepareStatement.setString(3 , password);
	    
	    int rows = prepareStatement.executeUpdate();
	    System.out.println("affected row :" + rows);
		return rows;
	}
	 
	 public int createExam(String examName, String description, Date examDate, Timestamp applicationStartDate, Timestamp applicationEndDate) throws SQLException {
		    String addExam = "INSERT INTO exams (exam_name, description, exam_date, application_start_date, application_end_date) VALUES (?, ?, ?, ?, ?)";
		    PreparedStatement prepareStatement = connect.prepareStatement(addExam);
		    prepareStatement.setString(1 , examName);
		    prepareStatement.setString(2 , description);
		    prepareStatement.setDate(3 , new java.sql.Date(examDate.getTime()));
		    prepareStatement.setTimestamp(4 , applicationStartDate);
		    prepareStatement.setTimestamp(5 , applicationEndDate);
		    
		    int rows = prepareStatement.executeUpdate();
		    System.out.println("affected row :" + rows);
		    return rows;
		}
	 

	    public List<ExamDetails> getAllExams() throws SQLException {
	        List<ExamDetails> exams = new ArrayList<>();
	        String selectAllExams = "SELECT exam_id,exam_name,description,exam_date,application_start_date,application_end_date FROM exams";
	        try (PreparedStatement preparedStatement = connect.prepareStatement(selectAllExams);
	             ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                int examId = resultSet.getInt("exam_id");
	                String examName = resultSet.getString("exam_name");
	                String description = resultSet.getString("description");
	                Date examDate = resultSet.getDate("exam_date");
	                Timestamp applicationStartDate = resultSet.getTimestamp("application_start_date");
	                Timestamp applicationEndDate = resultSet.getTimestamp("application_end_date");
	                exams.add(new ExamDetails(examId, examName, description, examDate, applicationStartDate, applicationEndDate));
	            }
	        }
	        return exams;
	    }

	 
		public boolean findUser(String email ,String password,UserDetails details,boolean isSignIn) throws Exception {
			//System.out.println(id);
			//System.out.println(connect);
			PasswordEncryption mask = new PasswordEncryption();
			String findUser = "SELECT roll_no,full_name,email, password,role_id FROM user_credentials WHERE email = ? AND is_active = ?";
		    PreparedStatement prepareStatement = connect.prepareStatement(findUser);
		    prepareStatement.setString(1, email);
		    prepareStatement.setBoolean(2, true);
		    ResultSet resultSet = prepareStatement.executeQuery();
		    if (resultSet.next()) {
		        int id = resultSet.getInt("roll_no");
		        String username = resultSet.getString("full_name");
		        String dbemail = resultSet.getString("email");
		        String dbpassword= resultSet.getString("password");
		        int roleId = resultSet.getInt("role_id");
		        System.out.println("email = " + dbemail + "password = " + dbpassword);
		        System.out.println("found record-id :"+id);
		        details.setUsername(username);
	        	details.setRollNo(id);
	        	details.setRoleId(roleId);
		        if(mask.decrypt(dbpassword).equals(password) && email.equals(dbemail) && isSignIn) {
		        	return true;
		        }
		        else if(email.equals(dbemail) && !isSignIn) {
		        	return true;
		        }
		        else {
		        	System.out.println(password +" " + email + " " + isSignIn);
		        	return false;
		        }
		        
		    }
		    return false;
		}
		
		public ExamDetails getExamById(int examId) throws SQLException {
		    ExamDetails exam = null; // Declare the variable here

		    String getExamQuery = "SELECT * FROM exams WHERE exam_id = ?";
		    try (PreparedStatement preparedStatement = connect.prepareStatement(getExamQuery)) {
		        preparedStatement.setInt(1, examId);
		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            if (resultSet.next()) {
		                int id = resultSet.getInt("exam_id");
		                String examName = resultSet.getString("exam_name");
		                String description = resultSet.getString("description");
		                Date examDate = resultSet.getDate("exam_date");
		                Timestamp applicationStartDate = resultSet.getTimestamp("application_start_date");
		                Timestamp applicationEndDate = resultSet.getTimestamp("application_end_date");
		                exam = new ExamDetails(id, examName, description, examDate, applicationStartDate, applicationEndDate);
		            }
		        }
		    }
		    return exam;
		    }
		}


