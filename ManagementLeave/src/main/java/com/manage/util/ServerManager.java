package com.manage.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.manage.model.LeaveDetails;

public class ServerManager {
	 ConnectionUtil connection;
     Connection connect;

    public ServerManager() throws ClassNotFoundException, SQLException {
        connection = new ConnectionUtil();
        connect = connection.getConnection();
    }
    
	public int userRegisteration(LeaveDetails details) throws ClassNotFoundException, SQLException {
	    String addEmployee = "INSERT INTO emp_credentials (emp_id, role_id, username, dept_id)"
	    		+ "VALUES (?, ?, ?, ?)";
	    PreparedStatement prepareStatement = connect.prepareStatement(addEmployee);
	    prepareStatement.setInt(1,Integer.parseInt(details.getEmployeeId()));
	    prepareStatement.setInt(2,details.getRoleId());
	    prepareStatement.setString(3, details.getUsername());
	    prepareStatement.setInt(4, details.getDeptId());
	    int rows = prepareStatement.executeUpdate();
	    System.out.println("affected row :" + rows);
		return rows;
	}
	public void insertEmpDetails(LeaveDetails details) throws ClassNotFoundException, SQLException {
	    String addEmployee = "INSERT INTO leave_details (emp_id, request_id, start_date, end_date, leave_type, status, no_of_leaves) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	    PreparedStatement prepareStatement = connect.prepareStatement(addEmployee);
	    prepareStatement.setInt(1, Integer.parseInt(details.getEmployeeId()));
	    prepareStatement.setString(2, details.getRequestId());
	    prepareStatement.setDate(3, new java.sql.Date(details.getStartDate().getTime()));
	    prepareStatement.setDate(4, new java.sql.Date(details.getEndDate().getTime()));
	    prepareStatement.setString(5, details.getLeaveType());
	    prepareStatement.setBoolean(6, details.isStatus());
	    prepareStatement.setInt(7, details.getNoOfLeaves());
	    int rows = prepareStatement.executeUpdate();
	    System.out.println("affected row :" + rows);
	    if(rows == 1) {
			System.out.println("Successfully Created account");
		}
		else {
			System.out.println("failed to creeate \nplease try again later !!!");
		}
	}

	
	public void readEmpDetails(String empId) throws SQLException, ClassNotFoundException {
		
		String empLeaves = "select * from leave_details where emp_id = " + Integer.parseInt(empId);
		PreparedStatement prepareStatement = connect.prepareStatement(empLeaves);
	    //prepareStatement.setInt(1, Integer.parseInt(details.getEmployeeId()));
		ResultSet rows = prepareStatement.executeQuery();
		
		ResultSetMetaData metaData = rows.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + "   ");
        }
        System.out.println();

        while (rows.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rows.getString(i) + "    ");
            }
            System.out.println();
        }
	}
	
	public void updateUserName(LeaveDetails details) throws ClassNotFoundException, SQLException {
	    String updateUsername = "UPDATE emp_credentials SET username = '" + details.getUsername() + "' WHERE emp_id = " + Integer.parseInt(details.getEmployeeId());
		PreparedStatement prepareStatement = connect.prepareStatement(updateUsername);
		int rows = prepareStatement.executeUpdate();
		System.out.println(rows);
		if(rows != 0) {
			System.out.println("Successfully Updated");
		}
		else {
			System.out.println("failed to Updated");
		}
		
	}
	
	public void removeEmpAccount(LeaveDetails details) throws ClassNotFoundException, SQLException {
		String deleteUser = "UPDATE emp_credentials SET is_active = '"+ 0 +"' where emp_id = "+ details.getEmployeeId();
		PreparedStatement prepareStatement = connect.prepareStatement(deleteUser);
		int rows = prepareStatement.executeUpdate();
		System.out.println(rows);
		if(rows >= 1) {
			System.out.println("Successfully deleted");
		}
		else {
			System.out.println("failed to delete");
		}
	}
	public boolean findEmpID(LeaveDetails details,String id) throws ClassNotFoundException, SQLException {
		//System.out.println(id);
		//System.out.println(connect);
		String findUser = "SELECT emp_id,username FROM emp_credentials WHERE emp_id = ? and is_active = ?";
	    PreparedStatement prepareStatement = connect.prepareStatement(findUser);
	    prepareStatement.setInt(1, Integer.parseInt(id));
	    prepareStatement.setBoolean(2, true);
	    ResultSet resultSet = prepareStatement.executeQuery();
	    if (resultSet.next()) {
	    	System.out.println(resultSet);
	        int count = resultSet.getInt(1);
	        int empId = resultSet.getInt("emp_id");
	        String userName= resultSet.getString("username");
	        details.setEmployeeId(Integer.toString(empId));
	        details.setUsername(userName);
	        System.out.println("found record :"+count);
	        return count > 0;
	    }
	    return false;
	}
	
	public int leaveCalculator(LeaveDetails details) throws ClassNotFoundException, SQLException {
		//System.out.println(id);
		//System.out.println(connect);
		int count = 0;
		String leaveFinder = "SELECT no_of_leaves FROM leave_details WHERE emp_id = ? and status = ?";
	    PreparedStatement prepareStatement = connect.prepareStatement(leaveFinder);
	    prepareStatement.setInt(1, Integer.parseInt(details.getEmployeeId()));
	    prepareStatement.setBoolean(2, true);
	    ResultSet resultSet = prepareStatement.executeQuery();
	    
	    while(resultSet.next()) {
	    	count = count + resultSet.getInt("no_of_leaves");
	    }
        System.out.println("total leaves taken :"+count);
        return count;
	}

}

