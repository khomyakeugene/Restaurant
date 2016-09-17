<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.09.2016
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="${pageContext.request.contextPath}/save-or-delete-course" method="post">
    <%@ include file="../../frames/buttons/save-and-delete-buttons-frame.jsp" %>
    <%@ include file="detail-course-frame.jsp" %>
</form>
