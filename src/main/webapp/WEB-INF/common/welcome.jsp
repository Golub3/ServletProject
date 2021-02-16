<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">

<head>
    <title>
        <fmt:message key="greeting"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/js/bootstrap.bundle.min.js" />" rel="script">
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>

</head>
<body class="body">

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/common/navbar-landing.jsp"/>

<div class="overlay"></div>
<div class="container offset-1">
    <div class="description text-left">
        <h3>
            <fmt:message key="greeting"/>
        </h3>
    </div>

</div>

</body>

</html>


