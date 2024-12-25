<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12/25/2024
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Book Page</title>
</head>
<body>
    <h1>Add Book</h1>
    <form method="POST" action="AddLivreServlet">
        <label for="title">Name:</label>
        <input type="text" id="title" name="title" required>
        <br>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required>
        <br>
        <label for="author">Author:</label>
        <input type="text" id="author" name="author" required>
        <br>
        <label for="genre">Genre:</label>
        <input type="text" id="genre" name="genre" required>
        <br>
        <label for="image">Image:</label>
        <input type="text" id="image" name="image" required>
        <br>
        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" required>
        <br>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required>
        <br>
        <button type="submit">Sauvegarder</button>
    </form>
</body>
</html>
