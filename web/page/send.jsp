<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<html>
<head>
    <title><fmt:message key="send.title" bundle="${lang}"/></title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/nav_and_header.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/css/publication.css" rel="stylesheet">
</head>
<body>
<%@include file="../WEB-INF/jspf/header.jspf"%>
<%@include file="../WEB-INF/jspf/nav.jspf"%>
<section>
<p>Sending in progress...</p>
<a href="controller?command=mail"><fmt:message key="send.button.return" bundle="${lang}"/></a>
    <br>
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="main" />
        <input type="hidden" name="page" value="0" />
        <input type="submit" value="<fmt:message key="send.button.main" bundle="${lang}"/>" />
    </form>
</section>
</body>
</html>

