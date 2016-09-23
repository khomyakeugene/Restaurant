<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 07.09.2016
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="employee" type="com.company.restaurant.model.Employee"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="ordinary-container">
    <img class="img" style="max-width: 100%; margin-top: 0; margin-left: 15px;"
         src="data:image/jpeg;base64,${employee.base64EncodePhoto}" alt="No employee photo"
         name="employeePhoto"/>
</div>
<div>
    <%@ include file="employee-upload-form.jsp" %>
</div>
