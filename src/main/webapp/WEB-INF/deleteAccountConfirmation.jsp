<%-- 
    Document   : deleteAccount
    Created on : Jan 2, 2025, 2:35:41 PM
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
<html lang="en">
  <fmt:setLocale value="${param.locale != null ? param.locale : 'en_US'}"/>
  <fmt:bundle basename="ressources_i18n.Messages_${param.locale != null ? param.locale : 'en_US'}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Confirm Page</title>
    </head>
    <body>
        <form method="POST" action="DeleteAccountServlet">
            <input type="hidden" name="id" value="${userId}">
            <p>Are you sure?</p>
            <button type="submit">Delete</button>
            <a href="account.jsp">Cancel</a>
        </form>
    </body>
   </fmt:bundle>
</html>
