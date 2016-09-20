<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 21.09.2016
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="${pageContext.request.contextPath}/save-or-delete-employee" method="post">
    <table style="width: 100%">
        <tr>
            <td>
                <%@ include file="../../frames/buttons/save-and-delete-buttons-frame.jsp" %>
            </td>
        </tr>
        <tr>
            <td>
                <%@ include file="employee-attributes-frame.jsp" %>
            </td>
        </tr>
    </table>
</form>
