<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<nav class="navbar navbar navbar-expand-lg navbar-inverse navbar-fixed-top navbar-dark bg-dark">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-collapse"
                aria-controls="navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand js-scroll-trigger">
            <fmt:message key="greeting"/>
        </a>

        <div>
            <ul class="navbar-nav text-uppercase ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/schedules">
                        <i class="fa fa-user"></i>
                        <fmt:message key="navigation.section.expositions"/>
                    </a>
                </li>
                <li class="nav-item en-icon" style="margin-left: 0 !important;">
                    <a class="nav-link" href="?sessionLocale=en">
                        <img class="lang-icon" src="${pageContext.request.contextPath}/image/eng.png"
                             alt="<fmt:message key="lang.en"/>">
                        <%--<span class="flag-icon flag-icon-us"></span><fmt:message key="lang.en"/>--%>
                    </a>
                </li>
                <li class="nav-item ua-icon">
                    <a class="nav-link" href="?sessionLocale=ua">
                        <img class="lang-icon" src="${pageContext.request.contextPath}/image/ukr.png"
                             alt="<fmt:message key="lang.ua"/>">
                        <%--<span class="flag-icon flag-icon-mx"></span><fmt:message key="lang.ua"/>--%>
                    </a>
                </li>


                <li class="nav-item logout-btn">
                    <a class="btn btn-primary my-2 my-sm-0" href="${pageContext.request.contextPath}/app/logout" type="submit">
                        <i class="fa fa-sign-out"></i>
                        <fmt:message key="navigation.section.logout"/>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>