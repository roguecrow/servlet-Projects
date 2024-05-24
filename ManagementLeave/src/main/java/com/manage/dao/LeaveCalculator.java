package com.manage.dao;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class LeaveCalculator implements LeaveOperationsDAO {
	
	@Override
	public int totalNoOfLeaves(Date start , Date end) {
		long diffInMillis = end.getTime() - start.getTime();
	    long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
	    int numberOfLeaves = (int) diffInDays + 1;
	    return numberOfLeaves;
	}
	
	public abstract boolean leaveStatusGenerator(int noOfLeaves);
	}


