package com.manage.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.manage.model.LeaveDetails;


public class LeaveDataSaver {
	
	public void fileWriter(LeaveDetails details,int totalLeaves) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String data = details.getEmployeeId() + "," +
                details.getUsername() + "," +
                details.getRequestId() + "," +
                details.getLeaveType() + "," +
                dateFormat.format(details.getStartDate()) + "," +
                dateFormat.format(details.getEndDate()) + "," +
                totalLeaves + "," + 
                details.isStatus() + "\n";

		 try {  
		        FileWriter fwrite = new FileWriter("C:\\Users\\babu3560\\eclipse-workspace\\Leave_Management_System\\leave_Data.txt",true);  
		        // writing the content into the FileOperationExample.txt file  
		        fwrite.write(data);   
		   
		        // Closing the stream  
		        fwrite.close();   
		        //System.out.println("Leave details stored to file.");  
		    } catch (IOException e) {  
		        System.out.println("Unexpected error occurred");  
		        e.printStackTrace();  
		        }  
	}
	
	public int fileReaderForLeave(LeaveDetails details) {
		int totalLeaves = 0;
		try {  
            // Create f1 object of the file to read data  
            File f1 = new File("C:\\Users\\babu3560\\eclipse-workspace\\Leave_Management_System\\leave_Data.txt");    
            Scanner dataReader = new Scanner(f1);
            while (dataReader.hasNextLine()) {  
                String fileData = dataReader.nextLine();
                String [] fields = fileData.split(",");
                String id = fields[0];
                String type = fields[3];
                String status = fields[7];
                String defStatus = "true";
                if(id.equals(details.getEmployeeId()) && !type.equals(details.getLeaveType()) && status.equals(defStatus)) {
                	totalLeaves += Integer.parseInt(fields[6]);
                }
                //System.out.println(fileData);  
            }  
            dataReader.close();  
        } catch (FileNotFoundException exception) {  
            System.out.println("Unexcpected error occurred!");  
            exception.printStackTrace();  
        }
		return totalLeaves;
	}
	public boolean fileReaderForPermission(LeaveDetails details) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Date format in the file

	    try {  
	        File f1 = new File("C:\\Users\\babu3560\\eclipse-workspace\\Leave_Management_System\\leave_Data.txt");    
	        Scanner dataReader = new Scanner(f1);
	        while (dataReader.hasNextLine()) {  
	        	//System.out.println("came in while");
	            String fileData = dataReader.nextLine();
	            String[] fields = fileData.split(",");
	            String date = fields[4];
	            String id = fields[0];
	    		String dateFromUser = simpleDateFormat.format(details.getStartDate());
	           // System.out.println(date);
	            if (date.equals(dateFromUser) && id.equals(details.getEmployeeId())) {
	                //System.out.println("came true");
	                dataReader.close(); 
	                return true;
	            }
	        }  
	        //System.out.println("came false");
	        dataReader.close(); // Close the file reader here too

	    } catch (FileNotFoundException exception) {  
	        System.out.println("Unexpected error occurred!");  
	        exception.printStackTrace();  
	    }
	    return false;
	}
}



	
