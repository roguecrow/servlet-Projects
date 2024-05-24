package com.manage.files;

import java.io.File;
import java.io.IOException;

public class FileGenerator {
	
	public void fileCreater() {
		 try {   
			    File f0 = new File("C:\\Users\\babu3560\\eclipse-workspace\\Leave_Management_System\\leave_Data.txt"); 
          if (f0.createNewFile()) {  
                     System.out.println("File " + f0.getName() + " is created successfully.");  
          } 
          else {  
                    // System.out.println("File is already exist in the directory.");  
          }  
        } catch (IOException exception) {  
                 System.out.println("An unexpected error is occurred.");  
                 exception.printStackTrace();  
     }      
	}
}