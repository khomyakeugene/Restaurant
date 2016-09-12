<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 12.09.2016
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="warehouseIngredients" type="Set<com.company.restaurant.model.Ingredient>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="single" path="warehouseIngredients" id="ingredientId"
             name="ingredientId" cssClass="input-control" cssStyle="font-weight: bold">
    <form:option selected="true" value="-1" label="--- Select ingredient ---"/>
    <form:options items="${warehouseIngredients}" itemValue="ingredientId" itemLabel="name"/>
</form:select>
