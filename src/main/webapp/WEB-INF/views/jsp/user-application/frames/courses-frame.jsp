<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.08.2016
  Time: 17:37
  To change this template use File | FSettings | File Templates.
--%>
<%--@elvariable id="courses" type="com.company.restaurant.web.user.application.UserMainController"--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>

<div>
    <%@ include file="course-search-frame.jsp" %>

    <div>
        <table class="course-table">
            <tr>
                <th>Title</th>
                <th style="text-align: right">Weight (kg)</th>
                <th style="text-align: right">Cost (hrn)</th>
            </tr>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td><b><a href="${pageContext.request.contextPath}/course/${course.courseId}">${course.name}</a></b></td>
                    <td style="text-align: right">${course.weight}</td>
                    <td style="text-align: right">${course.cost}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
