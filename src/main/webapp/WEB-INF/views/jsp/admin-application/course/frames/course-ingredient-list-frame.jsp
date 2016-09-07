<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 07.09.2016
  Time: 3:06
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>

<div>
    <table class="ingredient-table">
        <tr>
            <th>Ingredient</th>
            <th style="text-align: right">Amount</th>
        </tr>
        <c:forEach items="${course.courseIngredients}" var="courseIngredient">
            <tr>
                <td><b>${courseIngredient.ingredient.name}</b></td>
                <td style="text-align: right">${courseIngredient.amount}
                        ${courseIngredient.portion.description}</td>
            </tr>
        </c:forEach>
    </table>
</div>
