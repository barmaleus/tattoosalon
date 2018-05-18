<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization.nav_and_header" var="lang"/>

<section>
    <h2><fmt:message key="about.header.main" bundle="${lang}"/></h2>

    <fmt:message key="about.description" bundle="${lang}"/>
    <br>
    <fmt:message key="about.really" bundle="${lang}"/>

    <h3><fmt:message key="about.servicelist.title" bundle="${lang}"/>У нас вы получите:</h3>
    <ul>
        <li><fmt:message key="about.servicelist.point1" bundle="${lang}"/>Бесплатную консультацию</li>
        <li><fmt:message key="about.servicelist.point2" bundle="${lang}"/>Индивидуальный эскиз</li>
        <li><fmt:message key="about.servicelist.point3" bundle="${lang}"/>Широкий выбор стилей</li>
        <li><fmt:message key="about.servicelist.point4" bundle="${lang}"/>Черную, белую, цветную или ультрафиолетовую татуировку любой сложности и размера</li>
        <li><fmt:message key="about.servicelist.point5" bundle="${lang}"/>CoverUp (исправление, коррекция, перекрытие надоевших тату, а также закрытие шрамов и ожогов)</li>
        <li><fmt:message key="about.servicelist.point6" bundle="${lang}"/>Перманентный макияж (татуаж)</li>
        <li><fmt:message key="about.servicelist.point7" bundle="${lang}"/>Пирсинг любых частей тела</li>
    </ul>

    Master Bombaster ‒ качество по разумной цене.
    <br>
    Тату — это искусство. Мы видим красоту в каждом и помогаем открыть лучшие ее стороны!
    <br>
    Один раз выбрав наш тату-салон вы останетесь с нами надолго, т.к. именно здесь вы также получите полный комплекс услуг по уходу за волосами: любые виды стрижки и окрашивания волос, наращивание и экранирование, и даже африканские косички.
    <br>
    Для вас ежедневно работают мастера по маникюру, педикюру и наращиванию ногтей.
    <br>
    Мы заботимся о вашем здоровье и безопасности. Мы успешно работаем для вас 2004 года, имеем все необходимые разрешения, а также средства защиты. Bombaster-специалисты соблюдают все стандарты гигиены, а инструмент проходит тщательную стерилизацию.
    <br>
    Мы ‒ не просто студия татуировки, это целый мир ярких красок и красоты, двери которого открыты для каждого.
    <br>
    <br>

    <h3>Цены на тату</h3>
    <ul>
        <li>Ультрафиолетовая татуировка от 70</li>
        <li>Татуировка (эквивалент размера стандартного спичечного коробка) от 70</li>
        <li>Татуировка (эквивалентная размеру стандартной сигаретной пачки) от 85</li>
        <li>Портретная работа и другие работы повышенной сложности по согласованию с мастером</li>
    </ul>

</section>
