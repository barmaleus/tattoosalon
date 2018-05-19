<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>

<section>
    <h2><fmt:message key="about.header.main" bundle="${lang}"/></h2>

    <fmt:message key="about.description" bundle="${lang}"/>
    <br>
    <fmt:message key="about.really" bundle="${lang}"/>

    <h3><fmt:message key="about.servicelist.title" bundle="${lang}"/></h3>
    <ul>
        <li><fmt:message key="about.servicelist.point1" bundle="${lang}"/></li>
        <li><fmt:message key="about.servicelist.point2" bundle="${lang}"/></li>
        <li><fmt:message key="about.servicelist.point3" bundle="${lang}"/></li>
        <li><fmt:message key="about.servicelist.point4" bundle="${lang}"/></li>
        <li><fmt:message key="about.servicelist.point5" bundle="${lang}"/></li>
        <li><fmt:message key="about.servicelist.point6" bundle="${lang}"/></li>
        <li><fmt:message key="about.servicelist.point7" bundle="${lang}"/></li>
    </ul>

    <fmt:message key="about.info1" bundle="${lang}"/>
    <br>
    <fmt:message key="about.info2" bundle="${lang}"/>
    <br>
    <fmt:message key="about.info3" bundle="${lang}"/>
    <br>
    <fmt:message key="about.info4" bundle="${lang}"/>
    <br>
    <fmt:message key="about.info5" bundle="${lang}"/>
    <br>
    <fmt:message key="about.info6" bundle="${lang}"/>
    <br>
    <br>

    <h3><fmt:message key="about.price.title" bundle="${lang}"/></h3>
    <ul>
        <li><h3><fmt:message key="about.price.point1" bundle="${lang}"/></li>
        <li><h3><fmt:message key="about.price.point2" bundle="${lang}"/></li>
        <li><h3><fmt:message key="about.price.point3" bundle="${lang}"/></li>
        <li><h3><fmt:message key="about.price.point4" bundle="${lang}"/></li>
    </ul>

</section>
