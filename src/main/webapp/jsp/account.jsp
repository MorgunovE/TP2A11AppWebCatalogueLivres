<%-- 
    Document   : account
    Created on : Dec 27, 2024, 1:52:34 PM
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="account.title"/></title>
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
                <form method="get" action="AccountServlet">
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
        <h1><fmt:message key="account.welcome"/></h1>
        <section class="account-block grey-bg">
            <h3><fmt:message key="account.loginByEmailMessage"/></h3>
            <div class="account-form">
                <div class="card">
                    <h3><fmt:message key="account.loginByEmail"/></h3>
                    <form action="AccountServlet" method="post">
                        <input type="hidden" name="locale" value="<%= locale %>"/>
                        <label for="email_user"><fmt:message key="account.email"/></label>
                        <input type="email" id="email_user" name="email_user" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
                        <button type="submit" name="action" value="findUsersByEmail"><fmt:message key="account.loginButton"/></button>
                    </form>
                    <p><fmt:message key="account.loginByEmailMessageText"/></p>
                </div>
            </div>
        </section>
        <section class="account-block grey-bg">
            <h3><fmt:message key="account.createAccountMessage"/></h3>
            <div class="account-form pb-20">
                <div class="card ">
                    <h3><fmt:message key="account.createAccount"/></h3>
                    <form action="AccountServlet" method="post">
                        <input class="input-correction" type="hidden" name="locale" value="<%= locale %>"/>
                        <label for="name"><fmt:message key="account.name"/></label>
                        <input class="input-correction" type="text" id="name" name="name" required>
                        <label for="familyName"><fmt:message key="account.familyName"/></label>
                        <input class="input-correction" type="text" id="familyName" name="familyName" required>
                        <label for="tel"><fmt:message key="account.tel"/></label>
                        <input class="input-correction" type="tel" id="tel" name="tel" required>
                        <label for="email"><fmt:message key="account.email"/></label>
                        <input class="input-correction" type="email" id="email" name="email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
                        <button type="submit" name="action" value="create"><fmt:message key="account.createButton"/></button>
                    </form>
                    <p><fmt:message key="account.createAccountMessageText"/></p>
                </div>
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
