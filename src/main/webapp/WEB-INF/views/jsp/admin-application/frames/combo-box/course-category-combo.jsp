<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 07.09.2016
  Time: 2:25
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="courseCategories" type="java.util.Collection<com.company.restaurant.model.CourseCategory>"--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="single" path="courseCategories" id="courseCategoryId"
             name="courseCategoryId" cssClass="input-control">
    <c:forEach items="${courseCategories}" var="courseCategory">
        <c:choose>
            <c:when test="${courseCategory.id eq course.courseCategory.id}">
                <form:option value="${courseCategory.id}" selected="selected">${courseCategory.name}</form:option>
            </c:when>
            <c:otherwise>
                <form:option value="${courseCategory.id}">${courseCategory.name}</form:option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</form:select>

