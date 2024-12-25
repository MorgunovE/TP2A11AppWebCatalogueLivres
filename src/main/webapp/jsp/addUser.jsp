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
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add User Page</title>
</head>
<body>
    <h1>Add User</h1>
    <form method="POST" action="AddUserServlet">
        <label for="name">Firstname:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="familyName">Lastname:</label>
        <input type="text" id="familyName" name="familyName" required>
        <br>
        <label for="tel">Phone:</label>
        <input type="text" id="tel" name="tel" required>
        <br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <button type="submit">Sauvegarder</button>
    </form>
</body>
</html>
