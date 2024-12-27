<%-- 
    Document   : catalog
    Created on : Dec 25, 2024, 6:02:57 PM
    Author     : Evgenii Morgunov
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userName = (String) session.getAttribute("userName");
    String locale = request.getParameter("locale");
%>
<!DOCTYPE html>
<fmt:setLocale value="${param.locale != null ? param.locale : 'en_US'}"/>
<fmt:bundle basename="ressources_i18n.Messages_${param.locale != null
? param.locale : 'en_US'}">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="title.BookCatalog"/></title>
    <link rel="stylesheet" href="styles/styleCatalogPage.css">
</head>
<body>

<header>
    <div class="container-menu">
        <div class="menu-bar">
            <a href="/TP2A11AppWebCatalogueLivres/index.html"><img src="images/book_logo.png"
                                         alt="Book Catalog Logo"></a>
            <span class="menu-toggle">Menu</span>
            <nav class="menu-nav">
                <ul>
                    <li><a href="CatalogServlet?locale=<%= locale %>"><fmt:message key="header.catalog"/></a></li>
                    <li><a href=""><fmt:message key="header.account"/></a></li>
                    <li><a href=""><fmt:message key="header.checkout"/></a></li>
                    <li><a href="AdminServlet?locale=<%= locale %>"><fmt:message key="header.administration"/></a></li>
                </ul>
            </nav>
            <form method="get" action="CatalogServlet">
                <button type="submit" name="locale" value="en_US" class="locale-btn">EN</button>
                <button type="submit" name="locale" value="fr_FR" class="locale-btn">FR</button>
            </form>
        </div>
        <nav class="menu-nav-mobile">
            <ul>
                <li><a href="CatalogServlet?locale=<%= locale %>"><fmt:message key="header.catalog"/></a></li>
                <li><a href=""><fmt:message key="header.account"/></a></li>
                <li><a href=""><fmt:message key="header.checkout"/></a></li>
                <li><a href="AdminServlet?locale=<%= locale %>"><fmt:message key="header.administration"/></a></li>
            </ul>
        </nav>
    </div>
</header>

    <main>
    <h1 id="catalog"><fmt:message key="main.title"/> <%= userName != null ? userName : "" %>!</h1>
        <section id="text-block" class="catalog-block white-bg">
            <img src="images/catalog.jpg" alt="Catalog">
            <div class="text-block">
                <h3><fmt:message key="main.explore"/></h3>
                <p><fmt:message key="main.discover"/></p>
            </div>
        </section>
        <section id="catalogSection" class="catalog-block grey-bg">
            <div class="filter-form">
                <div class="card">
                    <form action="CatalogServlet" method="post">
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
        <section id="catalog-items" class="catalog-block white-bg">
            <div class="catalog-items">
                <c:forEach var="livre" items="${livres}">
                    <div class="card">
                        <img src="images/livre/${livre.image}" alt="${livre.title}">
                        <p><strong>${livre.title}</strong></p>
                        <p>${livre.author}</p>
                        <p>${livre.genre}</p>
                        <p>${livre.price} USD</p>
                        <p><fmt:message key="main.quantity"/>: ${livre.quantity}</p>
                        <button class="btn" onclick="addToCart(${livre.id})"><fmt:message key="main.addToCart"/></button>
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
    <script src="scripts/scriptCatalogPage.js"></script>
</body>
</html>