<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<html>
<head><title><fmt:message key="created.user.title" bundle="${lang}"/></title></head>
<body>
<p><fmt:message key="created.user.message" bundle="${lang}"/></p>
<a href="/page/login.jsp"><fmt:message key="created.user.back" bundle="${lang}"/></a>
</body>
</html>


