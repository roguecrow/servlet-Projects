package com.manage.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ConnectionUtil {
//	public static void main(String arg[]) throws ClassNotFoundException, SQLException {
//		ServerManager saver = new ServerManager();
//		
//		//saver.readvalues(connect);
//        //saver.insertValues(connect);
//		//saver.updateValues(connect);
//		//saver.removeValues(connect);
//	}
	public  Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_management_system","root","12345678");
		return connection;	
	}
}
