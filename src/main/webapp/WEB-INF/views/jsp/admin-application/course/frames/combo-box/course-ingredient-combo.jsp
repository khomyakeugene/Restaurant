<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 09.09.2016
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="newIngredients" type="List<com.company.restaurant.model.Ingredient>"--%>
<%--@elvariable id="courseIngredientName" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="sigle" path="newIngredients" id="courseIngredientName"
             name="courseIngredientName" cssClass="input-control" cssStyle="font-weight: bold">
    <form:option selected="true" value="--- Select ingredient ---"/>
    <form:options items="${newIngredients}" itemLabel="name" itemValue="name"/>
</form:select>
