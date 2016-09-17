<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 03.09.2016
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <link href="<c:url value="/resources/css/common.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main-menu.css"/>" rel="stylesheet">
</head>

<div class="main-menu ordinary-container">
    <ul>
        <li><a href="${pageContext.request.contextPath}/admin-menu">Menu</a></li>
        <li><a href="${pageContext.request.contextPath}/admin-course-list">Course</a></li>
        <li><a href="${pageContext.request.contextPath}/admin-employee-list">Personnel</a></li>
        <li><a href="${pageContext.request.contextPath}/admin-warehouse">Warehouse</a></li>
        <li><a href="${pageContext.request.contextPath}/admin-order-history">Order history</a></li>
    </ul>
</div>
