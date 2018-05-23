<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <input type="submit" name="button" onclick="return confirm('Reviews will be available soon!')" value="Отзывы">
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
