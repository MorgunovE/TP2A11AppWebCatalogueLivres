<%-- 
    Document   : deleteAccountConfirmation
    Created on : Jan 2, 2025, 2:35:41 PM
    Author     : Rustam Zholdubayev
--%>

<%@ page import="control.LocaleUtil" %>
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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="accountConfirmDeleteAccount.title"/></title>
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
                <form method="get" action="DeleteAccountServlet">
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
        <h1><fmt:message key="accountDeleteConfirm.title"/></h1>
        <section class="account-block white-bg">
            <div class="text-block text-block-correct">
                <h3><fmt:message key="accountDeleteConfirm.message"/></h3>
                <form method="POST" action="DeleteAccountServlet">
                    <input type="hidden" name="locale" value="${locale}">
                    <button type="submit"><fmt:message key="accountDeleteConfirm.deleteAccountButton"/></button>
                </form><br>
                <form method="GET" action="AccountPortalServlet?locale=<%= locale %>">
                    <input type="hidden" name="locale" value="${locale}">
                    <button type="submit"><fmt:message key="accountDeleteConfirm.cancelButton"/></button>
                </form>
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
