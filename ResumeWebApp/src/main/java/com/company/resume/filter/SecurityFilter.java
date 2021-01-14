package com.company.resume.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"*"})
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        if(!request.getRequestURI().contains("/login") && request.getSession().getAttribute("loggedInUser")==null){
              response.sendRedirect("login");
        }else{
            filterChain.doFilter(request,response);
        }
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
