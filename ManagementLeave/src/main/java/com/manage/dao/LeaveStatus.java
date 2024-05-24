package com.manage.dao;

public class LeaveStatus extends LeaveCalculator {

	@Override
	public boolean leaveStatusGenerator(int noOfLeaves) {
		if(noOfLeaves > 4 && noOfLeaves > 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean totalLeaveForEmployee(int leaves) {
		if(leaves >= 10) {
			return true;
		}
		return false;
	}
	
}
