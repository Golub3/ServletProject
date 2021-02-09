<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>
        <fmt:message key="navigation.section.home"/>
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

    <h1><fmt:message key="button.create.schedule"/></h1>
    <hr>
    <h2><fmt:message key="button.save.schedule"/></h2>

    <form href="${pageContext.request.contextPath}/app/schedules"
          method="POST">

        <input type="time" name="time_start"
               placeholder="Time start" class="form-control mb-4 col-4">

        <input type="time" name="time_end"
               placeholder="Time end" class="form-control mb-4 col-4">

        <input type="date" name="date"
               placeholder="Date" class="form-control mb-4 col-4">

        <select name="hall">
            <c:forEach items="${halls}" var="halls">
                <option value = ${halls.id_hall}>
                        ${halls.name}
                </option>
            </c:forEach>
        </select>

        <select name="exposition">
            <c:forEach items="${expositions}" var="expositions" >
                <option value = ${expositions.id_exp}>
                        ${expositions.theme}
                </option>
            </c:forEach>
        </select>

        <button type="submit" class="btn btn-info col-2"><h2><fmt:message key="button.save.schedule"/></h2></button>
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