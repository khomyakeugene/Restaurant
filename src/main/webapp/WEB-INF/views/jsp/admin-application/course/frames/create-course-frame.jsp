<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 07.09.2016
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="${pageContext.request.contextPath}/create-course" method="post">
    <%@ include file="../../frames/buttons/create-cancel-buttons-frame.jsp" %>
    <div class="horizontal-container ordinary-container">
        <div class="horizontal-part-holder ordinary-container">
            <div class="two-thirds">
                <%@ include file="course-frame.jsp" %>
            </div>

            <div class="third">
                <%@ include file="../../frames/photo/course-photo.jsp" %>
            </div>
        </div>
    </div>
</form>
