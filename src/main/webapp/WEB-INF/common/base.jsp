<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>

    <c:choose>
        <c:when test="${sessionScope.role == 'ADMIN'}">
            <title><fmt:message key="label.admin"/></title>
        </c:when>
        <c:when test="${sessionScope.role == 'USER'}">
            <title><fmt:message key="label.user"/></title>
        </c:when>
        <c:otherwise>
            <jsp:forward page="app/home"/>
        </c:otherwise>
    </c:choose>


    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
    <link href="<c:url value="${pageContext.request.contextPath}/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" />" rel="script">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css"/>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/personal-cabinet.css"/>--%>
</head>
<body>

<!--navbar-->

<c:choose>
    <c:when test="${sessionScope.role == 'ADMIN'}">
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/admin/navbar-cabinet.jsp"/>
    </c:when>
    <c:when test="${sessionScope.role == 'USER'}">
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/user/navbar-cabinet.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:forward page="app/home"/>
    </c:otherwise>
</c:choose>


<!--our content goes here-->
<div class="container content">
    <div class="row profile">

        <c:choose>
            <c:when test="${sessionScope.role == 'ADMIN'}">
                <jsp:include page="${pageContext.request.contextPath}/WEB-INF/admin/default-content.jsp"/>
            </c:when>
            <c:when test="${sessionScope.role == 'USER'}">
                <jsp:include page="${pageContext.request.contextPath}/WEB-INF/user/default-content.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:forward page="app/home"/>
            </c:otherwise>
        </c:choose>

    </div>
</div>


<%--<div class="container">--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-3">--%>
<%--        </div>--%>
<%--        <div class="col-md-9 ft">--%>
<%--            <footer class="footer">--%>
<%--                <div class="row">--%>
<%--                    <div class="col-md-4">--%>
<%--                        <span class="copyright">Copyright &copy; ExhibitionCentre 2021</span>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </footer>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
</body>
</html>



