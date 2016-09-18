<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 09.09.2016
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="${pageContext.request.contextPath}/admin-search-orders" method="post">
    <%@ include file="order-history-search-frame.jsp" %>
    <table class="ordinary-container" style="width: 100%">
        <tr style="vertical-align: top">
            <td style="width: 60%">
                <div>
                     <%@ include file="order-history-table-frame.jsp" %>
               </div>
            </td>
            <td>
                <div>
                    <%@ include file="order-courses-list-frame.jsp" %>
                </div>
            </td>
        </tr>
    </table>
</form>
