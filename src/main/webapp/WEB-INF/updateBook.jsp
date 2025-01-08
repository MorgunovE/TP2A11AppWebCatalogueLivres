<%--
    Document   : updateBook
    Created on : Jan 7, 2025, 7:48:25 PM
    Author     : Evgenii Morgunov
--%>

<%@ page import="control.LocaleUtil" %>
<%@ page import="model.Livre" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Livre livre = (Livre) request.getAttribute("livre");
    String locale = LocaleUtil.setLocaleAttributes(request);
%>
<!DOCTYPE html>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="ressources_i18n.Messages_${locale}">
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <title><fmt:message key="updateBook.title"/></title>
        <link rel="stylesheet" href="styles/styleJspPage.css">
    </head>
    <body>

    <header>
        <div class="container-menu">
            <div class="menu-bar">
                <a href="/TP2A11AppWebCatalogueLivres/<%= "fr_FR"
            .equals(locale) ? "html/indexFR" : "index" %>.html"><img
                        src="images/book_logo.png"
                        alt="Book Catalog Logo"></a>
                <span class="menu-toggle">Menu</span>
                <nav class="menu-nav">
                    <ul>
                        <li><a href="CatalogServlet?locale=<%= locale %>"><fmt:message key="header.catalog"/></a></li>
                        <li><a href="AccountServlet?locale=<%= locale %>"><fmt:message key="header.account"/></a></li>
                        <li><a href="CheckoutServlet?locale=<%= locale %>"><fmt:message key="header.checkout"/></a></li>
                        <li><a href="AdminServlet?locale=<%= locale %>"><fmt:message key="header.administration"/></a></li>
                    </ul>
                </nav>
                <form method="get" action="UpdateBookServlet">
                    <button type="submit" name="locale" value="en_US" class="locale-btn">EN</button>
                    <button type="submit" name="locale" value="fr_FR" class="locale-btn">FR</button>
                </form>
            </div>
            <nav class="menu-nav-mobile">
                <ul>
                    <li><a href="CatalogServlet?locale=<%= locale %>"><fmt:message key="header.catalog"/></a></li>
                    <li><a href="AccountServlet?locale=<%= locale %>"><fmt:message key="header.account"/></a></li>
                    <li><a href="CheckoutServlet?locale=<%= locale %>"><fmt:message key="header.checkout"/></a></li>
                    <li><a href="AdminServlet?locale=<%= locale %>"><fmt:message key="header.administration"/></a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main>
        <h1><fmt:message key="updateBook.title"/></h1>
        <section class="catalog-block catalog-block-form grey-bg">
            <div class="create-form">
                <div class="card">
                    <h3><fmt:message key="updateBook.message"/></h3>
                    <form id="updateBookForm" action="UpdateBookServlet" method="post">
                        <input type="hidden" name="id" value="<%= livre.getId() %>"/>
                        <input type="hidden" name="locale" value="<%= locale %>"/>
                        <label for="title" class="center-label"><fmt:message key="book.title"/></label>
                        <input type="text" id="title" name="title" value="<%= livre.getTitle() %>" required class="center-input input-correction">

                        <label for="author" class="center-label"><fmt:message key="book.author"/></label>
                        <input type="text" id="author" name="author" value="<%= livre.getAuthor() %>" required class="center-input input-correction">

                        <label for="genre" class="center-label"><fmt:message key="book.genre"/></label>
                        <input type="text" id="genre" name="genre" value="<%= livre.getGenre() %>" required class="center-input input-correction">

                        <label for="description" class="center-label"><fmt:message key="book.description"/></label>
                        <textarea id="description" name="description" required class="center-input input-correction"><%= livre.getDescription() %></textarea>

                        <label for="price" class="center-label"><fmt:message key="book.price"/></label>
                        <input type="number" id="price" name="price" value="<%= livre.getPrice() %>" min="0" step="0.01" required class="center-input input-correction">

                        <label for="quantity" class="center-label"><fmt:message key="book.quantity"/></label>
                        <input type="number" id="quantity" name="quantity" value="<%= livre.getQuantity() %>" min="0" required class="center-input input-correction">

                        <label for="image" class="center-label"><fmt:message key="book.image"/></label>
                        <input type="text" id="image" name="image" value="<%= livre.getImage() %>" required class="center-input input-correction">

                        <div class="center-button">
                            <button type="submit" name="action" value="updateBook"><fmt:message key="updateBook.button"/></button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="text-block">
                <h3><fmt:message key="updateBook.returnMessage"/></h3>
                <a class="btn" href="AdminPortalServlet?locale=<%= locale %>"><fmt:message key="updateBook.returnButton"/></a>
            </div>
        </section>
    </main>

    <footer>
        <p><fmt:message key="footer.copyright"/> <strong><fmt:message key="footer.year"/></strong>
            <fmt:message key="footer.message"/></p>
    </footer>

</fmt:bundle>
<script src="scripts/scriptJspPage.js"></script>
</body>
</html>

