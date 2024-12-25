<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12/24/2024
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="POST" action="EditUserServlet"> 
        <input type="hidden" name="id" value="${user.id}"> 
        <input type="text" name="name" value="${user.name}"> 
        <input type="text" name="familyName" value="${user.familyName}"> 
        <input type="text" name="tel" value="${user.tel}"> 
        <input type="text" name="email" value="${user.email}"> 
        <button type="submit">Sauvgarder</button> 
    </form>
</body>
</html>
