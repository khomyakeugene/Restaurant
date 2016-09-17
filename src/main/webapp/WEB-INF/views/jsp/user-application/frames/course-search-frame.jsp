<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 09.08.2016
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>

<form:form method="GET" action="${pageContext.request.contextPath}/search">
    <div class="course-search-container">
        <div class="horizontal-part-holder">
            <div class="fifth">
                <label class="input-label" for="courseName"><h3>Course</h3></label>
            </div>

            <div class="three-fifths">
                <input type="text" class="course-search-input-control" id="courseName"
                       name="courseName" placeholder="Enter course name" required autofocus>
            </div>

            <div class="fifth">
                <div style="margin: 15px">
                    <%@ include file="../../frames/buttons/search-button.jsp" %>
                </div>
            </div>
        </div>
    </div>
</form:form>
