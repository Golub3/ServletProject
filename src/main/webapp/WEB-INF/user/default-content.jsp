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
    <link href="<c:url value="${pageContext.request.contextPath}/css/bootstrap.min.css" />" rel="stylesheet">
</head>
<body>
<h1><fmt:message key="bought.tickets"/></h1>
<div class="col-md-9">
    <div class="container-md">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th><fmt:message key="expositions.theme"/></th>
                <th><fmt:message key="expositions.price"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tickets" items="${tickets}">
                <tr>
                    <td><c:out value="${tickets.exposition.theme}"/></td>
                    <td><c:out value="${tickets.exposition.price}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <nav aria-label="pager">
            <ul class="pagination">

                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/app/personal-cabinet?currentPage=${currentPage - 1}">
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
                                   href="${pageContext.request.contextPath}/app/personal-cabinet?currentPage=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>


                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/app/personal-cabinet?currentPage=${currentPage + 1}">
                        <fmt:message key="pager.next.page"/>
                    </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
</body>
