<%@ page import="com.company.dao.inter.UserDaoInter" %>
<%@ page import="com.company.main.Context" %>
<%@ page import="com.company.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.resume.UserRequestUtil" %>
<%@ page import="com.company.resume.controller.UserController" %>
<%@ page import="com.company.dao.inter.CountryDaoInter" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/20/2020
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="assets/css/users.css">
<script src="https://kit.fontawesome.com/eead799839.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="assets/js/users.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<head>
    <title>Users</title>
</head>
<body>
<%
     List<User> userList=(List<User>) request.getAttribute("userList");
%>
<div class="container mycontainer">

    <div>
    <form action="users" method="GET">
       <div class="form-group">
           <label>Name:</label>
           <input placeholder="Enter name" class="form-control" type="text" name="name" value=""/>
       </div>

        <div class="form-group">
            <label>Surname:</label>
            <input placeholder="Enter surname" class="form-control" type="text" name="surname" value=""/>
        </div>

        <input class="btn btn-primary" type="submit" name="search" value="Search"/>
    </form>
</div>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Nationality</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
                CountryDaoInter countryDaoInter=Context.countryDaoInstance();
                for (User u: userList) {
            %>
            <tr >
                <td ><%=u.getName()%></td >
                <td ><%=u.getSurname()%></td >
                <td><%=countryDaoInter.getById(u.getNationality().getId()).getNationality()==null?"N/A":countryDaoInter.getById(u.getNationality().getId()).getNationality()%></td>
                <td style="padding-left: 5px">
                        <input type="hidden" name="id" value="<%=u.getId()%>">
                        <input type="hidden" name="action" value="delete">
                        <button class="btn btn-danger" type="submit" value="delete"
                                data-toggle="modal" data-target="#exampleModal"
                        onclick="setIdForDelete(<%=u.getId()%>)">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                </td>
                <td >
                    <form action="userdetail" method="GET">
                        <input type="hidden" name="id" value="<%=u.getId()%>">
                        <input type="hidden" name="action" value="update">
                        <button class="btn btn-secondary" type="submit" value="update">
                            <i class="fas fa-edit"></i>
                        </button>
                    </form>
                </td>
            </tr >
               <% } %>
            </tbody>
        </table>

    </div>

</div>



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure?
            </div>
            <div class="modal-footer">
                <form action="userdetail" method="POST">
                    <input type="hidden" name="id" value="" id="idForDelete"/>
                    <input type="hidden" name="action" value="delete">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-danger" value="Delete">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
