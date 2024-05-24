package com.manage.dao;

import java.util.Date;

public interface LeaveOperationsDAO {
	int totalNoOfLeaves(Date start, Date end);
	boolean leaveStatusGenerator(int noOfLeaves);
}
