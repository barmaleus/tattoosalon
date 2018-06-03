<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<link type="text/css" href="${pageContext.request.contextPath}/css/prime.css" rel="stylesheet">
<section>

    <h2><fmt:message key="apnmt.title" bundle="${lang}"/></h2>
    <fmt:message key="apnmt.date.current" bundle="${lang}"/>: <c:out value="${twoWeeksSinceToday[0]}"/>
    <fmt:message key="apnmt.master.chosen" bundle="${lang}"/>:
    <c:choose>
        <c:when test="${masterId == null}">
            <fmt:message key="apnmt.master.none" bundle="${lang}"/>
        </c:when>
        <c:otherwise>
            <c:forEach items="${masters}" var="master">
                <c:choose>
                    <c:when test="${masterId eq master.id}">
                        ${master.name} ${master.surname}
                    </c:when>
                </c:choose>
            </c:forEach>
        </c:otherwise>
    </c:choose>

    <form action="controller" method="post">
        <input type="hidden" name="command" value="appointment">
        <select name="master-id">
            <c:choose>
                <c:when test="${masterId == null}">
                    <option selected disabled><fmt:message key="apnmt.master.choose" bundle="${lang}"/></option>
                    <c:forEach items="${masters}" var="master">
                        <option value="${master.id}">${master.name} ${master.surname}</option>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <option disabled><fmt:message key="apnmt.master.choose" bundle="${lang}"/></option>
                    <c:forEach items="${masters}" var="master">
                        <c:choose>
                            <c:when test="${masterId eq master.id}">
                                <option selected value="${master.id}">${master.name} ${master.surname}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${master.id}">${master.name} ${master.surname}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </select>
        <p><input type="submit" value="<fmt:message key="apnmt.master.this" bundle="${lang}"/>"></p>
    </form>

    <c:choose>
        <c:when test="${masterId ne null}">
            <table id="appointment-table" >
                <tr>
                    <th>&nbsp;</th>
                    <c:forEach items="${twoWeeksSinceToday}" var="day" varStatus="status">
                        <th>
                            <fmt:parseDate value="${day}" pattern="yyyy-MM-dd" var="date"/>
                            <fmt:formatDate value="${date}" pattern="MMM, dd" />
                                ${daysOfWeekSinceToday[status.index]}
                        </th>
                    </c:forEach>
                </tr>
                <c:forEach items="${timeOfDay}" var="time" varStatus="row">
                    <tr>
                        <td>${time}</td>
                        <c:forEach items="${twoWeeksSinceToday}" var="day" varStatus="column">
                            <td id="${row.index}-${column.index}">
                                <c:choose>
                                    <c:when test="${row.index < pastHoursValidateIndex && column.index == 0}" >
                                        <form>
                                            <input disabled type="submit" value="<fmt:message key="apnmt.passed" bundle="${lang}"/>">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="controller" method="POST">
                                            <input type="hidden" name="command" value="order">
                                            <input type="hidden" name="masterId" value="${masterId}">
                                            <input type="hidden" name="today" value="${twoWeeksSinceToday[0]}" />
                                            <input type="hidden" name="rowIndex" value="${row.index}">
                                            <input type="hidden" name="columnIndex" value="${column.index}">
                                            <input type="submit" value="<fmt:message key="apnmt.order" bundle="${lang}"/>"
                                                   onclick="return confirm('<fmt:message key="apnmt.order.confirm" bundle="${lang}"/>')"
                                            <c:forEach items="${appointedMeetingsTimeIndex}" var="timeIndex" varStatus="status">
                                            <c:if test="${row.index == timeIndex && column.index == appointedMeetingsDateIndex[status.index]}">
                                                <c:out value="disabled='disabled'"/>
                                            </c:if>
                                            </c:forEach>
                                            <c:forEach items="${holidayDateIndex}" var="holidayIndex">
                                            <c:if test="${column.index == holidayIndex}">
                                                <c:out value="disabled='disabled'"/>
                                            </c:if>
                                            </c:forEach>
                                            >
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
</section>
