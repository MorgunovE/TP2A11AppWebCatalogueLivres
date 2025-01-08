<%--
    Document   : checkout
    Created on : Dec 29, 2024, 12:33:09 PM
    Author     : Evgenii Morgunov
--%>

<%@ page import="control.LocaleUtil" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String locale = LocaleUtil.setLocaleAttributes(request);
    String name = (String) session.getAttribute("name");
    String familyName = (String) session.getAttribute("familyName");
    Long userId = (Long) session.getAttribute("id");
    Long basketId = (Long) session.getAttribute("basketId");
%>
<!DOCTYPE html>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="ressources_i18n.Messages_${locale}">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="checkout.title"/></title>
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
                <form method="get" action="CheckoutServlet">
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
        <h1><fmt:message key="checkout.title"/></h1>
        <h3><fmt:message key="checkout.message"/></h3>
        <section class="checkout-block grey-bg">
            <div class="text-block">
                <div class="card">
                    <h3><fmt:message key="checkout.form"/></h3>
                    <c:if test="${sessionScope.livres == null}">
                        <p><fmt:message key="checkout.noBooks"/></p>
                    </c:if>
                    <c:if test="${sessionScope.livres != null}">
                        <h4><fmt:message key="checkout.basketId"/>: ${sessionScope.basketId}</h4>
                        <div class="card-container">
                            <c:forEach var="livre" items="${sessionScope.livres}">
                                <div class="card">
                                    <p><strong><fmt:message key="checkout.bookTitle"/>:</strong> ${livre.title}</p>
                                    <p><strong><fmt:message key="checkout.bookAuthor"/>:</strong> ${livre.author}</p>
                                    <p><strong><fmt:message key="checkout.bookGenre"/>:</strong> ${livre.genre}</p>
                                    <p><strong><fmt:message key="checkout.bookPrice"/>:</strong> ${livre.price}</p>
                                    <a class="btn" href="UpdateBasketServlet?basketId=${sessionScope.basketId}&bookId=${livre.id}&locale=<%= locale %>">
                                        <fmt:message key="checkout.buttonUpdateBasket"/>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                        <c:set var="totalPrice" value="0"/>
                        <c:forEach var="livre" items="${sessionScope.livres}">
                            <c:set var="totalPrice" value="${totalPrice + livre.price}"/>
                        </c:forEach>
                        <h3><strong><fmt:message key="checkout.totalPrice"/>:</strong> ${totalPrice}</h3>
                    </c:if>
                    <div class="btn-checkout-portal">
                        <a class="btn" href="CatalogServlet?locale=<%= locale %>"><fmt:message key="checkout.buttonReturnCatalog"/></a>
                        <a class="btn" href="AccountServlet?locale=<%= locale %>"><fmt:message key="checkout.buttonGoAccount"/></a>
                        <a class="btn" href="PrintCheckoutPdfServlet?locale=<%= locale %>"><fmt:message key="checkout.buttonPrintPdf"/></a>
                    </div>
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
