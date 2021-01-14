package com.company.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

	private UserSkill getUserSkill(ResultSet rs) throws Exception{
		int userSkillId=rs.getInt("user_skill_id");
		int userId=rs.getInt("id");
		int skillId=rs.getInt("skill_id");
		String skillName=rs.getString("skill_name");
		int power=rs.getInt("power");
		
		return new UserSkill(userSkillId, new User(userId), new Skill(skillId,skillName), power);
	}
	
	@Override
	public List<UserSkill> getAllSkillByUserId(int userId) {
		List<UserSkill> result=new ArrayList<>();
		
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("SELECT u.*,"
					+ "us.id as user_skill_id,"
					+ "us.skill_id,"
					+ "s.`name` as skill_name,"
					+ "us.power FROM user_skill as us "
					+ "LEFT JOIN `user` as u ON us.user_id=u.id "
					+ "LEFT JOIN skill as s ON us.skill_id=s.id "
					+ "where us.user_id="+userId);
			pStmt.execute();
			ResultSet rs=pStmt.getResultSet();
			
			while(rs.next()){
				UserSkill u=getUserSkill(rs);
				 result.add(u);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return result;
	}

	@Override
	public boolean insertUserSkill(UserSkill obj) {
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("Insert into "
					+ "user_skill (user_id,skill_id,power) values(?,?,?)");
			pStmt.setInt(1, obj.getUser().getId());
			pStmt.setInt(2, obj.getSkill().getId());
			pStmt.setInt(3, obj.getPower());
			return pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public boolean updateUserSkill(UserSkill obj) {
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("Update user_skill "
					+ "set user_id=?,skill_id=?,power=? where id=?");
			pStmt.setInt(1, obj.getUser().getId());
			pStmt.setInt(2, obj.getSkill().getId());
			pStmt.setInt(3, obj.getPower());
			pStmt.setInt(4, obj.getId());
			return pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeUserSkill(int id) {
		try(Connection c=connection()) {
			PreparedStatement pStmt=c.prepareStatement("Delete from user_skill where id=?");
			pStmt.setInt(1, id);
			return pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

}
