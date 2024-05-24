package com.manage.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.manage.model.LeaveDetails;
import com.manage.util.ServerManager;

public class LoginCred {
	boolean createEmpDetails(Scanner sc,int n,LeaveDetails details,ServerManager manage) throws ClassNotFoundException, SQLException {
		String userInput,
		 userNamePattern = "^[A-Za-z]+{8,20}$",
		 empIdPattern = "^[0-9]{6,8}$";
		while(sc.hasNext()) {
			userInput = sc.nextLine();
			if(n==1) {
				if(userInput.matches(userNamePattern)) {
					details.setUsername(userInput);
					break;
				}
				System.out.println("Enter valid User Name.");
				System.out.println("use only alphabets");
			}
			else {
				if(userInput.matches(empIdPattern)) {
					if(!manage.findEmpID(details, userInput)) {
						details.setEmployeeId(userInput);
						break;
					}	
					System.out.println("Emp id already exists. \nPlease enter new one :");
				}
				System.out.println("Enter valid employee Id :");
				 System.out.println("Atleast enter six numbers");
			}
		}
		return false;
	}
	
	boolean loginToYourAccount(LeaveDetails details, String id, Scanner sc) throws ClassNotFoundException, SQLException {
	    InfoValidator val = new InfoValidator();
	    try {
	        File f1 = new File("C:\\Users\\babu3560\\eclipse-workspace\\Leave_Management_System\\leave_Data.txt");
	        if (!f1.exists() || f1.length() == 0) {
	            System.out.println("No data found. Please create an account to continue.");
	            System.out.println("Press 1 to create an account:");
	            if (Integer.parseInt(val.validator(1)) == 1) {
	                System.out.println(" ------- Employee SignUp Page -------");
	                System.out.println("Enter the username:");
	                createEmpDetails(sc, 1, details,null);
	                System.out.println("Enter the EmpId:");
	                createEmpDetails(sc, 2, details,null);
	                System.out.println("Account created successfully.");
	                return true;
	            } else {
	                System.out.println("Quitting from the Application...");
	                System.exit(0);
	            }
	        } else {
	            Scanner dataReader = new Scanner(f1);
	            while (dataReader.hasNextLine()) {
	                String fileData = dataReader.nextLine();
	                String[] fields = fileData.split(",");
	                String empId = fields[0];
	                String empName = fields[1];
	                if (empId.equals(id)) {
	                    details.setEmployeeId(id);
	                    details.setUsername(empName);	                    
	                    return true;
	                }
	            }
	            dataReader.close();
	            System.out.println("Employee not found. Please create an account to continue.");
	            System.out.println("Press 1 to create an account:");
	            if (Integer.parseInt(val.validator(1)) == 1) {
	                System.out.println(" ------- Employee SignUp Page -------");
	                System.out.println("Enter the username:");
	                createEmpDetails(sc, 1, details,null);
	                System.out.println("Enter the EmpId:");
	                createEmpDetails(sc, 2, details,null);
	                System.out.println("Account created successfully.");
	                return true;
	            } else {
	                System.out.println("Quitting from the Application...");
	                System.exit(0);
	            }
	        }
	    } catch (FileNotFoundException exception) {
	        System.out.println("Unexpected error occurred!");
	        exception.printStackTrace();
	    }
	    return false;
	}
	
	boolean loginToYourAccountFromSer(LeaveDetails details, String id, Scanner sc) throws ClassNotFoundException, SQLException {
		int role;
		Map<Integer, String> departmentMap = new HashMap<>();
        // Adding department details to the HashMap
        departmentMap.put(1, "Sales");
        departmentMap.put(2, "Marketing");
        departmentMap.put(3, "Human Resources");
        departmentMap.put(4, "Finance");
	    InfoValidator val = new InfoValidator();
		ServerManager manage = new ServerManager();

	        if (!manage.findEmpID(details,id)) {
	            System.out.println("No data found. Please create an account to continue.");
	            System.out.println("Press 1 to create an account:");
	            if (Integer.parseInt(val.validator(1)) == 1) {      
	            	System.out.println("Enter the role you are in the company : \n1.manager\n2.Employee");
	            	role  = Integer.parseInt(val.validator(6));
	            	if(role == 1) {
	            		System.out.println(" ------- Manager SignUp Page -------");
	            	}
	            	else {
		                System.out.println(" ------- Employee SignUp Page -------");
	            	}
	            	details.setRoleId(role);
	                System.out.println("Enter the username:");
	                createEmpDetails(sc, 1, details,manage);
	                System.out.println("Enter the EmpId:");
	                createEmpDetails(sc, 2, details,manage);
	                System.out.println("Select your Dept: \n1.Sales\n2.Marketing\n3.Human Resources\n4.Finance");
	                int deptId = Integer.parseInt(val.validator(1));
	                details.setDeptId(deptId);
	                details.setDeptName(departmentMap.get(deptId));
	                manage.userRegisteration(details);
	                System.out.println("Account created successfully.");
	                return true;
	            } else {
	                System.out.println("Quitting from the Application...");
	                System.exit(0);
	            }
	        }
	    return true;
	}
	
}
