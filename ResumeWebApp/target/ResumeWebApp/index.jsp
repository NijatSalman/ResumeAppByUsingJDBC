<%@ page import="com.company.dao.inter.UserDaoInter" %>
<%@ page import="com.company.main.Context" %>
<html>
<body>
<%
    UserDaoInter userDaoInter= Context.userDaoInstance();
%>

<h2>Hello World! <%=userDaoInter.getById(7)%></h2>
</body>
</html>
