<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 11.09.2016
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="order" type="com.company.restaurant.model.Order"--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <table class="course-table">
        <tr>
            <th>Course</th>
            <th style="text-align: right; width: 20%">Cost (hrn)</th>
        </tr>
        <c:forEach items="${order.courses}" var="course">
            <tr>
                <td><b><a href="/admin-course/${course.courseId}">${course.name}</a></b></td>
                <td style="text-align: right">${course.cost}</td>
            </tr>
        </c:forEach>
    </table>
</div>
