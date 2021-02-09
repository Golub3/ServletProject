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
    <link href="<c:url value="${pageContext.request.contextPath}/css/bootstrap.min.css" />" rel="stylesheet">
    <jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/admin/navbar-cabinet.jsp"/>
<main class="container-md">

    <a href="${pageContext.request.contextPath}/app/createSchedulePage" class="btn btn-primary btn-sm mb-3">
        <fmt:message key="button.create.schedule"/> </a>

    <form action="${pageContext.request.contextPath}/app/schedules?currentPage=${currentPage}
&${sort_way}=exposition.theme&${dir_way}=asc"
          method="POST">

        <input type="date" name=start value=${start_date}
               placeholder="fromDate" class="form-control mb-2 col-2">
        <input type="date" name=end value=${end_date}
               placeholder="toDate" class="form-control mb-2 col-2">

        <button type="submit" class="btn btn-info col-2"><fmt:message key="button.filter"/></button>
    </form>

    <div style="margin-left: 45px;">
        <b style="font-size: 18px;">sort</b>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           href="${pageContext.request.contextPath}/app/schedules?currentPage=${currentPage}
&${sort_way}=exposition.theme&${dir_way}=asc">
            sort theme asc
        </a>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           href="${pageContext.request.contextPath}/app/schedules?currentPage=${currentPage}
&${sort_way}=exposition.theme&${dir_way}=desc">
            sort theme desc
        </a>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           href="${pageContext.request.contextPath}/app/schedules?currentPage=${currentPage}
&${sort_way}=exposition.price&${dir_way}=asc">
            sort price asc
        </a>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           href="${pageContext.request.contextPath}/app/schedules?currentPage=${currentPage}
&${sort_way}=exposition.price&${dir_way}=desc">
            sort price desc
        </a>
    </div>

    <table class="table">
        <thead class="thead-dark">
        <tr>
<%--            <a th:href="@{'/schedules/page/' + ${currentPage} + '?sortField=exposition.theme&sortDir=' + ${reverseSortDir}}"--%>
<%--               th:text="#{expositions.theme}">--%>
<%--                theme</a>--%>
            <th><fmt:message key="expositions.theme"/></th>
            <th><fmt:message key="expositions.name"/></th>
            <th><fmt:message key="expositions.date"/></th>
            <th><fmt:message key="expositions.time_start"/></th>
            <th><fmt:message key="expositions.time_end"/></th>
            <th><fmt:message key="expositions.price"/></th>
            <th><fmt:message key="actions"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="schedules" items="${schedules}">
            <tr>
                <td><c:out value="${schedules.exposition.theme}"/></td>
                <td><c:out value="${schedules.hall.name}"/></td>
                <td><c:out value="${schedules.date}"/></td>
                <td><c:out value="${schedules.time_start}"/></td>
                <td><c:out value="${schedules.time_end}"/></td>
                <td><c:out value="${schedules.exposition.price}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/app/deleteSchedule?id=${schedules.id}" class="btn btn-danger">
                        <fmt:message key="button.delete"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<nav aria-label="pager">
    <ul class="pagination">

        <c:if test="${currentPage != 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/app/schedules?currentPage=${currentPage - 1}
&${sort_way}=${sort_field}&${dir_way}=${dir}">
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
                           href="${pageContext.request.contextPath}/app/schedules?currentPage=${i}&
${sort_way}=${sort_field}&${dir_way}=${dir}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>


        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/app/schedules?currentPage=${currentPage + 1}&
${sort_way}=${sort_field}&${dir_way}=${dir}">
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
