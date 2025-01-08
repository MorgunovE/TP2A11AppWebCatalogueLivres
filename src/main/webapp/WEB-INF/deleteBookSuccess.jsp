<%-- 
    Document   : deleteBookSuccess
    Created on : Jan 7, 2025, 7:02:21 PM
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
<fmt:setLocale value="${param.locale != null ? param.locale : 'en_US'}"/>
<fmt:bundle basename="ressources_i18n.Messages_${param.locale != null ? param.locale : 'en_US'}">
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><fmt:message key="deleteBookSuccess.title"/></title>
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
                <form method="get" action="DeleteBookSuccessServlet">
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
        <h1><fmt:message key="deleteBookSuccess.title"/></h1>
        <section class="account-block white-bg">
            <div class="text-block text-block-correct">
                <h3><fmt:message key="deleteBookSuccess.message"/></h3>
                <a class="btn" href="AdminPortalServlet?locale=<%= locale %>"><fmt:message key="deleteBookSuccess.returnButton"/></a>
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
