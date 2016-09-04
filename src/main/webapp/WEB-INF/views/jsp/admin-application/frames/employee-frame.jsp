<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 04.09.2016
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="employee" type="com.company.restaurant.model.Employee"--%>
<%--@elvariable id="jobPositionNames" type="List<String>"--%>
<%--@elvariable id="jobPositionName" type="String"--%>

<form action="/save-employee" method="post">
    <div class="horizontal-container ordinary-container">
        <div class="horizontal-part-holder ordinary-container">
            <div class="two-thirds">
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
                            <label class="input-label" for="jobPositionName">Job position:</label>
                        </td>
                        <td>
                            <form:select multiple="sigle" path="jobPositionName" id="jobPositionName"
                                         name="jobPositionName" cssClass="input-control">
                                <form:option selected="true" value="${jobPositionName}" />
                                <form:options items="${jobPositionNames}"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="input-label" for="employeePhoneNumber">Phone number:</label>
                        </td>
                        <td>
                            <input type="text" class="input-control" id="employeePhoneNumber"
                                   name="employeePhoneNumber" placeholder="Enter phone number"
                                   value="${employee.phoneNumber}"
                                   pattern="^\([0-9]{3}\)\s[0-9]{3}-[0-9]{4}$"/>
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
                <div>
                    <button type="submit" class="button green" name="save" value="save" formnovalidate>
                        Save
                    </button>
                </div>
            </div>

            <div class="third">
                <div class="ordinary-container">
                    <img class="img" style="max-width: 100%; margin-top: 0; margin-left: 15px;"
                         src="data:image/jpeg;base64,${employee.base64EncodePhoto}" alt="No employee photo"
                        name="employeePhoto"/>
                </div>
            </div>
        </div>
    </div>
</form>
