<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
    <c:forEach items="${masters}" var="master">
        <div>
            <p>
                <a href="controller?command=gallery&page=0&master=${master}">
                    <img src="../image/default.png" width="400" height="300" alt="${master}">
                </a>
            </p>
            <div>
                <form action="#">
                    <input type="submit" name="button" value="Отзывы">
                </form>
            </div>
            <div>
                <form action="controller?command=gallery&page=0&master=${master}">
                    <input type="submit" name="button" value="${master}">
                </form>
            </div>
        </div>
    </c:forEach>
    <%--todo place here masters with photos --%>
</section>
