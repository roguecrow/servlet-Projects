package com.manage.test;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.manage.dao.InvalidRangeException;


 class CustomRangeException  {  
     void validate (int num) throws InvalidRangeException {    
       if(num > 3 || num <= 0){  
        throw new InvalidRangeException("range is out of bound ");    
    }  
       else {   
        System.out.println("In Range");   
        }   
     }
 }

public class InfoValidator {
    String validator(int n) {
		Scanner sc = new Scanner(System.in);
		InputsValidator validate = new InputsValidator();
		CustomRangeException exp = new CustomRangeException();
		int num;
		String str = null;
		while(sc.hasNext()) {
			try {
				if(n == 1) {
					num = sc.nextInt();
					 if(!validate.intInputChecker(num)) {
						 System.out.println("Enter a valid Integer :");
					 }
					 else {
						 return String.valueOf(num);
					 }
			}
			else if(n == 2) {
				str = sc.next();
				 if(!validate.stringInputChecker(str)) {
					 System.out.println("enter a valid input :");
				 }
				 else {
					 return str;
				 }
			}
			else if(n==3) {
				str = sc.next();
				 if(!validate.longNumberInputChecker(str)) {
					 System.out.println("enter a valid input :");
				 }
				 else {
					 return str;
				 }
			}
			else if(n==4) {
				str = sc.next();
				 if(!validate.dateInputChecker(str)) {
					 System.out.println("enter a valid input :");
				 }
				 else {
					 return str;
				 }
			}
			else if(n==5) {
				num = sc.nextInt();
				 if(!validate.intInputChecker(num)) {
					 System.out.println("Enter a valid Integer :");
				 }
				 else {
					 try {
						 exp.validate(num);
						 return String.valueOf(num);
					 }
					 catch(InvalidRangeException ex){
				            System.out.println("Caught the exception");  
				            System.out.println("Exception occured: " + ex);  
					 }
				 }
			}
			else if(n == 6) {
					num = sc.nextInt();
					 if(!validate.intInputChecker(num)) {
						 if(num > 2) {
							 System.out.println("Enter a valid Integer :");
						 }
					 }
					 else {
						 return String.valueOf(num);
					 }
			}
				
			else {
				str = sc.next();
				 if(!validate.IfscCodeInputChecker(str)) {
					 System.out.println("enter a valid input :");
				 }
				 else {
					 return str;
				 }
			}
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input. Please enter a valid input (Input Mismatch).");
	            sc.nextLine();
	        }
			
		}
		return str;
	}
}
