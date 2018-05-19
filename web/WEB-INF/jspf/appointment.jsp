<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" href="${pageContext.request.contextPath}/css/prime.css" rel="stylesheet">
<section>

    <h2>Online appointment</h2>
    Current date: <c:out value="${twoWeeksSinceToday[0]}"/>
    Choosed master:
    <c:choose>
        <c:when test="${masterId == null}">
            None
        </c:when>
        <c:otherwise>
            <c:forEach items="${masters}" var="master">
                <c:choose>
                    <c:when test="${masterId eq master.id}">
                        ${master.login}
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
                    <option selected disabled>Choose master</option>
                    <c:forEach items="${masters}" var="master">
                        <option value="${master.id}">${master.login}</option>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <option disabled>Choose master</option>
                    <c:forEach items="${masters}" var="master">
                        <c:choose>
                            <c:when test="${masterId eq master.id}">
                                <option selected value="${master.id}">${master.login}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${master.id}">${master.login}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </select>
        <p><input type="submit" value="Choose this master"></p>
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
                                            <input disabled type="submit" value="Passed">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="controller" method="POST">
                                            <input type="hidden" name="command" value="order">
                                            <input type="hidden" name="masterId" value="${masterId}">
                                            <input type="hidden" name="today" value="${twoWeeksSinceToday[0]}" />
                                            <input type="hidden" name="rowIndex" value="${row.index}">
                                            <input type="hidden" name="columnIndex" value="${column.index}">
                                            <input type="submit" value="Order"
                                                   onclick="return confirm('Are you sure you want to make appointment for this time?')"
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
