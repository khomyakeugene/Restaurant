<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 12.09.2016
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table style="border-spacing: 10px">
    <tr>
        <td style="width: 40%">
            <%@ include file="combo-box/warehouse-ingredient-combo.jsp" %>
        </td>
        <td>
            <%@ include file="../../../frames/buttons/search-button.jsp" %>
        </td>
        <td style="width: 60%">
            <%@ include file="../../../frames/error/error-mesage-frame.jsp" %>
        </td>
    </tr>
</table>