<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>
        <fmt:message key="navigation.section.expositions"/>
    </title>
    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
    <link href="<c:url value="${pageContext.request.contextPath}/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" />" rel="script">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<div class="container">

    <h1><fmt:message key="button.create.exposition"/></h1>
    <hr>
    <h2><fmt:message key="button.save.exposition"/></h2>

    <form href="${pageContext.request.contextPath}/app/expositions"
          method="POST">

        <input type="text" name="theme"
               placeholder=<fmt:message key="theme"/> class="form-control mb-4 col-4">

        <input type="text" name="price"
               placeholder=<fmt:message key="price"/> class="form-control mb-4 col-4">


        <button type="submit" class="btn btn-info col-2"><h2><fmt:message key="button.save.exposition"/></h2></button>
    </form>

    <hr>
    <br>
    <div class="home">
        <a class="" href="${pageContext.request.contextPath}/app/schedules">
            <fmt:message key="button.back"/>
        </a>
    </div>
</div>
</body>
</html>