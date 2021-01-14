package com.company.resume.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = {"/users"})
public class UserController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        UserDaoInter userDaoInter= Context.userDaoInstance();

        String name=request.getParameter("name");
        String surname=request.getParameter("surname");
        String nationalityStr=request.getParameter("nid");
        Integer nationalityId=null;
        if (nationalityStr!=null){
            nationalityId=Integer.valueOf(nationalityStr);
        }

        List<User> userList=userDaoInter.getAll(name,surname,nationalityId);

        request.setAttribute("userList", userList);
        request.getRequestDispatcher("users.jsp").forward(request,response);
    }
}
