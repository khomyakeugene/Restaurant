<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 11.09.2016
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="menu" type="com.company.restaurant.model.Menu"--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <table class="course-table">
        <tr>
            <th>Course</th>
            <th class="table-action" style="text-align: center; width: 20%">action</th>
        </tr>
        <tr>
            <td>
                <%@ include file="combo-box/menu-course-combo.jsp" %>
            </td>
            <td class="table-action" style="text-align: center">
                <%@ include file="buttons/add-course-button.jsp" %>
            </td>
        </tr>
        <c:forEach items="${menu.courses}" var="course">
            <tr>
                <td><b><a href="${pageContext.request.contextPath}/admin-course/${course.courseId}">${course.name}</a></b></td>
                <td class="table-action" style="text-align: center">
                    <a href="${pageContext.request.contextPath}/admin-menu/delete-menu-course/${menu.menuId}/${course.courseId}"
                       onclick="return confirm('Are you sure you want to delete the data?')"
                    >delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
