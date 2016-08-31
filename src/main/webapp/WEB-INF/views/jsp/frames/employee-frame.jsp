<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.08.2016
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="employees" type="com.company.restaurant.web.EmployeeController"--%>
<%--@elvariable id="employee" type="com.company.restaurant.model.Employee"--%>

<h3>Our personnel</h3>
<table class="employee-table">
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td><b>${employee.firstName}</b></td>
            <td>${employee.jobPosition.name}</td>
            <td>
                <img src="data:image/jpeg;base64,${employee.base64EncodePhoto}"
                     height="70" width="70"/>
            </td>
        </tr>
    </c:forEach>
</table>

