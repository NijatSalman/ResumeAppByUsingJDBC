package com.company.dao.inter;

import java.util.List;

import com.company.entity.UserSkill;

public interface UserSkillDaoInter {

	public List<UserSkill> getAllSkillByUserId(int userId);
	
	public boolean insertUserSkill(UserSkill obj);
	
	public boolean updateUserSkill(UserSkill obj);
	
	public boolean removeUserSkill(int userId);
}
