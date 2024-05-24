package com.manage.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;

import com.manage.dao.LeaveStatus;
import com.manage.files.FileGenerator;
import com.manage.files.LeaveDataSaver;
import com.manage.model.LeaveDetails;
import com.manage.util.ServerManager;

public class LeaveModules {
	
	static String requestIDGenerator() {
		Random random = new Random();
		long requestID =  random.nextLong(999L,9999L);
		return "111"+Long.toString(requestID);
	}
	
	
	static void printLeaveDetails(LeaveDetails details) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String date = simpleDateFormat.format(details.getStartDate());
		System.out.println("Employee ID :" + details.getEmployeeId()+
	             "\nYour Request ID : "+ details.getRequestId() +
	             "\nleave Type : "+details.getLeaveType()+"\nstartDate :" +date);
		System.out.println("------------------------------------------------");
	}
	
	
	static void getLeaveDetails(LeaveDetails details,LeaveStatus status) {
		String[] leaveType = new String[] {"Medical Leave", "Personal Leave", "Permission"};
		InfoValidator val = new InfoValidator();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
		System.out.println("leave Type :");
		for(int i = 0;i<leaveType.length;i++) {
			System.out.println(i+1+" " + leaveType[i]);
		}
		int num = Integer.parseInt(val.validator(5));
		details.setLeaveType(leaveType[num - 1]);
		while (true) {
			if(num == 3) {
				System.out.println("You will be getting a 2 hours permission.");
				System.out.println("Enter a date(dd/mm/yyyy) :");
				try {
					details.setStartDate(sdf.parse(val.validator(4)));
					details.setEndDate(details.getStartDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Enter Leave start date(dd/mm/yyyy) :");
				try {
					details.setStartDate(sdf.parse(val.validator(4)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Enter Leave End date(dd/mm/yyyy) :");
				try {
					details.setEndDate(sdf.parse(val.validator(4)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int leave = status.totalNoOfLeaves(details.getStartDate(), details.getEndDate());
			if(leave > 0) {
				details.setNoOfLeaves(leave);
				break;
			}
			else {
				System.out.println("Enter a valid date ...");
			}
		}
			details.setRequestId(requestIDGenerator());
	}
	
	void applyLeave(LeaveDetails details) throws ClassNotFoundException, SQLException {
		LeaveStatus status = new LeaveStatus();
		//FileGenerator fg = new FileGenerator();
		LeaveDataSaver ld = new LeaveDataSaver();
		ServerManager manage = new ServerManager();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  

		getLeaveDetails(details,status);
		int totalLeave = status.totalNoOfLeaves(details.getStartDate(), details.getEndDate());
		if(details.getLeaveType() == "Permission") {
			if(!ld.fileReaderForPermission(details)) {
				System.out.println("Permission has been granted !");
				details.setStatus(true);
			}
			else {
				System.out.println("You have already taken Permisson for this date :"+
			        sdf.format(details.getStartDate())+
			        "\nPermission you asked for is not approved.");
				details.setStatus(false);
			}
			System.out.println("-------------------------------------------");
		}
		else {
			 System.out.println("total no. of leave :" + totalLeave);
			if(status.leaveStatusGenerator(totalLeave)) {
				details.setStatus(true);
				System.out.println("You can take leave for " + totalLeave + " days");
			}
			else {
				details.setStatus(false);
				System.out.println("You are not eligible for "+ totalLeave + " days leaves");
			}
		}
		
		printLeaveDetails(details);
		//fg.fileCreater();
		manage.insertEmpDetails(details);
		ld.fileWriter(details,totalLeave);
		//int empLeaves = ld.fileReaderForLeave(details);
		int empLeaves = manage.leaveCalculator(details);
		if(status.totalLeaveForEmployee(empLeaves)) {
			System.out.println("You have exceeded the leave limit of 10."
					+ "\nNo. of Leaves taken : "+empLeaves + 
					"\nSo the leave you are about to take is going to be LOP(LOSS OF PAY)");
		}
	}
	
	 void showHistory(String empId) {
		try {  
            // Create f1 object of the file to read data  
            File f1 = new File("C:\\Users\\babu3560\\eclipse-workspace\\Leave_Management_System\\leave_Data.txt");    
            Scanner dataReader = new Scanner(f1);
            boolean flag = false;
           // System.out.println("EmpId|userName|requestId|leave type|start date|end date|No. of leaves|leave status");
            while (dataReader.hasNextLine()) {  
          
            	String fileData = dataReader.nextLine();
                String [] fields = fileData.split(",");
                String id = fields[0];
                if(id.equals(empId)) {
                	System.out.println("EmpId: " + fields[0]);
                    System.out.println("Username: " + fields[1]);
                    System.out.println("Request ID: " + fields[2]);
                    System.out.println("Leave Type: " + fields[3]);
                    System.out.println("Start Date: " + fields[4]);
                    System.out.println("End Date: " + fields[5]);
                    System.out.println("Number of Leaves: " + fields[6]);
                    System.out.println("Leave Status: " + fields[7]);
                    System.out.println("---------------------------------------");
                	flag = true;
                }
            }
            if(flag == false) {
            	System.out.println("There is no leave history for this Employee ID.");
            }
            dataReader.close();  
        } catch (FileNotFoundException exception) {  
            System.out.println("Unexcpected error occurred!");  
            exception.printStackTrace();  
        }
	}

}
