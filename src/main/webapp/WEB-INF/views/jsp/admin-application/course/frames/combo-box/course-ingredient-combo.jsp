<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 09.09.2016
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="newIngredients" type="Collection<com.company.restaurant.model.Ingredient>"--%>
<%--@elvariable id="newIngredientId" type="Integer"--%>
<%--@elvariable id="newIngredientName" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="single" path="newIngredients" id="courseIngredientId"
             name="courseIngredientId" cssClass="input-control" cssStyle="font-weight: bold">
    <form:option selected="true" value="${newIngredientId}" label="${newIngredientName}"/>
    <form:options items="${newIngredients}" itemValue="ingredientId" itemLabel="name"/>
</form:select>
