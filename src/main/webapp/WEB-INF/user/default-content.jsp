<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<div class="col-md-9">
    <div class="profile-content">
        <h1><fmt:message key="label.user"/></h1>
    </div>
</div>