<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 11.09.2016
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="/edit-menus" method="post">
    <%@ include file="../../../frames/error/error-mesage-frame.jsp" %>
    <div class="horizontal-container ordinary-container">
        <div class="horizontal-part-holder ordinary-container">
            <div class="half">
                <%@ include file="menu-table-frame.jsp" %>
            </div>
            <div class="half">
                 <%@ include file="menu-courses-list-frame.jsp" %>
           </div>
        </div>
    </div>
</form>
