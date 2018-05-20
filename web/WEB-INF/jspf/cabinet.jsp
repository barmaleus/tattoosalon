<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<section>
    <c:choose>
        <c:when test="${userRange == 0 || user == uname.login}"><%--if user is himself or admin--%>
            Id: ${uname.id}
            <br>
            Login: ${uname.login}
            <br>
            Name: ${uname.name} ${uname.surname}
            <br>
            Email: ${uname.email}
            <br>
            Gender: 
            <c:choose>
                <c:when test="${uname.male == true}">
                    male
                </c:when>
                <c:otherwise>
                    female
                </c:otherwise>
            </c:choose>
            <br>
            Role:
            <c:choose>
                <c:when test="${uname.userRole == 0}">
                    admin
                </c:when>
                <c:when test="${uname.userRole == 1}">
                    master
                </c:when>
                <c:otherwise>
                    user
                </c:otherwise>
            </c:choose>
            <br>
            Registered: ${uname.registration}
            <br>
            Birth date: ${uname.birth}
            <br>
            Blocked: ${uname.blocked}
            <br>
            <br>
            <c:choose>
                <c:when test="${uname.userRole <= 1}">
                    Created publications: ${publicationSum}
                </c:when>
                <c:when test="${uname.userRole == 2}">
                    ${uname.login} can't create publications.
                </c:when>
            </c:choose>
            <br>
            <a href="controller?command=cabinet_block_user&userId=${uname.id}&uname=${uname.login}&blocked=${uname.blocked}" onclick="return confirm('Are you sure you want to delete this account?')">
                <c:choose>
                    <c:when test="${uname.blocked}">
                        Restore account
                    </c:when>
                    <c:otherwise>
                        Delete account
                    </c:otherwise>
                </c:choose>
            </a>
        </c:when>
        <c:otherwise>
            Login: ${uname.login}
            <br>
            Role:
            <c:choose>
                <c:when test="${uname.userRole == 0}">
                    admin
                </c:when>
                <c:when test="${uname.userRole == 1}">
                    master
                </c:when>
                <c:otherwise>
                    user
                </c:otherwise>
            </c:choose>
            <br>
            Registered: ${uname.registration}
            <br>
            <br>
            <c:choose>
                <c:when test="${uname.userRole <= 1}">
                    Created publications: ${publicationSum}
                </c:when>
                <c:when test="${uname.userRole == 2}">
                    ${uname.login} can't create publications.
                </c:when>
            </c:choose>
        </c:otherwise>
    </c:choose>


    <%--<br>todo показывать пользователю его назначенные консультации--%>
    <%--<br>--%>
    <%--<c:if test="${(userRange == 0 && uname.userRole == 2) || (user == uname.login && uname.userRole == 2)}">--%>
        <%--<c:forEach items="appointedCkientMeetings" var="appointment">--%>
            <%--Назначено: ${appointment.appointmentType????}, мастер: ${appointment.masterId????}, дата: ${appointment.beginOfAppointment}.--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>
    <%--<c:if test="${(userRange == 0 && uname.userRole == 1) || (user == uname.login && uname.userRole == 1)}">--%>
        <%--<c:forEach items="appointedMasterMeetings" var="appointment">--%>
            <%--Назначено: ${appointment.appointmentType???}, посетитель: ${appointment.clientId????}, дата: ${appointment.beginOfAppointment}.--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>

</section>

