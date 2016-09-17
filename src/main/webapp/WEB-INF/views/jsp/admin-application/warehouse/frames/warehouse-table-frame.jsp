<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 12.09.2016
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="warehouseContent" type="List<com.company.restaurant.model.Warehouse>"--%>
<%--@elvariable id="thisWarehouse" type="com.company.restaurant.model.Warehouse"--%>
<%--@elvariable id="newAmount" type="Float"--%>
<%--@elvariable id="warehouse" type="com.company.restaurant.model.Warehouse"--%>
<%--@elvariable id="filterIngredientName" type="java.lang.String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <table class="ingredient-table">
        <tr>
            <th>
                <c:choose>
                    <c:when test="${empty filterIngredientName}">
                        Ingredient
                    </c:when>
                    <c:otherwise>
                        Ingredient (${filterIngredientName})
                    </c:otherwise>
                </c:choose>
            </th>
            <th style="text-align: right" width="15%">Amount</th>
            <th style="text-align: left" width="10%">Measure</th>
            <th class="table-action" style="text-align: center" width="5%">action</th>
        </tr>
        <tr>
            <td>
                <%@ include file="combo-box/new-warehouse-ingredient-combo.jsp" %>
            </td>
            <td>
                <input type="number" step="0.001" class="input-control" style="text-align: right"
                       id="newAmount" name="newAmount" value="${newAmount}"
                       placeholder="Enter amount">
            </td>
            <td>
                <%@ include file="../../frames/combo-box/portion-combo.jsp" %>
            </td>
            <td class="table-action" style="text-align: center">
                <%@ include file="../../frames/buttons/add-button.jsp" %>
            </td>
        </tr>
        <c:forEach items="${warehouseContent}" var="thisWarehouse">
            <tr>
                <c:choose>
                    <c:when test="${thisWarehouse.ingredient eq warehouse.ingredient and thisWarehouse.portion eq warehouse.portion}">
                        <td><b>${warehouse.ingredient.name}</b></td>
                        <td>
                            <input type="number" step="0.001" class="input-control" style="text-align: right"
                                   id="amount" name="amount" value="${warehouse.amount}" autofocus>
                        </td>
                        <td style="text-align: left">${warehouse.portion.description}</td>
                        <td class="table-action" style="text-align: center">
                            <%@ include file="../../frames/buttons/table-action-save-button.jsp" %>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <a href="${pageContext.request.contextPath}/warehouse/edit-warehouse-ingredient/${thisWarehouse.ingredient.id}/${thisWarehouse.portion.id}">
                            ${thisWarehouse.ingredient.name}
                            </a>
                        </td>
                        <td style="text-align: right">${thisWarehouse.amount}</td>
                        <td style="text-align: left">${thisWarehouse.portion.description}</td>
                        <td class="table-action" style="text-align: center">
                            <a href="${pageContext.request.contextPath}/warehouse/delete-warehouse-ingredient/${thisWarehouse.ingredient.id}/${thisWarehouse.portion.id}"
                               onclick="return confirm('Are you sure you want to delete the data?')"
                            >delete</a>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>
