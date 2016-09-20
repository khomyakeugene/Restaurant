<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 19.09.2016
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="employee" type="com.company.restaurant.model.Employee"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <input type="hidden" name="employeeId" value="${employee.employeeId}"/>
    <table class="admin-employee-data-table">
        <tr>
            <td>
                <label class="input-label" for="employeeFirstName">First name:</label>
            </td>
            <td>
                <input type="text" class="input-control" id="employeeFirstName"
                       name="employeeFirstName" placeholder="Enter first name"
                       value="${employee.firstName}" required="required" autofocus>
            </td>
        </tr>
        <tr>
            <td>
                <label class="input-label" for="employeeSecondName">Last name:</label>
            </td>
            <td>
                <input type="text" class="input-control" id="employeeSecondName"
                       name="employeeSecondName" placeholder="Enter last name"
                       value="${employee.secondName}" required="required">
            </td>
        </tr>
        <tr>
            <td>
                <label class="input-label">Job position:</label>
            </td>
            <td>
                <%@ include file="../../frames/combo-box/job-position-combo.jsp" %>
            </td>
        </tr>
        <tr>
            <td>
                <label class="input-label" for="employeePhoneNumber">Phone number:</label>
            </td>
            <td>
                <input type="text" class="input-control" id="employeePhoneNumber"
                       name="employeePhoneNumber" placeholder="Enter phone number"
                       value="${employee.phoneNumber}"/>
            </td>
        </tr>
        <tr>
            <td>
                <label class="input-label" for="employeeSalary">Salary:</label>
            </td>
            <td>
                <input type="number" step="0.01" class="input-control" id="employeeSalary"
                       name="employeeSalary" placeholder="Enter salary"
                       value="${employee.salary}">
            </td>
        </tr>
    </table>
</div>
