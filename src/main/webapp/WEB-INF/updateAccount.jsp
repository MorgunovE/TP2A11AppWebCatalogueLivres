<%-- 
    Document   : editAccount
    Created on : Jan 2, 2025, 1:31:25 PM
    Author     : user
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="accountUpdateAccount.title"/></title>
    </head>
    <body>       
        <form method="POST" action="UpdateAccountServlet">
            <p>
                <strong><label for="name"><fmt:message key="account.name"/>: </label></strong>
                <input class="input-correction" type="text" id="name" name="name" value="${user.name}" required> 
            </p>
            <p>
                <strong><label for="familyName"><fmt:message key="account.familyName"/>: </label></strong>
                <input class="input-correction" type="text" id="familyName" name="familyName" value="${user.familyName}" required>
            </p>
            <p>
                <strong><label for="tel"><fmt:message key="account.tel"/>: </label></strong>
                <input class="input-correction" type="tel" id="tel" name="tel" value="${user.tel}" required>
            </p>
            <p>
                <strong><label for="email"><fmt:message key="account.email"/>: </label></strong>
                <input class="input-correction" type="email" id="email" name="email" value="${user.email}" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
            </p>
            <p>
                <input type="hidden" name="userId" value="${userId}">
            </p>
            <p>
                <button type="submit" name="action" value="update">
                    <fmt:message key="account.updateButton"/>
                </button>
            </p>                         
        </form>
    </body>
</html>
</fmt:bundle>