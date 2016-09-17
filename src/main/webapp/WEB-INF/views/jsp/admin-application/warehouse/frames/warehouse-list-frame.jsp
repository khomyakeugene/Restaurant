<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 12.09.2016
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="${pageContext.request.contextPath}/warehouse-action" method="post">
    <%@ include file="warehouse-search-frame.jsp" %>
    <%@ include file="warehouse-table-frame.jsp" %>
</form>
