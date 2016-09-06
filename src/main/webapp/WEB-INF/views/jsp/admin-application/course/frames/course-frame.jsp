<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.09.2016
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>

<div class="horizontal-container ordinary-container">
    <div class="horizontal-part-holder ordinary-container">
        <div class="two-thirds">
            <input type="hidden" name="courseId" value="${course.courseId}"/>
            <table style="width: 100%; padding-left: 10px; padding-right: 10px;">
                <tr>
                    <td>
                        <label class="input-label" for="courseName">Title (name)</label>
                    </td>
                    <td style="text-align: right">
                        <label class="input-label" for="courseWeight">Weight (kg)</label>
                    </td>
                    <td style="text-align: right">
                        <label class="input-label" for="courseCost">Cost (hrn)</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" class="input-control" id="courseName"
                               name="courseName" placeholder="Enter course name"
                               value="${course.name}" required="required" autofocus>
                    </td>
                    <td>
                        <input type="number" step="0.001" class="input-control" style="text-align: right"
                               id="courseWeight" name="courseWeight" placeholder="Enter course weight"
                               value="${course.weight}">
                    </td>
                    <td>
                        <input type="number" step="0.01" class="input-control" style="text-align: right"
                               id="courseCost" name="courseCost" placeholder="Enter course cost"
                               value="${course.cost}">
                    </td>
                </tr>
            </table>
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

        <div class="third">
            <div class="ordinary-container">
                <img class="img" style="max-width: 100%"
                     src="data:image/jpeg;base64,${course.base64EncodePhoto}" alt="No course photo"
                     name="coursePhoto"/>
            </div>
        </div>
    </div>
</div>
