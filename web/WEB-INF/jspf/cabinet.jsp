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

    <%--List of appointments to masters like a client.--%>

    <br>
    <br>
    <br>
<c:if test="${uname.id == salonUser.id || salonUser.userRole == 0}"> <%--Only admin may see everybody's appointments and every user their own ones.--%>
    Appointed:
    <c:if test="${empty appointedClientMeetings}">
        None.
    </c:if>
    <br>
    <c:forEach items="${appointedClientMeetings}" var="appointment">
            Time: ${appointment.beginOfAppointment},
            master:
        <c:forEach items="${users}" var="master">
            <c:if test="${master.id == appointment.masterId}">
                ${master.name} ${master.surname}
            </c:if>
        </c:forEach>,
            type of appointment:
            <c:choose>
                <c:when test="${appointment.appointmentType == 0}">
                    consultation
                </c:when>
                <c:when test="${appointment.appointmentType == 1}">
                    tattooing
                </c:when>
                <c:when test="${appointment.appointmentType == 2}">
                    piercing
                </c:when>
                <c:when test="${appointment.appointmentType == 3}">
                    premanent makeup
                </c:when>
            </c:choose>,
            status:
            <c:choose>
                <c:when test="${appointment.appointmentStatus == 0}">
                    appointed
                </c:when>
                <c:when test="${appointment.appointmentStatus == 1}">
                    canceled
                </c:when>
                <c:when test="${appointment.appointmentType == 2}">
                    failure to attend
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
        Appointed clients:
        <c:if test="${empty appointedMasterMeetings}">
            None.
        </c:if>
        <br>
        <c:forEach items="${appointedMasterMeetings}" var="appointment">
            Time: ${appointment.beginOfAppointment},
            client:
            <c:forEach items="${users}" var="client">
                <c:if test="${client.id == appointment.clientId}">
                    ${client.name} ${client.surname}
                </c:if>
            </c:forEach>,
            type of appointment:
            <c:choose>
                <c:when test="${appointment.appointmentType == 0}">
                    consultation
                </c:when>
                <c:when test="${appointment.appointmentType == 1}">
                    tattooing
                </c:when>
                <c:when test="${appointment.appointmentType == 2}">
                    piercing
                </c:when>
                <c:when test="${appointment.appointmentType == 3}">
                    premanent makeup
                </c:when>
            </c:choose>,
            status:
            <c:choose>
                <c:when test="${appointment.appointmentStatus == 0}">
                    appointed
                </c:when>
                <c:when test="${appointment.appointmentStatus == 1}">
                    canceled
                </c:when>
                <c:when test="${appointment.appointmentType == 2}">
                    failure to attend
                </c:when>
            </c:choose>.
            <br>
        </c:forEach>
    </c:if>

</section>

