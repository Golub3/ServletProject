<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<%--<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">--%>
<%--<link href="<c:url value="/js/bootstrap.bundle.min.js" />" rel="script">--%>

<nav class="navbar navbar navbar-expand-lg navbar-inverse navbar-fixed-top navbar-dark bg-dark">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-collapse"
                aria-controls="navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div id="navbar-collapse" class="navbar">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/app/home">Exhibition center</a>
            <ul class="navbar-nav">
                <li class="navbar-item">
                    <a class="nav-link " href="${pageContext.request.contextPath}/app/personal-cabinet">
                        <fmt:message key="navigation.section.home"/>
                    </a>
                </li>
            </ul>
            <ul class="navbar-nav navbar-right">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/log-me">
                        <fmt:message key="navigation.section.login"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="${pageContext.request.contextPath}/app/reg-me">
                        <fmt:message key="navigation.section.register"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="navbar-brand" href="?sessionLocale=en">
                        <img class="lang-icon" src="${pageContext.request.contextPath}/image/eng.png"
                             alt="<fmt:message key="lang.en"/>">
                    </a>
                </li>
                <li class="nav-item">
                    <a class="navbar-brand" href="?sessionLocale=ua">
                        <img class="lang-icon" src="${pageContext.request.contextPath}/image/ukr.png"
                             alt="<fmt:message key="lang.ua"/>">
                    </a>
                </li>
            </ul>
        </div>
    </div>



</nav>

