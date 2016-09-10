<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 09.09.2016
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
    <tr>
        <td>
            <%@ include file="combo-box/order-date-combo.jsp" %>
        </td>
        <td>
            <%@ include file="combo-box/order-waiter-combo.jsp" %>
        </td>
        <td>
            <%@ include file="combo-box/order-table-combo.jsp" %>
        </td>
        <td>
            <%@ include file="../../../frames/buttons/search-button.jsp" %>
        </td>
        <td>
            <%@ include file="../../../frames/error/error-mesage-frame.jsp" %>
        </td>
    </tr>
</table>