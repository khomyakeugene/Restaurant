<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.09.2016
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="jobPositions" type="List<com.company.restaurant.model.JobPosition>"--%>
<%--@elvariable id="employee" type="com.company.restaurant.model.Employee"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="single" path="jobPositions" id="jobPositionId"
             name="jobPositionId" cssClass="input-control">
    <c:forEach items="${jobPositions}" var="jobPosition">
        <c:choose>
            <c:when test="${jobPosition.id eq employee.jobPosition.id}">
                <form:option value="${jobPosition.id}" selected="selected">${jobPosition.name}</form:option>
            </c:when>
            <c:otherwise>
                <form:option value="${jobPosition.id}">${jobPosition.name}</form:option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</form:select>
