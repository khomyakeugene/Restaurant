<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 07.09.2016
  Time: 2:25
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="courseCategories" type="List<com.company.restaurant.model.CourseCategory>"--%>
<%--@elvariable id="courseCategoryName" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="sigle" path="courseCategories" id="courseCategoryName"
             name="courseCategoryName" cssClass="input-control">
    <form:option selected="true" value="${courseCategoryName}" />
    <form:options items="${courseCategories}" itemLabel="name" itemValue="name"/>
</form:select>
