package com.company.dao.inter;

import java.util.List;

import com.company.entity.EmploymentHistory;

public interface EmploymentHistoryDaoInter {

	public List<EmploymentHistory> getAllEmploymentHistoryById(int id);
	
	public boolean addEmploymentHistory(EmploymentHistory obj);
	
	public boolean updateEmploymentHistory(EmploymentHistory obj);
	
	public boolean removeEmploymentHistory(int id);
	
	
}
