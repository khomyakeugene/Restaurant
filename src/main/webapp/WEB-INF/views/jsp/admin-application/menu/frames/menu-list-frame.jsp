<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 11.09.2016
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="${pageContext.request.contextPath}/edit-menus" method="post">
    <%@ include file="../../../frames/error/error-mesage-frame.jsp" %>
    <table style="width: 100%">
        <tr style="vertical-align: top">
            <td style="width: 50%">
                <div>
                    <%@ include file="menu-table-frame.jsp" %>
                </div>
            </td>
            <td>
                <div>
                    <%@ include file="menu-courses-list-frame.jsp" %>
                </div>
            </td>
        </tr>
    </table>
</form>
