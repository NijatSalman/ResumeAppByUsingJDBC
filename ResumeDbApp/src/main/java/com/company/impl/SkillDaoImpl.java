package com.company.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter{

	private Skill getSkill(ResultSet rs) throws Exception{
			int id=rs.getInt("id");
			String name=rs.getString("name");	
			return new Skill(id, name);
	}
	
	@Override
	public List<Skill> getAllSkill() {
		List<Skill> result=new ArrayList<>();
		try(Connection c=connection()) {
			Statement stmt=c.createStatement();
			stmt.execute("Select * from skill");
			ResultSet rs=stmt.getResultSet();
			Skill obj=null;
			while (rs.next()) {
				obj=getSkill(rs);
				result.add(obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public Skill getSkillById(int id) {
		Skill result=null;
		try(Connection c=connection()) {
			Statement stmt=c.createStatement();
			stmt.execute("Select * from skill where id="+id);
			ResultSet rs=stmt.getResultSet();
			Skill obj=null;
			while (rs.next()) {
				obj=getSkill(rs);
				result=obj;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public boolean updateSkill(Skill obj) {
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("Update skill set name=? where id=?");
			pStmt.setString(1, obj.getName());
			pStmt.setInt(2, obj.getId());
			return pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addSkill(Skill obj) {
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("Insert into skill (name) values(?)", Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, obj.getName());
			pStmt.execute();
			
			ResultSet generatedKeys= pStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				obj.setId(generatedKeys.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	
}
