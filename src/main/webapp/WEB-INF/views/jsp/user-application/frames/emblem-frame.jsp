<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.08.2016
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="restaurantName" type="com.company.restaurant.web.user.application.UserMainController"--%>
<%--@elvariable id="restaurantEmblemImage" type="com.company.restaurant.web.user.application.UserMainController"--%>

<table class="ordinary-container" style="width: 100%">
    <tr style="vertical-align: top">
        <td style="width: 33%">
            <div class="around-emblem-text">
                Restaurant
            </div>
        </td>
        <td style="width: 33%">
            <div class="image-to-centre">
                <img src="data:image/jpeg;base64,${restaurantEmblemImage}"/>
            </div>
        </td>
        <td style="width: 33%">
            <div class="around-emblem-text">
                ${restaurantName}
            </div>
        </td>
    </tr>
</table>
