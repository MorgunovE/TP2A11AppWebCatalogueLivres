<%--
    Document   : bookAlreadyInBasket
    Created on : Dec 28, 2024, 1:55:25 PM
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><fmt:message key="bookAlreadyInBasket.title"/></title>
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
                <form method="get" action="BookAlreadyInBasketServlet">
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
        <h1><fmt:message key="bookAlreadyInBasket.title"/></h1>
        <section class="section-block white-bg">
            <div class="text-block text-block-card">
                <h3><fmt:message key="bookAlreadyInBasket.message"/></h3>
                <a class="btn" href="CatalogServlet?locale=<%= locale %>"><fmt:message key="bookAlreadyInBasket.buttonCatalog"/></a>
            </div>
        </section>
        <section class="account-block grey-bg">
            <div class="text-block">
                <h3><fmt:message key="accountPortal.basketInfo"/></h3>
                <c:if test="${sessionScope.livres == null}">
                    <p><fmt:message key="accountPortal.noBaskets"/></p>
                </c:if>
                <c:if test="${sessionScope.livres != null}">
                    <h4><fmt:message key="accountPortal.basketId"/>: ${sessionScope.basketId}</h4>
                    <div class="card-container">
                        <c:forEach var="livre" items="${sessionScope.livres}">
                            <div class="card">
                                <p>
                                    <strong><fmt:message key="accountPortal.bookTitle"/>:</strong>
                                        ${livre.title}
                                </p>
                                <p>
                                    <strong><fmt:message key="accountPortal.bookAuthor"/>:</strong>
                                        ${livre.author}
                                </p>
                                <p>
                                    <strong><fmt:message key="accountPortal.bookGenre"/>:</strong>
                                        ${livre.genre}
                                </p>
                                <p>
                                    <strong><fmt:message key="accountPortal.bookPrice"/>:</strong>
                                        ${livre.price}
                                </p>
                                <a class="btn" href="UpdateBasketServlet?basketId=${sessionScope.basketId}&bookId=${livre.id}&locale=<%= locale %>">
                                    <fmt:message key="accountPortal.buttonUpdateBasket"/>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
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