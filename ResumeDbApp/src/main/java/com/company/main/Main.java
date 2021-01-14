package com.company.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.impl.UserDaoImpl;
/**
 * Hello world!
 *@author NijatSalmanov
 */

public class Main 
{
	
	
	
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
//        UserDaoInter userDao=Context.userDaoInstance();
//        User user=new User("Kenan", "Mirzayev","+994700000000", "mirzayev@gmail.com");
        
//        List<User> userList=userDao.getAll();
//        System.out.println(userDao.getById(5));
//        System.out.println("list: "+userList);    
        
//        UserSkillDaoInter userDao=Context.userSkillDaoInstance();
//        System.out.println(userDao.getAllSkillByUserId(7));
        
//        UserDaoInter userDao=Context.userDaoInstance();
//        User user=userDao.getById(7);
//        user.setPhone("+994-969-44-47");
//        System.out.println(userDao.updateUser(user));
       
        
//        CountryDaoInter countryDao=Context.countryDaoInstance(); 
//        
//  
//        
//       System.out.println(countryDao.addCountry(new Country( "Gemany", "German")));
//       System.out.println(countryDao.getAllCountry());
        
//        SkillDaoInter skillDao=Context.skillDaoInstance();
        
//        Skill skillObj=skillDao.getSkillById(1);
//        skillObj.setName("Java");
////        skillDao.updateSkill(skillObj);
//        System.out.println(skillDao.getSkillById(1));
//        System.out.println(skillDao.addSkill(new Skill("Sql")));
//        System.out.println(skillDao.getAllSkill());
        
          UserDaoInter userDaoInter=Context.userDaoInstance();
       userDaoInter.updateUser(new User(7,"Serkhan","Serkhanov","55","@gmail"));
//       userDaoInter.removeUser(10);
        List<User> userList=userDaoInter.getAll();
        for (User user:userList) {
            System.out.println("user: "+user);
        }

    }
}
