<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 07.09.2016
  Time: 3:06
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>
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

        </tr>
        <c:forEach items="${course.courseIngredients}" var="courseIngredient">
            <tr>
                <td><b>${courseIngredient.ingredient.name}</b></td>
                <td style="text-align: right">${courseIngredient.amount}</td>
                <td style="text-align: left">${courseIngredient.portion.description}</td>
                <td class="table-action" style="text-align: center">
                    <a href="/admin-course/delete-course-ingredient/${course.courseId}/${courseIngredient.ingredient.ingredientId}"
                       onclick="return confirm('Are you sure you want to delete the data?')"
                    />
                        delete
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><%@ include file="combo-box/course-ingredient-combo.jsp" %></td>
            <td>
                <input type="number" step="0.001" class="input-control" style="text-align: right"
                       id="courseIngredientAmount" name="courseIngredientAmount"
                       placeholder="Enter amount">
            </td>
            <td><%@ include file="../../frames/combo-box/portion-combo.jsp" %></td>
            <td class="table-action" style="text-align: center">
                <%@ include file="../../frames/buttons/add-button.jsp" %>
            </td>
        </tr>
    </table>
</div>
