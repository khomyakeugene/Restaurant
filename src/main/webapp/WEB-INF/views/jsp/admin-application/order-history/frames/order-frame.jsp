<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 11.09.2016
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="order" type="com.company.restaurant.model.Order"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table>
    <tr>
        <td>Order time: </td>
        <td style="font-weight: bolder">
            <fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${order.orderDatetime}"/>
        </td>
        <td>Order number: </td>
        <td style="font-weight: bolder">${order.orderNumber}</td>
    </tr>
</table>
