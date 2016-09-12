<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 09.09.2016
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="orders" type="List<com.company.restaurant.model.Order>"--%>
<%--@elvariable id="orderDatePresentation" type="String"--%>
<%--@elvariable id="orderTableNumberPresentation" type="String"--%>
<%--@elvariable id="orderWaiterNamePresentation" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="admin-order-history-table">
    <tr>
        <th style="width: 23%">Date&Time ${orderDatePresentation}</th>
        <th style="width: 3%">State</th>
        <th style="width: 10%">Number</th>
        <th style="width: 5%">Table ${orderTableNumberPresentation}</th>
        <th>Waiter ${orderWaiterNamePresentation}</th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${order.orderDatetime}"/></td>
            <td>${order.state.name}</td>
            <td><b><a href="/admin-order/${order.orderId}"/>${order.orderNumber}</b></td>
            <td>${order.table.number}</td>
            <td>${order.waiter.name}</td>
        </tr>
    </c:forEach>
</table>
