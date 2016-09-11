<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 11.09.2016
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="menus" type="com.company.restaurant.web.admin.application.AdminMenuController"--%>
<%--@elvariable id="menu" type="com.company.restaurant.model.Menu"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <table class="menu-table">
        <tr>
            <th>Menu name</th>
            <th class="table-action" style="text-align: center; width: 17%">action</th>
        </tr>
        <tr>
            <td>
                <input type="text" class="input-control" id="menuName"
                       name="menuName" placeholder="Enter menu name"
                       autofocus>
            </td>
            <td class="table-action" style="text-align: center" >
                <%@ include file="buttons/add-menu-button.jsp" %>
            </td>
        </tr>
        <c:forEach items="${menus}" var="menu">
            <tr>
                <td><b><a href="/admin-menu/${menu.menuId}">${menu.name}</a></b></td>
                <td class="table-action" style="text-align: center">
                    <a href="/admin-menu/delete-menu/${menu.menuId}"/>
                    delete
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
