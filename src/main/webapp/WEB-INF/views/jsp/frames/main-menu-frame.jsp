<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 17.09.2016
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <link href="<c:url value="/resources/css/common.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main-menu.css"/>" rel="stylesheet">
</head>

<div class="main-menu ordinary-container">
    <ul>
        <li><a href="${pageContext.request.contextPath}/user-application-main">User application</a></li>
        <li><a href="${pageContext.request.contextPath}/admin-menu">Admin application</a></li>
    </ul>
</div>
