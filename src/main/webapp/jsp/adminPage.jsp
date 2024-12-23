<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12/23/2024
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            </tr>
    </thead>
    <tbody>
        <c:forEach items="${livres}" var="livre">
            <tr>
                <td>${livre.id}</td>
                <td>${livre.title}</td>
                <td>${livre.author}</td>
                <td>${livre.price}</td>
                <td>
                    <a href="/editLivre?id=${livre.id}">Modifier</a>
                    <a href="/deleteLivre?id=${livre.id}">Supprimer</a>
                    <script src="scriptConfirmDelete.js"></script>
                </td>
                </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
