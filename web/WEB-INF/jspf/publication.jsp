<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<section>
    <c:if test="${publication.id == param.pub}">
        <article>
            <header class="article-header">
                <h1>
                        ${publication.title}
                </h1>
                <div class="entry-meta">
                    Published ${publication.publishTime}
                    author <a href=controller?command=cabinet&uname=${publication.author}>${publication.author}</a>
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
    </c:if>
</section>

