<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 09.09.2016
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="/search_orders" method="post">
    <%@ include file="order-history-search-frame.jsp" %>
    <div class="horizontal-container ordinary-container">
        <div class="horizontal-part-holder ordinary-container">
            <div class="two-thirds">
                <%@ include file="order-history-table-frame.jsp" %>
            </div>
            <div class="third">
            </div>
        </div>
    </div>
</form>
