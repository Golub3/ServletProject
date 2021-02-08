<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>
        <fmt:message key="login.form"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signin.css">
<%--    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>--%>
    <jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
<%--    <jsp:include page="${pageContext.request.contextPath}/js/bootstrap_min.jsp"/>--%>
    <link href="<c:url value="${pageContext.request.contextPath}/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" />" rel="script">

</head>

<body>
<div class="form">


    <form class="form-horizontal" role="form" method="POST" action="${pageContext.request.contextPath}/app/login">


        <c:if test="${param.dataInvalid == true}">
            <p style="color: orange"><fmt:message key="invalid.input"/></p>
        </c:if>

        <c:if test="${param.userExist == false}">
            <p style="color: darkred"><fmt:message key="user.not.found"/></p>
        </c:if>


        <div class="form-group">
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                    <input id="email" type="text" class="form-control"
                           placeholder="<fmt:message key="input.email"/>" name="email">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                    <input id="password" type="password" class="form-control"
                           placeholder="<fmt:message key="input.password"/>" name="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary btn-sm">
                        <fmt:message key="login.form"/>
                    </button>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a class="" href="${pageContext.request.contextPath}/app/home">
                    <fmt:message key="back.to.main"/>
                </a>
            </div>
        </div>
    </form>

</div>

</body>
</html>