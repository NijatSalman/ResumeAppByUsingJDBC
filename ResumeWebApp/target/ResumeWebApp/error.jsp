<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/26/2020
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Why are you here?</title>
</head>
<body>
<%
        String msg=request.getParameter("msg");
%>
    <%=msg%>
</body>
</html>
