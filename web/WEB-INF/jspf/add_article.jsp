<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<section>
    <h2><fmt:message key="addarticle.name" bundle="${lang}"/></h2>
    <p><fmt:message key="addarticle.form.desc" bundle="${lang}"/></p>

    <form action="controller" method="POST">
        <input type="hidden" name="command" value="confirmation" />
        <fieldset>
            <legend><fmt:message key="addarticle.form.name" bundle="${lang}"/>:</legend>
            <span class="req">*</span><fmt:message key="addarticle.title" bundle="${lang}"/>:
            <br>
            <input required type="text" name="title" onkeyup="validate_add_publication(this)" placeholder="<fmt:message key="addarticle.title.placeholder" bundle="${lang}"/>"><br>
            <span class="req">*</span><fmt:message key="addarticle.text" bundle="${lang}"/>:
            <br>
            <textarea required name="text" style="width:700px; height:600px;" onkeyup="validate_add_publication(this)" placeholder="<fmt:message key="addarticle.text.placeholder" bundle="${lang}"/>"></textarea>
            <br>
            <input type="submit" value="<fmt:message key="addarticle.button.ok" bundle="${lang}"/>" />
        </fieldset>
    </form>
</section>
