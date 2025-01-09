<%--
    Document   : adminPortal
    Created on : Dec 26, 2024, 11:25:34 AM
    Author     : Evgenii Morgunov
--%>

<%@ page import="control.LocaleUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
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
    <title><fmt:message key="adminPortal.title"/></title>
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
                <form method="get" action="AdminPortalServlet">
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
        <h1><fmt:message key="main.portalTitle"/></h1>
        <section  class="catalog-block catalog-block-main white-bg">
            <div class="text-block">
                <h3><fmt:message key="main.portalMessage"/></h3>
            </div>
        </section>
        <section class="catalog-block catalog-block-form grey-bg">
            <div class="create-form">
                <div class="card">
                    <h3><fmt:message key="book.addBook"/></h3>
                    <form id="addBookForm" action="AdminPortalServlet" method="post">
                        <input type="hidden" name="locale" value="<%= locale %>"/>
                        <label for="title" class="center-label"><fmt:message key="book.title"/></label>
                        <input type="text" id="title" name="title" required class="center-input input-correction">

                        <label for="author" class="center-label"><fmt:message key="book.author"/></label>
                        <input type="text" id="author" name="author" required class="center-input input-correction">

                        <label for="genre" class="center-label"><fmt:message key="book.genre"/></label>
                        <input type="text" id="genre" name="genre" required class="center-input input-correction">

                        <label for="description" class="center-label"><fmt:message key="book.description"/></label>
                        <textarea id="description" name="description" required class="center-input input-correction"></textarea>

                        <label for="price" class="center-label"><fmt:message key="book.price"/></label>
                        <input type="number" id="price" name="price" min="0" step="0.01" required class="center-input input-correction">

                        <label for="quantity" class="center-label"><fmt:message key="book.quantity"/></label>
                        <input type="number" id="quantity" name="quantity" min="0" required class="center-input input-correction">

                        <label for="image" class="center-label"><fmt:message key="book.image"/></label>
                        <input type="text" id="image" name="image" required class="center-input input-correction">

                        <div class="center-button">
                            <button type="submit" name="action" value="addBook"><fmt:message key="book.addButton"/></button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="filter-form">
                <div class="card">
                    <form action="AdminPortalServlet" method="post">
                        <input type="hidden" name="locale" value="<%= locale %>"/>
                        <label for="filterType" class="center-label"><fmt:message key="main.filterBy"/></label>
                        <select id="filterType" name="filterType">
                            <option value="title"><fmt:message key="main.titleOption"/></option>
                            <option value="author"><fmt:message key="main.authorOption"/></option>
                            <option value="genre"><fmt:message key="main.genreOption"/></option>
                            <option value="price"><fmt:message key="main.priceOption"/></option>
                        </select>
                        <input type="text" name="filterValue" placeholder="Enter filter value" class="center-input input-correction">
                        <div class="center-button">
                            <button type="submit" name="action" value="filter"><fmt:message key="main.filterButton"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <section class="catalog-block grey-bg">
            <div class="catalog-items">
                <c:forEach var="livre" items="${livres}">
                    <div class="card">
                        <img src="images/livre/${livre.image}" alt="${livre.title}">
                        <p><strong>${livre.title}</strong></p>
                        <p>${livre.author}</p>
                        <p>${livre.genre}</p>
                        <p>${livre.price} USD</p>
                        <p><fmt:message key="main.quantity"/>: ${livre.quantity}</p>
                        <div class="btn-admin-portal">
                            <a class="btn" href="UpdateBookServlet?id=${livre.id}&locale=<%= locale %>"><fmt:message key="adminPortal.buttonUpdate"/></a>
                            <a class="btn" href="DeleteBookServlet?id=${livre.id}&locale=<%= locale %>"><fmt:message key="adminPortal.buttonDelete"/></a>
                        </div>
                    </div>
                </c:forEach>
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
