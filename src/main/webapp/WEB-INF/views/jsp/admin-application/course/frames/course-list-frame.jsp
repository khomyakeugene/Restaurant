<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.09.2016
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="${pageContext.request.contextPath}/prepare-new-course" method="post">
    <%@ include file="../../frames/buttons/create-button-frame.jsp" %>
    <%@ include file="course-table-frame.jsp" %>
</form>
