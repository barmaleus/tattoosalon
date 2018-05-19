<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<html>
<head>
    <title>Tattoo Salon</title>
    <script src="${pageContext.request.contextPath}/js/prime.js"></script>
    <link type="text/css" href="${pageContext.request.contextPath}/css/nav_and_header.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/css/publication.css" rel="stylesheet">
</head>
    <body>
    <%@include file="../WEB-INF/jspf/header.jspf"%>
    <%@include file="../WEB-INF/jspf/nav.jspf"%>
    <jsp:include page="../WEB-INF/jspf/${param.command}.jsp" />
    </body>
</html>
