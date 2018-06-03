<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<section>
    <c:choose>
        <c:when test="${salonUser.userRole == 0 || salonUser.login == uname.login}"><%--if user is himself or admin--%>
            <fmt:message key="cabinet.id" bundle="${lang}"/>: ${uname.id}
            <br>
            <fmt:message key="cabinet.login" bundle="${lang}"/>: ${uname.login}
            <br>
            <fmt:message key="cabinet.name" bundle="${lang}"/>: ${uname.name} ${uname.surname}
            <br>
            <fmt:message key="cabinet.email" bundle="${lang}"/>: ${uname.email}
            <br>
            <fmt:message key="cabinet.gender" bundle="${lang}"/>:
            <c:choose>
                <c:when test="${uname.male == true}">
                    <fmt:message key="cabinet.male" bundle="${lang}"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="cabinet.female" bundle="${lang}"/>
                </c:otherwise>
            </c:choose>
            <br>
            <fmt:message key="cabinet.role" bundle="${lang}"/>:
            <c:choose>
                <c:when test="${uname.userRole == 0}">
                    <fmt:message key="cabinet.admin" bundle="${lang}"/>
                </c:when>
                <c:when test="${uname.userRole == 1}">
                    <fmt:message key="cabinet.master" bundle="${lang}"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="cabinet.user" bundle="${lang}"/>
                </c:otherwise>
            </c:choose>
            <br>
            <fmt:message key="cabinet.registered" bundle="${lang}"/>: ${uname.registration}
            <br>
            <fmt:message key="cabinet.birth" bundle="${lang}"/>: ${uname.birth}
            <br>
            <fmt:message key="cabinet.blocked" bundle="${lang}"/>: ${uname.blocked}
            <br>
            <br>
            <c:choose>
                <c:when test="${uname.userRole <= 1}">
                    <fmt:message key="cabinet.publications" bundle="${lang}"/>: ${publicationSum}
                </c:when>
                <c:when test="${uname.userRole == 2}">
                    ${uname.login} <fmt:message key="cabinet.publications.not" bundle="${lang}"/>.
                </c:when>
            </c:choose>
            <br>
            <a href="controller?command=cabinet_block_user&userId=${uname.id}&uname=${uname.login}&blocked=${uname.blocked}" onclick="return confirm('<fmt:message key="cabinet.delete.confirm" bundle="${lang}"/>')">
                <c:choose>
                    <c:when test="${uname.blocked}">
                        <fmt:message key="cabinet.restore" bundle="${lang}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="cabinet.delete" bundle="${lang}"/>
                    </c:otherwise>
                </c:choose>
            </a>
        </c:when>
        <c:otherwise>
            <fmt:message key="cabinet.login" bundle="${lang}"/>: ${uname.login}
            <br>
            <fmt:message key="cabinet.role" bundle="${lang}"/>:
            <c:choose>
                <c:when test="${uname.userRole == 0}">
                    <fmt:message key="cabinet.admin" bundle="${lang}"/>
                </c:when>
                <c:when test="${uname.userRole == 1}">
                    <fmt:message key="cabinet.master" bundle="${lang}"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="cabinet.user" bundle="${lang}"/>
                </c:otherwise>
            </c:choose>
            <br>
            <fmt:message key="cabinet.registered" bundle="${lang}"/>: ${uname.registration}
            <br>
            <br>
            <c:choose>
                <c:when test="${uname.userRole <= 1}">
                    <fmt:message key="cabinet.publications" bundle="${lang}"/>: ${publicationSum}
                </c:when>
                <c:when test="${uname.userRole == 2}">
                    ${uname.login} <fmt:message key="cabinet.publications.not" bundle="${lang}"/>.
                </c:when>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <%--List of appointments to masters as a client.--%>

    <br>
    <br>
    <br>
<c:if test="${uname.id == salonUser.id || salonUser.userRole == 0}"> <%--Only admin may see everybody's appointments and every user their own ones.--%>
    <fmt:message key="cabinet.appointed" bundle="${lang}"/>:
    <c:if test="${empty appointedClientMeetings}">
        <fmt:message key="cabinet.appointed.not" bundle="${lang}"/>.
    </c:if>
    <br>
    <c:forEach items="${appointedClientMeetings}" var="appointment">
        <fmt:message key="cabinet.time" bundle="${lang}"/>: ${appointment.beginOfAppointment},
        <fmt:message key="cabinet.master" bundle="${lang}"/>:
        <c:forEach items="${users}" var="master">
            <c:if test="${master.id == appointment.masterId}">
                ${master.name} ${master.surname}
            </c:if>
        </c:forEach>,
        <fmt:message key="cabinet.appointment.type" bundle="${lang}"/>:
            <c:choose>
                <c:when test="${appointment.appointmentType == 0}">
                    <fmt:message key="cabinet.consultation" bundle="${lang}"/>
                </c:when>
                <c:when test="${appointment.appointmentType == 1}">
                    <fmt:message key="cabinet.tattooing" bundle="${lang}"/>
                </c:when>
                <c:when test="${appointment.appointmentType == 2}">
                    <fmt:message key="cabinet.piercing" bundle="${lang}"/>piercing
                </c:when>
                <c:when test="${appointment.appointmentType == 3}">
                    <fmt:message key="cabinet.makeup" bundle="${lang}"/>
                </c:when>
            </c:choose>,
        <fmt:message key="cabinet.status" bundle="${lang}"/>:
            <c:choose>
                <c:when test="${appointment.appointmentStatus == 0}">
                    <fmt:message key="cabinet.appointed.s" bundle="${lang}"/>
                </c:when>
                <c:when test="${appointment.appointmentStatus == 1}">
                    <fmt:message key="cabinet.canceled" bundle="${lang}"/>canceled
                </c:when>
                <c:when test="${appointment.appointmentType == 2}">
                    <fmt:message key="cabinet.attend.not" bundle="${lang}"/>
                </c:when>
            </c:choose>.
            <br>
    </c:forEach>
</c:if>

    <%-- List of master's appointments. Admin sees all of them, masters - only their own appointments. --%>

    <br>
    <br>
    <c:if test="${uname.id == salonUser.id && uname.userRole == 1 || salonUser.userRole == 0 && uname.userRole == 1}">
        <br>
        <fmt:message key="cabinet.appointed.clients" bundle="${lang}"/>:
        <c:if test="${empty appointedMasterMeetings}">
            <fmt:message key="cabinet.appointed.not" bundle="${lang}"/>.
        </c:if>
        <br>
        <c:forEach items="${appointedMasterMeetings}" var="appointment">
            <fmt:message key="cabinet.time" bundle="${lang}"/>: ${appointment.beginOfAppointment},
            <fmt:message key="cabinet.client" bundle="${lang}"/>:
            <c:forEach items="${users}" var="client">
                <c:if test="${client.id == appointment.clientId}">
                    ${client.name} ${client.surname}
                </c:if>
            </c:forEach>,
            <fmt:message key="cabinet.appointment.type" bundle="${lang}"/>:
            <c:choose>
                <c:when test="${appointment.appointmentType == 0}">
                    <fmt:message key="cabinet.consultation" bundle="${lang}"/>
                </c:when>
                <c:when test="${appointment.appointmentType == 1}">
                    <fmt:message key="cabinet.tattooing" bundle="${lang}"/>
                </c:when>
                <c:when test="${appointment.appointmentType == 2}">
                    <fmt:message key="cabinet.piercing" bundle="${lang}"/>
                </c:when>
                <c:when test="${appointment.appointmentType == 3}">
                    <fmt:message key="cabinet.makeup" bundle="${lang}"/>
                </c:when>
            </c:choose>,
            <fmt:message key="cabinet.status" bundle="${lang}"/>:
            <c:choose>
                <c:when test="${appointment.appointmentStatus == 0}">
                    <fmt:message key="cabinet.appointed.s" bundle="${lang}"/>
                </c:when>
                <c:when test="${appointment.appointmentStatus == 1}">
                    <fmt:message key="cabinet.canceled" bundle="${lang}"/>canceled
                </c:when>
                <c:when test="${appointment.appointmentType == 2}">
                    <fmt:message key="cabinet.attend.not" bundle="${lang}"/>
                </c:when>
            </c:choose>.
            <br>
        </c:forEach>
    </c:if>

</section>

