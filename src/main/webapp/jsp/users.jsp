<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12/24/2024
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <script src="scripts/confirmDeleteUserScript.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<form method="GET" action="users">
    <input type="text" name="search" placeholder="Search by id">
    <button type="submit">Find</button>
</form>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Phone</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.familyName}</td>
            <td>${user.tel}</td>
            <td>${user.email}</td>
            <td>
                <a href="EditUserServlet?id=${user.id}">Modifier</a>
                <a href="#" onclick="confirmDelete(${user.id})">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
