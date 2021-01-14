package com.company.resume;


import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRequestUtil{

    public static void checkRequest(HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException {
          String strId=request.getParameter("id");
          if(strId==null || strId.trim().isEmpty()){
              throw new IllegalArgumentException("id is not specified");
          }

    }

    public static User processRequest(HttpServletRequest request, HttpServletResponse response){

                  UserRequestUtil.checkRequest(request,response);

        UserDaoInter userDaoInter= Context.userDaoInstance();

        Integer id=Integer.parseInt(request.getParameter("id"));
        User user=userDaoInter.getById(id);
        if(user==null){
            throw new IllegalArgumentException("There is no user with this id");
        }
        return user;
    }

}
