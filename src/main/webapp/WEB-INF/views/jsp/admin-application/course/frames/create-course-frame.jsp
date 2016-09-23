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

    <table class="ordinary-container" style="width: 100%">
        <tr style="vertical-align: top">
            <td style="width: 66%">
                <div>
                    <%@ include file="course-frame.jsp" %>
                </div>
            </td>
            <td>
                <div>
                    <%@ include file="photo/course-photo.jsp" %>
                </div>
            </td>
        </tr>
    </table>
</form>
