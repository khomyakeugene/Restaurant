<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 07.09.2016
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="course" type="com.company.restaurant.model.Course"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="ordinary-container">
    <img class="img" style="max-width: 100%"
         src="data:image/jpeg;base64,${course.base64EncodePhoto}" alt="No course photo"
         name="coursePhoto"/>
</div>
