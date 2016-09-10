<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 10.09.2016
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="tables" type="java.util.Collection<com.company.restaurant.model.Table>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="sigle" path="tables" id="tableId"
             name="tableId" cssClass="input-control" cssStyle="font-weight: bold">
    <form:option selected="true" value="--- table ---"/>
    <form:options items="${tables}" itemLabel="name" itemValue="employeeId"/>
</form:select>
