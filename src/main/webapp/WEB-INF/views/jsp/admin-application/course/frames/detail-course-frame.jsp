<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.09.2016
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="horizontal-container ordinary-container">
    <div class="horizontal-part-holder ordinary-container">
        <div class="two-thirds">
            <input type="hidden" name="courseId" value="${course.courseId}"/>
            <%@ include file="course-frame.jsp" %>
            <%@ include file="course-ingredient-list-frame.jsp" %>
        </div>

        <div class="third">
            <%@ include file="../../frames/photo/course-photo.jsp" %>
        </div>
    </div>
</div>
