<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
    <form action="#some_action.jsp" method="post">
        <select>
            <option disabled value="volvo">Choose master</option>
            <c:forEach items="${masters}" var="master">
                <option value="${master.login}">${master.login}</option>
            </c:forEach>
        </select>


        <input type="submit" value="Отправить">
    </form>



</section>
