<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<section>
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="sendmail" />
        <table>
            <tr>
                <td><fmt:message key="mail.subject" bundle="${lang}"/>:</td>
                <td><input type="text" onkeyup="validate_add_publication(this)" name="subject"/></td>
            </tr>
        </table>
        <hr>
        <textarea type="text" onkeyup="validate_add_publication(this)" name="body" rows="5" cols="45"><fmt:message key="mail.text" bundle="${lang}"/></textarea>
        <br><br>
        <input type="submit" value="<fmt:message key="mail.button.send" bundle="${lang}"/>"/>
        <br>
        ${badProperties}
    </form>
</section>

