package com.company.dao.inter;
import java.util.List;

import com.company.entity.Skill;;
public interface SkillDaoInter {

	public List<Skill> getAllSkill();
	
	public Skill getSkillById(int id);
	
	public boolean updateSkill(Skill obj);
	
	public boolean addSkill(Skill obj);
	
	
}
