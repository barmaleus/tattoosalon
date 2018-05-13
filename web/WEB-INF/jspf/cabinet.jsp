<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<section>
    <c:choose>
        <c:when test="${userRange == 0 || user == uname.login}"><%--if user is himself or admin--%>
            * Id: ${uname.id}
            <br>
            Login: ${uname.login}
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
            Wroted comments: ${commentsSum}     <%--todo--%>
            <br>
            Delete <a href="controller?command=cabinet&uname=${uname.login}&&command=delete_user">account</a>  <%--TODO !!! make another command!!!!!!!!! todo make tiser? which asks, really you want to delete account?--%> <%--if user is himself js-> onclick--%>
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
            <br>
            Wroted comments: ${commentsSum}     <%--todo--%>
        </c:otherwise>
    </c:choose>
</section>

