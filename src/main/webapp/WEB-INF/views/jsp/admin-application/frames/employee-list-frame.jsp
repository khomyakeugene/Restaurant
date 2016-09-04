<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 04.09.2016
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="employees" type="com.company.restaurant.web.user.application.UserEmployeeController"--%>
<%--@elvariable id="employee" type="com.company.restaurant.model.Employee"--%>

<%--<h3>Personnel</h3>--%>
<table class="admin-employee-table">
    <tr>
        <th>Name</th>
        <th>Job position</th>
        <th>Phone number</th>
        <th style="text-align: right">Salary</th>
    </tr>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td><b><a href="/employee/${employee.employeeId}">${employee.name}</b></td>
            <td>${employee.jobPosition.name}</td>
            <td>${employee.phoneNumber}</td>
            <td style="text-align: right">${employee.salary}</td>
        </tr>
    </c:forEach>
</table>
