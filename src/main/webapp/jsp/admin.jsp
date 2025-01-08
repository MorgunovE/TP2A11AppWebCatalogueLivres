<%-- 
    Document   : admin
    Created on : Dec 26, 2024, 11:01:56 AM
    Author     : Evgenii Morgunov
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String locale = request.getParameter("locale");
    String language = request.getParameter("Language");
    if ("fr_FR".equals(locale)) {
        locale = "fr_FR";
    } else if ("en_US".equals(locale)) {
        locale = "en_US";
    } else if ("fr".equals(language)) {
        locale = "fr_FR";
    } else if (locale == null || locale.isEmpty()) {
        locale = "en_US";
    } else {
        locale = "en_US";
    }
%>
<!DOCTYPE html>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="ressources_i18n.Messages_${locale}">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="admin.title"/></title>
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
                <form method="get" action="AdminServlet">
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
        <section class="catalog-block white-bg">
            <div class="text-block">
                <h3><fmt:message key="main.adminMessageForm"/></h3>
                <p><fmt:message key="main.adminMessageFormText"/></p>
            </div>
        </section>
        <section class="catalog-block grey-bg">
            <div class="admin-form">
                <div class="card">
                    <form action="AdminServlet" method="post">
                        <input type="hidden" name="locale" value="<%= locale %>"/>
                        <label for="userName"><fmt:message key="main.userName"/></label>
                        <input class="input-correction" type="text" id="userName" name="userName" required>
                        <label for="password"><fmt:message key="main.password"/></label>
                        <input class="input-correction" type="password" id="password" name="password" required>
                        <button type="submit"><fmt:message key="main.loginButton"/></button>
                    </form>
                    <p><fmt:message key="main.adminMessageFormTextAfter"/></p>
                </div>
            </div>
        </section>
        <section class="catalog-block white-bg">
            <div class="text-block">
                <h3><fmt:message key="main.adminMessageFormParagrafAfter"/></h3>
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
