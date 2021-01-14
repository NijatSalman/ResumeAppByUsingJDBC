package com.company.dao.inter;

import java.util.List;

import com.company.entity.Country;
import com.company.entity.User;

public interface CountryDaoInter {

	public List<Country> getAllCountry();
	
	public Country getById(int id);
	
	public boolean addCountry(Country obj);
	
	public boolean updateCountry(Country obj);
	
}
