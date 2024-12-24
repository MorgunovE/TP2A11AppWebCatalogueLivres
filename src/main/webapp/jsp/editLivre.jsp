<%-- 
    Document   : editLivre
    Created on : Dec 23, 2024, 4:11:44 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EditLivrePage</title>
    </head>
    <body>
        <form action="/editLivre" method="POST">
            <input type="hidden" name="title" value="${livre.title}">
            <input type="hidden" name="description" value="${livre.description}">
            <input type="hidden" name="author" value="${livre.author}">
            <input type="hidden" name="genre" value="${livre.genre}">
            <input type="hidden" name="image" value="${livre.image}">
            <input type="hidden" name="price" value="${livre.price}">
            <input type="hidden" name="quantity" value="${livre.quantity}">
            <button type="submit">Sauvegarder</button>
        </form>
    </body>
</html>
