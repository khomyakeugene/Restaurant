<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.08.2016
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="restaurantAddress" type="com.company.restaurant.web.MainController"--%>
<%--@elvariable id="restaurantEMail" type="com.company.restaurant.web.MainController"--%>
<%--@elvariable id="restaurantPhoneNumbers" type="com.company.restaurant.web.MainController"--%>

<h3>Contacts</h3>
<table class="standard-table">
    <tr>
        <td>Address :</td>
        <td><b>${restaurantAddress}</b></td>
    </tr>
    <tr>
        <td>Phone numbers: </td>
        <td>
            <table>
                <c:forEach items="${restaurantPhoneNumbers}" var="phoneNumber">
                    <tr>
                        <td><b>${phoneNumber}</b></td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <td>e-mail :</td>
        <td><b>${restaurantEMail}</b></td>
    </tr>
</table>
