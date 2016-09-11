<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 10.09.2016
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="orderDates" type="java.util.Set<String>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="sigle" path="orderDates" id="orderDate"
             name="orderDate" cssClass="input-control" cssStyle="font-weight: bold">
    <form:option selected="true" value="" label="--- Select order date ---"/>
    <form:options items="${orderDates}" />
</form:select>
