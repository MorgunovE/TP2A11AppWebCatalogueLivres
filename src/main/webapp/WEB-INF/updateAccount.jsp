<%-- 
    Document   : updateAccount
    Created on : Jan 14, 2025, 3:25:01 PM
    Author     : Evgenii Morgunov
--%>

<%@ page import="control.LocaleUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String locale = LocaleUtil.setLocaleAttributes(request);
    model.User user = (model.User) request.getAttribute("user");
%>
<!DOCTYPE html>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="ressources_i18n.Messages_${locale}">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="updateAccount.title"/></title>
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
                <form method="get" action="UpdateAccountServlet">
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
        <h1><fmt:message key="updateAccount.title"/></h1>
        <section class="account-block grey-bg">
            <div class="account-form pb-20 pt-10">
                <div class="card">
                    <h3><fmt:message key="updateAccount.message"/></h3>
                    <form action="UpdateAccountServlet" method="post">
                        <input type="hidden" name="locale" value="<%= locale %>"/>
                        <label for="name"><fmt:message key="account.name"/></label>
                        <input class="input-correction" type="text" id="name" name="name" value="<%= user.getName() %>" required>
                        <label for="familyName"><fmt:message key="account.familyName"/></label>
                        <input class="input-correction" type="text" id="familyName" name="familyName" value="<%= user.getFamilyName() %>" required>
                        <label for="tel"><fmt:message key="account.tel"/></label>
                        <input class="input-correction" type="tel" id="tel" name="tel" value="<%= user.getTel() %>" required>
                        <label for="email"><fmt:message key="account.email"/></label>
                        <input class="input-correction" type="email" id="email" name="email" value="<%= user.getEmail() %>" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
                        <div class="center-button">
                            <button type="submit"><fmt:message key="updateAccount.button"/></button>
                        </div>
                    </form>
                </div>
                <div class="btn-basket">
                    <a class="btn" href="AccountPortalServlet?locale=<%= locale %>"><fmt:message key="updateAccount.returnButton"/></a>
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
