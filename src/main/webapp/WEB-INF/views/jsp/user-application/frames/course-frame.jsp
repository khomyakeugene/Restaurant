<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.08.2016
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>

<table class="ordinary-container" style="width: 100%">
    <tr style="vertical-align: top">
        <td style="width: 66%">
            <div>
                <div>
                    <div>
                        <h1>${course.name}</h1>
                    </div>
                    <div>
                        <h3>Weight: ${course.weight} kg, Cost: ${course.cost} hrn</h3>
                    </div>
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
                </div>
            </div>
        </td>
        <td>
            <div>
                <div class="ordinary-container">
                    <img class="img" style="max-width: 100%"
                         src="data:image/jpeg;base64,${course.base64EncodePhoto}" alt="No course photo"
                         name="coursePhoto"/>
                </div>
            </div>
        </td>
    </tr>
</table>
