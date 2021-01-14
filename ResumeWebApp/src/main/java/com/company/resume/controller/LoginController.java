package com.company.resume.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;
import com.company.resume.util.ControllerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try{
                String email=request.getParameter("email");
                String password=request.getParameter("password");

                UserDaoInter userDaoInter= Context.userDaoInstance();
                User user=userDaoInter.findByEmail(email);
                System.out.println(user.getEmail());
                System.out.println(user.getPassword());
                if(user==null){
                    throw new IllegalArgumentException("user doesn't exist");
                }

                BCrypt.Result rs=BCrypt.verifyer().verify(password.toCharArray(),user.getPassword().toCharArray());
                if(!rs.verified){
                    throw new IllegalArgumentException("password is incorrect!");
                }
                request.getSession().setAttribute("loggedInUser",user);
                response.sendRedirect("users");
            }catch (IOException ex){
                ControllerUtil.stuckOnError(response,ex);
            }
    }

}
