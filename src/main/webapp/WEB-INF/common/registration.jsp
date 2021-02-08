<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="registration.form"/></title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/js/bootstrap.bundle.min.js" />" rel="script">
    <jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>

</head>

<body>
<section>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-8 col-lg-8 col-xl-6">
                <div class="row">
                    <div class="col text-center">
                        <h1>
                            <fmt:message key="button.login"/>
                        </h1>
                    </div>
                </div>

                <c:if test="${param.passwordsDifferent == true}">
                    <p style="color: orange"><fmt:message key="passwords.diff"/></p>
                </c:if>
                <c:if test="${param.dataInvalid == true}">
                    <p style="color: orange"><fmt:message key="invalid.input"/></p>
                </c:if>
                <c:if test="${param.success == true}">
                    <p style="color: green"><fmt:message key="reg.success"/></p>
                </c:if>
                <c:if test="${param.alreadyExist == true}">
                    <p style="color: darkred"><fmt:message key="user.exist"/></p>
                </c:if>

                <form method="post" action="${pageContext.request.contextPath}/app/registration">

                    <div class="row align-items-center">
                        <div class="col mt-4">
                            <input name="firstName" type="text" class="form-control"
                                   placeholder="<fmt:message key="input.firstname"/>">
                        </div>
                    </div>
                    <div class="row align-items-center">
                        <div class="col mt-4">
                            <input name="lastName" type="text" class="form-control"
                                   placeholder="<fmt:message key="input.lastname"/>">
                        </div>
                    </div>
                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <input name="email" type="email" class="form-control"
                                   placeholder="<fmt:message key="input.email"/>">
                        </div>
                    </div>
                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <input name="password" type="password" class="form-control"
                                   placeholder="<fmt:message key="input.password"/>">
                        </div>
                        <div class="col">
                            <input name="confirmPassword" type="password" class="form-control"
                                   placeholder="<fmt:message key="input.confirm.password"/>">
                        </div>
                    </div>
                    <button class="btn btn-primary mt-4">
                        <fmt:message key="label.submit"/>
                    </button>
                </form>
            </div>
        </div>


        <a class="" href="${pageContext.request.contextPath}/app/home">
            <fmt:message key="back.to.main"/>
        </a>

    </div>
    </div>
    </div>
</section>
</body>
</html>


