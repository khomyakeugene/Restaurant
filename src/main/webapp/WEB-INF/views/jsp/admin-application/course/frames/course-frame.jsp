<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 07.09.2016
  Time: 3:02
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>

<div>
    <table style="width: 100%; padding-left: 10px; padding-right: 10px;">
        <tr>
            <td style="width: 40%">
                <label class="input-label" for="courseName">Title (name)</label>
            </td>
            <td>
                <label class="input-label">Category</label>
            </td>
            <td style="text-align: right; width: 15%">
                <label class="input-label" for="courseWeight">Weight (kg)</label>
            </td>
            <td style="text-align: right; width: 15%">
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
                <%@ include file="../../frames/combo-box/course-category-combo.jsp" %>
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
</div>