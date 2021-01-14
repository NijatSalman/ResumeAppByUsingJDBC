package com.company.resume.controller;

import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.main.Context;
import com.company.resume.UserRequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name ="UserDetailController", urlPatterns={"/userdetail"})
public class UserDetailController extends HttpServlet {

      private  UserDaoInter userDaoInter= Context.userDaoInstance();
      private CountryDaoInter countryDaoInter=Context.countryDaoInstance();
         private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));

        String strAction=req.getParameter("action");
        if(strAction.equals("update")) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String address=req.getParameter("address");
            String email=req.getParameter("email");
            String profileDescription=req.getParameter("profileDescription");
            String phoneNumber=req.getParameter("phone");
            Integer countryId=Integer.valueOf(req.getParameter("selectedCountry"));
            Integer nationalityId=Integer.valueOf(req.getParameter("selectedNationality"));


            User user = userDaoInter.getById(id);
            user.setName(name);
            user.setSurname(surname);
            user.setAddress(address);
            user.setEmail(email);
            user.setPhone(phoneNumber);

            user.setProfileDescription(profileDescription);

            Country country=countryDaoInter.getById(countryId);
            user.setBirthPlace(country);

            Country nationality=countryDaoInter.getById(nationalityId);
            user.setNationality(nationality);


            try {
                Date date=format.parse(req.getParameter("date"));
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                user.setBirthDate(sqlDate);
            }catch (Exception ex){
                ex.printStackTrace();
                resp.sendRedirect("error?msg="+ex.getMessage());
            }

            userDaoInter.updateUser(user);

        }else if(strAction.equals("delete")){
            userDaoInter.removeUser(id);
        }
        resp.sendRedirect("users");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String strId=request.getParameter("id");
            if(strId==null || strId.trim().isEmpty()){
                throw new IllegalArgumentException("id is not specified");
            }
            UserDaoInter userDaoInter= Context.userDaoInstance();

            Integer id=Integer.parseInt(strId);
            User user=userDaoInter.getById(id);
            if(user==null){
                throw new IllegalArgumentException("There is no user with this id");
            }
            request.setAttribute("owner",true);
            request.setAttribute("user",user);
        request.getRequestDispatcher("userDetail.jsp").forward(request,response);
        }catch (IOException ex){
            ex.printStackTrace();
            response.sendRedirect("error?msg="+ex.getMessage());
        }
    }

    }
