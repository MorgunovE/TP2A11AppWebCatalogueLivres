<%-- 
    Document   : errorAdmin
    Created on : Dec 26, 2024, 11:25:00 AM
    Author     : Evgenii Morgunov
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String locale = request.getParameter("locale");
    if (locale == null) {
        locale = "en_US";
    }
%>
<!DOCTYPE html>
<fmt:setLocale value="${param.locale != null ? param.locale : 'en_US'}"/>
<fmt:bundle
        basename="ressources_i18n.Messages_${param.locale != null
        ? param.locale : 'en_US'}">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="adminError.title"/></title>
    <link rel="stylesheet" href="styles/styleAdminErrorPage.css">
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
                        <li><a href="CatalogServlet"><fmt:message key="header.catalog"/></a></li>
                        <li><a href=""><fmt:message key="header.account"/></a></li>
                        <li><a href=""><fmt:message key="header.checkout"/></a></li>
                        <li><a href="AdminServlet"><fmt:message key="header.administration"/></a></li>
                    </ul>
                </nav>
                <form method="get" action="AdminErrorServlet">
                    <button type="submit" name="locale" value="en_US" class="locale-btn">EN</button>
                    <button type="submit" name="locale" value="fr_FR" class="locale-btn">FR</button>
                </form>
            </div>
            <nav class="menu-nav-mobile">
                <ul>
                    <li><a href="CatalogServlet"><fmt:message key="header.catalog"/></a></li>
                    <li><a href=""><fmt:message key="header.account"/></a></li>
                    <li><a href=""><fmt:message key="header.checkout"/></a></li>
                    <li><a href="AdminServlet"><fmt:message key="header.administration"/></a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main>
        <h1><fmt:message key="main.errorTitle"/></h1>
        <section class="catalog-block white-bg">
            <div class="text-block">
                <h3><fmt:message key="main.errorMessage"/></h3>
                <a class="btn" href="AdminServlet"><fmt:message key="adminErrorButton"/></a>
            </div>
        </section>
    </main>

    <footer>
        <p><fmt:message key="footer.copyright"/> <strong><fmt:message key="footer.year"/></strong>
            <fmt:message key="footer.message"/></p>
    </footer>

</fmt:bundle>
<script src="scripts/scriptAdminErrorPage.js"></script>
</body>
</html>