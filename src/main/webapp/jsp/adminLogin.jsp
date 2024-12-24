<%-- 
    Document   : adminLogin
    Created on : Dec 23, 2024, 11:16:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Login Page</title>
    </head>
    <body>
        <form action="AdminLoginServlet" method="POST">
            <label for="username">Username:</label>
            <input type="username" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Enter</button>
          </form>
    </body>
</html>
