<%-- 
    Document   : editAccount
    Created on : Jan 2, 2025, 1:31:25 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account Page</title>
    </head>
    <body>       
        <form method="POST" action="UpdateAccountServlet">//rename editAccount.jsp to UpdateAccount
            <input type="text" name="name" value="${user.name}"> 
            <input type="text" name="familyName" value="${user.familyName}"> 
            <input type="text" name="tel" value="${user.tel}"> 
            <input type="text" name="email" value="${user.email}"> 
            <button type="submit">Save</button> 
        </form>
    </body>
</html>
