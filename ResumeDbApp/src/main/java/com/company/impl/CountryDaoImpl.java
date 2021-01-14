package com.company.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

	private Country getCountry(ResultSet rs) throws Exception{
		int id=rs.getInt("id");
		String name=rs.getString("name");
		String nationality=rs.getString("nationality");
		
		return new Country(id, name, nationality);	
	}
	
	@Override
	public List<Country> getAllCountry() {
		List<Country> result=new ArrayList<Country>();
		try(Connection c=connection()) {
			Statement stmt=c.createStatement();
			stmt.execute("Select * from country");
			ResultSet rs=stmt.getResultSet();
			while (rs.next()) {
				Country obj=getCountry(rs);
				result.add(obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public boolean addCountry(Country obj) {
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("Insert into country (name,nationality) values (?,?) ");
			pStmt.setString(1, obj.getName());
			pStmt.setString(2, obj.getNationality());
			return pStmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean updateCountry(Country obj) {
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("UPDATE country "
					+ "SET name=? ,nationality=? where id=?");
		
			pStmt.setString(1, obj.getName());
			pStmt.setString(2, obj.getNationality());
			pStmt.setInt(3, obj.getId());
			return pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Country getById(int id) {
		Country result=null;
		try(Connection c=connection()) {
			Statement stmt=c.createStatement();
			stmt.execute("Select * from country where id="+id);
			ResultSet rs=stmt.getResultSet();
			while (rs.next()) {
				result=getCountry(rs);		
			}
		} catch (Exception e) {
			
		}
		return result;
	}

}
