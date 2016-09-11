<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.09.2016
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="ingredients" type="List<com.company.restaurant.model.Ingredient>"--%>
<%--@elvariable id="ingredientName" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="single" path="ingredients" id="ingredientName"
             name="ingredientName" cssClass="input-control">
    <form:option selected="true" value="--- Select ingredient ---"/>
    <form:options items="${ingredients}" itemLabel="name" itemValue="name"/>
</form:select>
