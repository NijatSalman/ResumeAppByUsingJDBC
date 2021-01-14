<%@ page import="com.company.dao.inter.UserDaoInter" %>
<%@ page import="com.company.main.Context" %>
<%@ page import="com.company.entity.User" %>
<%@ page import="com.company.resume.UserRequestUtil" %>
<%@ page import="com.company.entity.Country" %>
<%@ page import="com.company.dao.inter.CountryDaoInter" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/20/2020
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<head>
    <title>Title</title>
</head>
<body>

<%
    User user=null;
    CountryDaoInter countryDaoInter=Context.countryDaoInstance();
    List<Country> countryList=countryDaoInter.getAllCountry();
    try {
       user=(User) request.getAttribute("user");
    }catch (Exception ex){
        response.sendRedirect("error.jsp?msg="+ex.getMessage());
            return;
    }

%>

    <div style="width: 900px;height:100px;margin:15px 0px 0px 30px ;">
        <form action="userdetail" method="POST">
            <input type="hidden" name="id" value="<%=user.getId()%>"/>
            <input type="hidden" name="action" value="update"/>

           <div class="form-group row">
               <label class="col-2 col-form-label">Name</label>
              <div class="col-10">
                  <input type="text" class="form-control" name="name" value="<%=user.getName()%>"/>
              </div>
           </div>

            <div class="form-group row">
                <label class="col-2 col-form-label">Surname</label>
                <div class="col-10">
                    <input type="text" class="form-control" name="surname" value="<%=user.getSurname()%>"/>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-2 col-form-label">Address</label>
                <div class="col-10">
                    <input type="text" class="form-control" id="inputAddress" name="address" placeholder="Baku.Azerbaijan" value="<%=user.getAddress()%>">
                </div>
            </div>

            <div class="form-group row">
                <label class="col-2 col-form-label">Email</label>
                <div class="col-10">
                    <input type="email" class="form-control" name="email" id="inputEmail4" placeholder="Email" value="<%=user.getEmail()%>">
                </div>
            </div>

            <div class="form-group row">
                <label class="col-2 col-form-label">Phone-number</label>
                <div class="col-10">
                    <input class="form-control" type="tel" name="phone" value="<%=user.getPhone()%>" id="example-tel-input" >
                </div>
            </div>

            <div class="form-group row">
                <label for="example-date-input" class="col-2 col-form-label">Date</label>
                <div class="col-10">
                    <input class="form-control" type="date" name="date" value="<%=user.getBirthDate()%>" id="example-date-input">
                </div>
            </div>

            <div class="form-group row">
                <label class="col-2 col-form-label">Profile Description</label>
                <div class="col-10">
                    <textarea class="form-control" name="profileDescription"  id="exampleFormControlTextarea1" rows="5"><%=user.getProfileDescription()%></textarea>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-2 col-form-label">Nationality</label>
                <div class="col-10">
                    <select class="form-control" name="selectedNationality">
                        <%
                            Country nationality=countryDaoInter.getById(user.getNationality().getId());
                        %>
                        <option value="<%=nationality.getId()%>"><%=nationality.getNationality()%></option>
                        <%

                            for (Country nationalityObj: countryList) {
                                if(nationality.getId()!=nationalityObj.getId()){
                        %>
                        <option value="<%=nationalityObj.getId()%>"> <%=nationalityObj.getNationality()%> </option>
                        <%}
                        }%>
                        %>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-2 col-form-label">Country</label>
                <div class="col-10">
                    <select class="form-control" name="selectedCountry">
                        <%
                        Country country=countryDaoInter.getById(user.getBirthPlace().getId());
                        %>
                        <option value="<%=country.getId()%>"><%=country.getName()%></option>
                        <%

                        for (Country countryObj: countryList) {
                            if(country.getId()!=countryObj.getId()){
                                %>
                                <option value="<%=countryObj.getId()%>"> <%=countryObj.getName()%> </option>
                                 <%}
                        }%>
                        %>
                    </select>
                </div>
            </div>

            <input type="submit" name="save" value="Save"/>
        </form>
    </div>

</body>
</html>
