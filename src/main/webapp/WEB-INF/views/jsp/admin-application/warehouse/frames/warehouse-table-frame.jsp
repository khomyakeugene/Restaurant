<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 12.09.2016
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="warehouseContent" type="List<com.company.restaurant.model.Warehouse>"--%>
<%--@elvariable id="warehouse" type="com.company.restaurant.model.Warehouse"--%>
<%--@elvariable id="newAmount" type="Float"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <table class="ingredient-table">
        <tr>
            <th>Ingredient</th>
            <th style="text-align: right" width="15%">Amount</th>
            <th style="text-align: left" width="10%">Measure</th>
            <th class="table-action" style="text-align: center" width="5%">action</th>
        </tr>
        <tr>
            <td><%@ include file="combo-box/new-warehouse-ingredient-combo.jsp" %></td>
            <td>
                <input type="number" step="0.001" class="input-control" style="text-align: right"
                       id="amount" name="amount" value="${newAmount}"
                       placeholder="Enter amount">
            </td>
            <td><%@ include file="../../frames/combo-box/portion-combo.jsp" %></td>
            <td class="table-action" style="text-align: center">
                <%@ include file="../../frames/buttons/add-button.jsp" %>
            </td>
        </tr>
        <c:forEach items="${warehouseContent}" var="warehouse">
            <tr>
                <td>${warehouse.ingredient.name}</td>
                <td style="text-align: right">${warehouse.amount}</td>
                <td style="text-align: left">${warehouse.portion.description}</td>
                <td class="table-action" style="text-align: center">
                    <a href="/warehouse/delete-warehouse-ingredient/${warehouse.ingredient.id}/${warehouse.portion.id}"/>
                    delete
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
