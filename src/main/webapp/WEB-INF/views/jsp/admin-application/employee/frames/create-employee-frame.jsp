<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 04.09.2016
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="${pageContext.request.contextPath}/create-employee" method="post">
    <%@ include file="employee-frame.jsp" %>
    <%@ include file="../../frames/buttons/create-cancel-buttons-frame.jsp" %>
</form>
