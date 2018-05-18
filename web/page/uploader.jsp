<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<html>
<head>
    <title>Image Uploader</title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/nav_and_header.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/css/publication.css" rel="stylesheet">
</head>
<body>
<%@include file="../WEB-INF/jspf/header.jspf"%>
<%@include file="../WEB-INF/jspf/nav.jspf"%>
<section>
    <div class="center">
        <h1>File Upload</h1>
        <form method="post" action="uploader" enctype="multipart/form-data">
                <br>
                Select file to upload (max size 2 Mb):
                <input type="file" name="file" size="60" />
                <br>
                ${fileTypeError}
                <br>
                <span class="req">* </span> Image title:
                <input required type="text" name="title" placeholder="Write photo title here" size="20" />
            <br>
            <br>
            <input type="submit" value="Upload" />
        </form>
    </div>
</section>
</body>
</html>
