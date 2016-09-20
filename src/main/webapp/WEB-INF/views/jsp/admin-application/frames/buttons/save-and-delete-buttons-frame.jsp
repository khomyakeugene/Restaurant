<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 04.09.2016
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="currentObjectId" type="java.lang.Integer"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table>
    <tr>
        <td>
            <%@ include file="save-button.jsp" %>
        </td>
        <c:choose>
            <c:when test="${currentObjectId > 0}">
                <td>
                    <%@ include file="delete-button.jsp" %>
                </td>
            </c:when>
        </c:choose>
        <td>
            <%@ include file="cancel-button.jsp" %>
        </td>
        <td>
            <%@ include file="../../../frames/error/error-mesage-frame.jsp" %>
        </td>
    </tr>
</table>
