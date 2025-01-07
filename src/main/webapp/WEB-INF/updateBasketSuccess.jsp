<%-- 
    Document   : updateBasketSuccess
    Created on : Jan 7, 2025, 12:06:04 PM
    Author     : Evgenii Morgunov
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <title><fmt:message key="updateBasketSuccess.title"/></title>
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
                <form method="get" action="UpdateBasketSuccessServlet">
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
        <h1><fmt:message key="updateBasketSuccess.title"/></h1>
        <section class="section-block white-bg">
            <div class="text-block text-block-card">
                <h3><fmt:message key="updateBasketSuccess.message"/></h3>
                <div class="btn-basket">
                    <a class="btn" href="CheckoutServlet?locale=<%= locale %>"><fmt:message key="updateBasketSuccess.buttonCheckout"/></a>
                    <a class="btn" href="CatalogServlet?locale=<%= locale %>"><fmt:message key="updateBasketSuccess.buttonCatalog"/></a>
                    <a class="btn" href="AccountServlet?locale=<%= locale %>"><fmt:message key="updateBasketSuccess.buttonAccount"/></a>
                </div>
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
