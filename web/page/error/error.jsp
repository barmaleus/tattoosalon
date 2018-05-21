<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<html>
<head>
    <title>Error Page</title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/css/prime.css" rel="stylesheet">
</head>
<body>
<div id="error-data">
<dev>
    <fmt:message key="error.request1" bundle="${lang}"/> ${pageContext.errorData.requestURI} <fmt:message key="error.request2" bundle="${lang}"/>
    <br>
    <fmt:message key="error.servlet" bundle="${lang}"/>: ${pageContext.errorData.servletName}
    <br>
    <fmt:message key="error.status" bundle="${lang}"/>: ${pageContext.errorData.statusCode}
    <br>
    <fmt:message key="error.exception" bundle="${lang}"/>: ${pageContext.errorData.throwable}
    <br>
    <fmt:message key="error.message" bundle="${lang}"/>: ${pageContext.exception.message}
    <br>
    ${someErrorMessage}
    <br>
    <br>
    <input type=button value="<fmt:message key="error.back" bundle="${lang}"/>" onClick="history.back();">
</dev>
<br>
<img id="error-img" src="${pageContext.request.contextPath}/image/error/error633-471.jpg">
</div>
</body>
</html>