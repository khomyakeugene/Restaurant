<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 09.09.2016
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="orders" type="List<com.company.restaurant.model.Order>"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="admin-order-history-table">
    <tr>
        <th style="width: 15%">Order datetime</th>
        <th style="width: 5%">State</th>
        <th style="width: 10%">Order number</th>
        <th style="width: 5%">Table</th>
        <th style="width: 30%">Waiter</th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td><time datetime="YYYY-MM-DD hh:mm:ss">${order.orderDatetime}</time></td>
            <td>${order.state.name}</td>
            <td><b><a href="/order/${order.orderId}"/>${order.orderNumber}</b></td>
            <td>${order.table.number}</td>
            <td>${order.waiter.name}</td>
        </tr>
    </c:forEach>
</table>
