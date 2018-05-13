<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="r" uri="customtags" %>
<%@ page pageEncoding="UTF-8" %>
<section>
    <c:forEach items="${viewedPublications}" var="publication">
        <article>
            <header class="article-header">
                <h1>
                    <a href="controller?command=publication&pub=${publication.id}">${publication.title}</a>
                </h1>
                <div class="entry-meta">
                    Published <a href="#">${publication.publishTime}</a>
                    author <a href="controller?command=cabinet&uname=${publication.author}">${publication.author}</a>
                </div>
            </header>
            <div class="article-content">
                <div class="article-image">
                    <img src="../${publication.content}">
                </div>
            </div>
        </article>
    </c:forEach>

    <r:pager page="${results}" pageParam="page" />
</section>

