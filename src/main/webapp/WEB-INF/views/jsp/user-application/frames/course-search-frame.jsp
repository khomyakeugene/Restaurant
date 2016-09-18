<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 09.08.2016
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>

<form:form method="GET" action="${pageContext.request.contextPath}/search">
    <table class="ordinary-container" style="width: 100%">
        <tr style="vertical-align: top">
            <td style="width: 20%">
                <div style="margin: 15px">
                    <label class="input-label" for="courseName"><h3>Course</h3></label>
                </div>
            </td>
            <td style="width: 60%">
                <div>
                    <input type="text" class="course-search-input-control" id="courseName"
                           name="courseName" placeholder="Enter course name" required autofocus>
                </div>
            </td>
            <td style="width: 20%">
                <div style="margin: 15px">
                     <%@ include file="../../frames/buttons/search-button.jsp" %>
                 </div>
            </td>
        </tr>
    </table>
</form:form>
