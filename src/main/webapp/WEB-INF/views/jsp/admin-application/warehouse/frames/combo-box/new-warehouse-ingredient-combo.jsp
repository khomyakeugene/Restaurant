<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 12.09.2016
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="ingredients" type="Set<com.company.restaurant.model.Ingredient>"--%>
<%--@elvariable id="newIngredientId" type="Integer"--%>
<%--@elvariable id="newIngredientName" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="single" path="ingredients" id="newIngredientId"
             name="newIngredientId" cssClass="input-control" cssStyle="font-weight: bold">
    <form:option selected="true" value="${newIngredientId}" label="${newIngredientName}"/>
    <form:options items="${ingredients}" itemValue="ingredientId" itemLabel="name"/>
</form:select>
