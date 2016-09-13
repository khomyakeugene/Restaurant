<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 09.09.2016
  Time: 3:14
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="portions" type="List<com.company.restaurant.model.Portion>"--%>
<%--@elvariable id="newPortionId" type="Integer"--%>
<%--@elvariable id="newPortionName" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="single" path="portions" id="portionId"
             name="portionId" cssStyle="font-weight: bold">
    <form:option selected="true" value="${newPortionId}" label="${newPortionName}"/>
    <form:options items="${portions}"  itemValue="portionId" itemLabel="description"/>
</form:select>
