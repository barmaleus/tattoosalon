<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<html>
<head>
    <title><fmt:message key="uploader.title" bundle="${lang}"/></title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/nav_and_header.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/css/publication.css" rel="stylesheet">
</head>
<body>
<%@include file="../WEB-INF/jspf/header.jspf"%>
<%@include file="../WEB-INF/jspf/nav.jspf"%>
<section>
    <div class="center">
        <h1><fmt:message key="uploader.file.name" bundle="${lang}"/></h1>
        <form method="post" action="uploader" enctype="multipart/form-data">
                <br>
            <fmt:message key="uploader.file.desc" bundle="${lang}"/>:
                <input type="file" name="file" size="60" />
                <br>
                ${fileTypeError}
                <br>
                <span class="req">* </span> <fmt:message key="uploader.image.name" bundle="${lang}"/>:
                <input required type="text" name="title" onkeyup="validate_add_publication(this)" placeholder="<fmt:message key="uploader.image.placeholder" bundle="${lang}"/>" size="20" />
            <br>
            <br>
            <input type="submit" value="<fmt:message key="uploader.button.ok" bundle="${lang}"/>" />
        </form>
    </div>
</section>
</body>
</html>
