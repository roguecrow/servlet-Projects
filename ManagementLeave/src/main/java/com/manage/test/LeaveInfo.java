package com.manage.test;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.manage.model.LeaveDetails;
import com.manage.util.ServerManager;

public class LeaveInfo {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LoginCred lc = new LoginCred();
		Scanner sc = new Scanner(System.in);
		LeaveDetails details = new LeaveDetails();
		LeaveModules modules = new LeaveModules();
		InfoValidator val = new InfoValidator();
		ServerManager manage = new ServerManager();
		LoginCred cred = new LoginCred();
		// TODO Auto-generated method stub
		System.out.println("************Leave Management System************");
		System.out.println("Please Enter the EmpId :");
		
		if(lc.loginToYourAccountFromSer(details,val.validator(1),sc)) {
			System.out.println("Username -" + details.getUsername() + "emp-id -" + details.getEmployeeId());
			while(true) {
				System.out.println("Enter an Option (1 or 2) :");
				System.out.println("1. Apply Leave \n2. Leave History \n3. Update User Name \n4. Delete employee leave account");
				int num = Integer.parseInt(val.validator(1));
				if(num < 5 && num > 0) {
					switch(num) {
					case 1 :
						modules.applyLeave(details);
						break;
					case 2 :
						manage.readEmpDetails(details.getEmployeeId());
						//modules.showHistory(details.getEmployeeId());
						break; 
					case 3 :
		                System.out.println("Enter the username:");
						cred.createEmpDetails(sc, 1, details,manage);
						manage.updateUserName(details);
						break;
					case 4 :
						System.out.println("are you sure you want to delete your account ?(y/n)");
						if(val.validator(2).equalsIgnoreCase("y")) {
							manage.removeEmpAccount(details);
							System.out.println("Done !");
							main(null);
						}
						break;
					}
					
					System.out.println("Press 'y' to apply again :");
					String s = val.validator(2);
					if(!s.equalsIgnoreCase("y")) {
						System.out.println("Quiting Application...");
						break;
					}
				}
				else {
					System.out.println("enter a valid number :");
				}
			}
		}
	}


}
