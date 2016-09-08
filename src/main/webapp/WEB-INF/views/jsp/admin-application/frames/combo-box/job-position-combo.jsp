<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.09.2016
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="jobPositions" type="List<com.company.restaurant.model.JobPosition>"--%>
<%--@elvariable id="jobPositionNames" type="List<String>"--%>
<%--@elvariable id="jobPositionName" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="sigle" path="jobPosition" id="jobPositionName"
             name="jobPositionName" cssClass="input-control">
    <%--<form:option selected="true" value="${jobPositionName}"/>--%>
    <form:options items="${jobPositions}" itemValue="name" itemLabel="name"
                  value="${jobPositionName}"/>
</form:select>
