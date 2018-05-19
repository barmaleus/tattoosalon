<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.login" var="lang"/>
<html lang="${language}">
<head>
    <title>Tattoo Salon :: Login Page</title>
    <script src="${pageContext.request.contextPath}/js/prime.js"></script>
    <link type="text/css" href="${pageContext.request.contextPath}/css/style_login.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet">
</head>
<body>

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
</form>

<div class="container">

<br>
<h2><fmt:message key="message.welcome" bundle="${lang}"/></h2>
<br>
<br>
<br>
<br>
<form name="loginform" action="controller" method="POST" id="loginform">
    <input type="hidden" name="command" value="login" />
    <label>
        <fmt:message key="field.name" bundle="${lang}"/>
        <br>
        <input type="text" name="login" onkeyup = "Validate(this)" value="" size="20" />
    </label>
    <br>
    <label>
        <fmt:message key="field.password" bundle="${lang}"/>
        <br>
        <input type="password" name="password" size="20" />
    </label>

    <br>
    ${errorLoginPassMessage}
    <br>
    ${wrongAction}
    <br>
    ${nullPage}
    <br>
    <fmt:message key="button.submit" var="loginButtonValue" bundle="${lang}"/>
    <input type="submit" name="login-button" value="${loginButtonValue}" />

</form>

<form name="registerform" action="controller" method="POST" id="registerform">
    <input type="hidden" name="command" value="to_registration_page" />
    <fmt:message key="button.register" var="registerButtonValue" bundle="${lang}"/>
    <input type="submit" name="log-register-button" value="${registerButtonValue}" />
</form>

</div><!-- end .container -->

<footer class="footer">
    <fmt:message key="login.footer" bundle="${lang}"/>
</footer>

</body>
</html>
