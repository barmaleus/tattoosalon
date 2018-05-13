<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<html>
<head>
    <title>Admin Page</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/administration.js"></script>  <!--todo import js dont works-->
    <link type="text/css" href="${pageContext.request.contextPath}/css/nav_and_header.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/css/publication.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/css/administration.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet">
</head>
<body>
<%@include file="../../WEB-INF/jspf/header.jspf"%>
<%@include file="../../WEB-INF/jspf/nav.jspf"%>
<section>
    <h2>Publications</h2>
    <br>
    <c:forEach items="${viewedPublications}" var="publication" >
        <button class="accordion" id="accordeonPubId${count.count}">${publication.title}  —  Author: ${publication.author}.  Published: ${publication.publishTime}.  Status:
            <c:choose>
            <c:when test="${publication.blocked}">
                Blocked.
            </c:when>
            <c:otherwise>
                Active.
            </c:otherwise>
        </c:choose></button>
        <div class="panel">
            <c:choose>
            <c:when test="${publication.blocked}">
                <form name="publicationform" action="controller" method="POST" id="publicationform">
                    <input type="hidden" name="command" value="block_publication" />
                    <input type="hidden" name="publicationId" value="${publication.id}" />
                    <input type="hidden" name="blocked" value="${publication.blocked}" />
                    <input type="submit" name="pub-block-button" value="Unblock publication" />
                </form>
            </c:when>
            <c:otherwise>
                <form name="publicationform" action="controller" method="POST" id="publicationform">
                    <input type="hidden" name="command" value="block_publication" />
                    <input type="hidden" name="publicationId" value="${publication.id}" />
                    <input type="hidden" name="blocked" value="${publication.blocked}" />
                    <input type="submit" name="pub-block-button" value="Block publication" />
                </form>
            </c:otherwise>
            </c:choose>
        </div>
    </c:forEach>

    <br>
    <h2>Users</h2>
    <br>
    <c:forEach items="${allUsers}" var="user" >
        <button class="accordion" id="accordeonUserId${count.count}">${user.login}  —  Email: ${user.email}.  Role:
            <c:choose>
                <c:when test="${user.userRole == 0}">
                    Administrator.
                </c:when>
                <c:when test="${user.userRole == 1}">
                    Master.
                </c:when>
                <c:otherwise>
                    User.
                </c:otherwise>
            </c:choose> Registered: ${user.registration}. Status:
            <c:choose>
                <c:when test="${user.blocked}">
                    Blocked.
                </c:when>
                <c:otherwise>
                    Active.
                </c:otherwise>
            </c:choose></button>
        <div class="panel">
            <c:choose>
                <c:when test="${user.userRole == 0}">
                    <form name="userroleform" action="controller" method="POST" id="userroleform">
                        <input type="hidden" name="command" value="role_user" />
                        <input type="hidden" name="publicationId" value="${user.id}" />
                        <input type="hidden" name="operation" value="makeMaster" />
                        <input type="submit" name="user-role-button" value="Make Admin Master" />
                    </form>
                    <form name="userroleform" action="controller" method="POST" id="userroleform">
                        <input type="hidden" name="command" value="role_user" />
                        <input type="hidden" name="publicationId" value="${user.id}" />
                        <input type="hidden" name="operation" value="makeUser" />
                        <input type="submit" name="user-role-button" value="Make Admin User" />
                    </form>
                </c:when>
                <c:when test="${user.userRole == 1}">
                    <form name="userroleform" action="controller" method="POST" id="userroleform">
                        <input type="hidden" name="command" value="role_user" />
                        <input type="hidden" name="publicationId" value="${user.id}" />
                        <input type="hidden" name="operation" value="makeAdmin" />
                        <input type="submit" name="user-role-button" value="Make Master Admin" />
                    </form>
                    <form name="userroleform" action="controller" method="POST" id="userroleform">
                        <input type="hidden" name="command" value="role_user" />
                        <input type="hidden" name="publicationId" value="${user.id}" />
                        <input type="hidden" name="operation" value="makeUser" />
                        <input type="submit" name="user-role-button" value="Make Master User" />
                    </form>
                </c:when>
                <c:otherwise>
                    <form name="userroleform" action="controller" method="POST" id="userroleform">
                        <input type="hidden" name="command" value="role_user" />
                        <input type="hidden" name="publicationId" value="${user.id}" />
                        <input type="hidden" name="operation" value="makeAdmin" />
                        <input type="submit" name="user-role-button" value="Make User Admin" />
                    </form>
                    <form name="userroleform" action="controller" method="POST" id="userroleform">
                        <input type="hidden" name="command" value="role_user" />
                        <input type="hidden" name="publicationId" value="${user.id}" />
                        <input type="hidden" name="operation" value="makeMaster" />
                        <input type="submit" name="user-role-button" value="Make User Master" />
                    </form>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${user.blocked}">
                    <form name="userform" action="controller" method="POST" id="userform">
                        <input type="hidden" name="command" value="block_user" />
                        <input type="hidden" name="userId" value="${user.id}" />
                        <input type="hidden" name="blocked" value="${user.blocked}" />
                        <input type="submit" name="user-block-button" value="Unblock user" />
                    </form>
                </c:when>
                <c:otherwise>
                    <form name="userform" action="controller" method="POST" id="userform">
                        <input type="hidden" name="command" value="block_user" />
                        <input type="hidden" name="userId" value="${user.id}" />
                        <input type="hidden" name="blocked" value="${user.blocked}" />
                        <input type="submit" name="user-block-button" value="Block user" />
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </c:forEach>

<style>
    form {
        display: inline-block;
    }
</style>


<script>                                                                   <%--todo import js dont works--%>
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var panel = this.nextElementSibling;
            if (panel.style.maxHeight){
                panel.style.maxHeight = null;
            } else {
                panel.style.maxHeight = panel.scrollHeight + "px";
            }
        });
    }
</script>

</section>
</body>
</html>
