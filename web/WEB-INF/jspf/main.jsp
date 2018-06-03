<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="r" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<section>
    <c:forEach items="${viewedPublications}" var="publication">
        <article>
            <header class="article-header">
                <h1>
                    <a href="controller?command=publication&pub=${publication.id}">${publication.title}</a>
                </h1>
                <div class="entry-meta">

                    <fmt:message key="article.published" bundle="${lang}"/> <a href="#">${publication.publishTime}</a>
                    <fmt:message key="article.author" bundle="${lang}"/> <a href="controller?command=cabinet&uname=${publication.author}">${publication.author}</a>
                </div>
            </header>
            <div class="article-content">
                <c:choose>
                    <c:when test="${publication.textNotPhoto}">
                        <p>
                                ${publication.content}
                        </p>
                    </c:when>
                    <c:otherwise>
                        <div class="article-image">
                            <img src="../${publication.content}">
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </article>
    </c:forEach>

    <r:pager page="${results}" pageParam="page" />

</section>

