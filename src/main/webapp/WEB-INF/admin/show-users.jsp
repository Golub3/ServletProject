<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>
        <fmt:message key="navigation.section.users"/>
    </title>
    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="${pageContext.request.contextPath}/css/bootstrap.min.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/admin/navbar-cabinet.jsp"/>
<div class="container-md">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th><fmt:message key="users.firstname"/></th>
                <th><fmt:message key="users.lastname"/></th>
                <th><fmt:message key="users.email"/></th>
                <th><fmt:message key="users.role"/></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="users" items="${users}">
            <tr>
                <td><c:out value="${users.firstName}"/></td>
                <td><c:out value="${users.lastName}"/></td>
                <td><c:out value="${users.email}"/></td>
                <td><c:out value="${users.role}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <nav aria-label="pager">
        <ul class="pagination">

            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/app/show-users?currentPage=${currentPage - 1}">
                        <fmt:message key="pager.prev.page"/>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active">
                            <a class="page-link"
                               href="#"> ${i} </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/app/show-users?currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>


            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/app/show-users?currentPage=${currentPage + 1}">
                    <fmt:message key="pager.next.page"/>
                </a>
                </li>
            </c:if>
        </ul>
    </nav>

    <br>
    <div class="home">
        <a class="" href="${pageContext.request.contextPath}/app/personal-cabinet">
            <fmt:message key="back.to.main"/>
        </a>
    </div>
</div>


</body>
</html>
