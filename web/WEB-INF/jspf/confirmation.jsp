<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<section>
    <h2>${requestScope.message}</h2>
    <br>
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="main" />
        <input type="hidden" name="page" value="0" />
        <input type="submit" value="<fmt:message key="confirmation.button.main" bundle="${lang}"/>" />
    </form>
</section>