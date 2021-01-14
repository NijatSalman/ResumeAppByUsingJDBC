package com.company.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

	private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception{
		int id=rs.getInt("id");
		String header=rs.getString("header");
		Date beginDate=rs.getDate("begin_date");
		Date endDate=rs.getDate("end_date");
		String jobDescription=rs.getString("job_description");
		int userId=rs.getInt("user_id");
	   
		User user=new User(userId);
			
		EmploymentHistory empHistory= new EmploymentHistory(id, header, beginDate, endDate, jobDescription, user);
		return empHistory;
	}
	
	@Override
	public List<EmploymentHistory> getAllEmploymentHistoryById(int id) {
		List<EmploymentHistory> result=new ArrayList<>();
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("Select * from employment_history where user_id="+id);
			pStmt.execute();
			ResultSet rs=pStmt.getResultSet();
			while (rs.next()) {
				EmploymentHistory empHistory=getEmploymentHistory(rs);
					result.add(empHistory);
			}
		} catch (Exception e) {
		  
		}
		return result;
	}

	@Override
	public boolean addEmploymentHistory(EmploymentHistory obj) {
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("Insert into employment_history"
					+ " (header,begin_date,end_date,job_description,user_id) values(?,?,?,?,?)" , Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, obj.getHeader());
			pStmt.setDate(2, obj.getBeginDate());
			pStmt.setDate(3, obj.getEndDate());
			pStmt.setString(4, obj.getJobDescription());
			pStmt.setInt(5, obj.getUser().getId());
			pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateEmploymentHistory(EmploymentHistory obj) {
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("Update employment_history set header=?, begin_date=?, end_date=?, job_description=?, user_id=? where id=?");
			pStmt.setString(1, obj.getHeader());
			pStmt.setDate(2, obj.getBeginDate());
			pStmt.setDate(3, obj.getEndDate());
			pStmt.setString(4, obj.getJobDescription());
			pStmt.setInt(5, obj.getUser().getId());
			pStmt.setInt(6, obj.getId());
			pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean removeEmploymentHistory(int id) {
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("Delete from employment_history where id=?");
			pStmt.setInt(1, id);
			pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	

	
	
}
