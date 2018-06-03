<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>
<section>
    <c:if test="${publication.id == param.pub}">
        <article>
            <header class="article-header">
                <h1>
                        ${publication.title}
                </h1>
                <div class="entry-meta">
                    <fmt:message key="article.published" bundle="${lang}"/> ${publication.publishTime}
                    <fmt:message key="article.author" bundle="${lang}"/> <a href=controller?command=cabinet&uname=${publication.author}>${publication.author}</a>
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
                        <div class="article-full-image">
                            <img src="../${publication.content}">
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </article>
    </c:if>
</section>

