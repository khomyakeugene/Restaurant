<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 09.09.2016
  Time: 3:14
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="portions" type="List<com.company.restaurant.model.Portion>"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="sigle" path="portions" id="coursePortionDescription"
             name="coursePortionDescription" cssStyle="font-weight: bold">
    <form:option selected="true" value="- portion -"/>
    <form:options items="${portions}" itemLabel="description" itemValue="description"/>
</form:select>