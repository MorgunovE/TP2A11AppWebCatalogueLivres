<%-- 
    Document   : books
    Created on : Dec 25, 2024, 10:13:54 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Books</title>
    <link rel="stylesheet" href="/styles.css">
    <script src="scripts/scriptConfirmDeleteLivre.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>Books</h1>
    <!-- Add book boutton -->
    <a href="addLivre.jsp" class="button">Add book</a>

    <!-- Search book form -->
    <form method="GET" action="books">
        <input type="text" name="search" placeholder="Search by id">
        <button type="submit">Find book</button>
    </form>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
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
                    <a href="ModifierLivreServlet?id=${livre.id}">Modifier</a>
                    <a href="#" onclick="scriptConfirmDeleteLivre(${livre.id})">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>

