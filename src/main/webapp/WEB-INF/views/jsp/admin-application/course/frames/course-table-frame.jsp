<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.09.2016
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="courses" type="com.company.restaurant.web.admin.application.AdminCourseController"--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>

<div>
    <table class="course-table">
        <tr>
            <th>Title</th>
            <th style="text-align: right">Weight</th>
            <th style="text-align: right">Cost</th>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td><b><a href="/admin-course/${course.courseId}">${course.name}</a></b></td>
                <td style="text-align: right">${course.weight}</td>
                <td style="text-align: right">${course.cost}</td>
            </tr>
        </c:forEach>
    </table>
</div>
