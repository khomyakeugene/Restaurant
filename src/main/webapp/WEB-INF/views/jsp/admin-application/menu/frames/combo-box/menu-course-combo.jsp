<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 12.09.2016
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="newCourses" type="Collestion<com.company.restaurant.model.Course>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="single" path="newCourses" id="courseId"
             name="courseId" cssClass="input-control" cssStyle="font-weight: bold">
    <form:option selected="true" value="-1" label="--- Select course ---"/>
    <form:options items="${newCourses}" itemValue="courseId" itemLabel="name"/>
</form:select>
