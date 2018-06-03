<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<section>
    <c:forEach items="${masters}" var="master">
        <div class="master-block">
            <p>
                <a href="controller?command=gallery&page=0&master=${master.login}">
                    <img src="../image/default.png" width="400" height="300" alt="${masterlogin}">
                </a>
            </p>
            <div class="master-button">
                <form action="controller">
                    <input type="hidden" name="command" value="masters">
                    <input type="submit" name="button" onclick="return confirm('<fmt:message key="master.message.reviews" bundle="${lang}"/>')" value="<fmt:message key="master.button.reviews" bundle="${lang}"/>">
                </form>
            </div>
            <div class="master-button">
                <form action="controller">
                    <input type="hidden" name="command" value="gallery">
                    <input type="hidden" name="page" value="0">
                    <input type="hidden" name="master" value="${master.login}">
                    <input type="submit" value="${master.name} ${master.surname}">
                </form>
            </div>
        </div>
    </c:forEach>
</section>
