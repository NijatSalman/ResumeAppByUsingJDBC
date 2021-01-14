package com.company.main;

import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.EmploymentHistory;
import com.company.impl.CountryDaoImpl;
import com.company.impl.EmploymentHistoryDaoImpl;
import com.company.impl.SkillDaoImpl;
import com.company.impl.UserDaoImpl;
import com.company.impl.UserSkillDaoImpl;

public class Context {

	
	public static UserDaoInter userDaoInstance(){
		return new UserDaoImpl();
	}
	
	public static UserSkillDaoInter userSkillDaoInstance(){
		return new UserSkillDaoImpl();
	}
	
	public static EmploymentHistoryDaoInter employmentHistoryDaoInstance(){
		return new EmploymentHistoryDaoImpl();
	}
	
	public static CountryDaoInter countryDaoInstance(){
		return new CountryDaoImpl();
	}
	
	public static SkillDaoInter skillDaoInstance(){
		return new SkillDaoImpl();
	}
}
