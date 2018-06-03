<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.login" var="lang"/>
<html lang="${language}">
<head>
    <title><fmt:message key="reg.title" bundle="${lang}"/></title>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/prime.js"></script>
    <link type="text/css" href="${pageContext.request.contextPath}/css/registration.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet">
</head>
<body>
<section>
    <div class="container">
        <h2><fmt:message key="reg.form" bundle="${lang}"/></h2>
        <div class="row">
            <div class="col-md-6">
                <form action="" method="post" id="fileForm" role="form">
                    <input type="hidden" name="command" value="registration" />
                    <fieldset><legend class="text-center"><fmt:message key="reg.form.description" bundle="${lang}"/><span class="req"><small><fmt:message key="reg.required" bundle="${lang}"/></small></span></legend>

                        <div class="form-group">
                            <label for="login"><span class="req">* </span> <fmt:message key="field.name" bundle="${lang}"/>:  <small><fmt:message key="reg.login.desc" bundle="${lang}"/></small> </label>
                            <input class="form-control" type="text" name="reg-login" id = "login" minlength="4" maxlength="16" onkeyup = "Validate(this)" placeholder="<fmt:message key="reg.login.placeholder" bundle="${lang}"/>" required />
                            ${errorLoginExists}
                        </div>

                        <div class="form-group">
                            <label for="name"><span class="req">* </span> <fmt:message key="reg.name" bundle="${lang}"/>: </label>
                            <input class="form-control" type="text" name="reg-name" id = "name" minlength="2" maxlength="20" onkeyup = "validate_name(this)" placeholder="<fmt:message key="reg.name.placeholder" bundle="${lang}"/>" required />
                        </div>

                        <div class="form-group">
                            <label for="surname"><span class="req">* </span> <fmt:message key="reg.surname" bundle="${lang}"/>: </label>
                            <input class="form-control" type="text" name="reg-surname" id = "surname" minlength="2" maxlength="20" onkeyup = "validate_name(this)" placeholder="<fmt:message key="reg.surname.placeholder" bundle="${lang}"/>" required />
                        </div>

                        <div class="form-group">
                            <label for="pass1"><span class="req">* </span> <fmt:message key="field.password" bundle="${lang}"/>: </label>
                            <input required name="reg-password" type="password" class="form-control inputpass" minlength="4" maxlength="16"  id="pass1" /> </p>

                            <label for="pass2"><span class="req">* </span> <fmt:message key="reg.password2" bundle="${lang}"/>: </label>
                            <input required name="reg-password" type="password" class="form-control inputpass" minlength="4" maxlength="16" placeholder="<fmt:message key="reg.password2.placeholder" bundle="${lang}"/>"  id="pass2" onkeyup="checkPass(); return false;" />
                            <span id="confirmMessage" class="confirmMessage"></span>
                        </div>

                        <div class="form-group">
                            <label for="email"><span class="req">* </span> <fmt:message key="reg.email" bundle="${lang}"/>: </label>
                            <input class="form-control" required type="text" name="reg-email" id = "email"  onchange="email_validate(this.value);" />
                            <div class="status" id="status"></div>
                            <br>
                            ${errorEmailExists}
                        </div>

                        <div class="form-group">
                            <label><span class="req">* </span> <fmt:message key="reg.gender" bundle="${lang}"/></label>
                            <input type="radio" name="reg-sex" id="sex-m" value="true" checked>
                            <label for="sex-m"><fmt:message key="reg.gender.m" bundle="${lang}"/></label>
                            <input type="radio" name="reg-sex" id="sex-f" value="false">
                            <label for="sex-f"><fmt:message key="reg.gender.f" bundle="${lang}"/></label>
                        </div>

                        <div class="form-group">
                            <label><span class="req">* </span> <fmt:message key="reg.birth" bundle="${lang}"/>: <small><fmt:message key="reg.birth.desc" bundle="${lang}"/></small></label>
                            <input required type="date" id="birth" name="reg-birth" min="1900-01-01" max="2018-05-01" onfocus="maxdate_validate()">
                        </div>
                        <div class="form-group">
                            <input class="btn btn-success" type="submit" name="submit_reg" value="<fmt:message key="reg.button.ok" bundle="${lang}"/>">
                        </div>
                    </fieldset>
                </form><!-- ends register form -->
            </div><!-- ends col-6 -->
        </div>
    </div>
</section>
</body>
</html>