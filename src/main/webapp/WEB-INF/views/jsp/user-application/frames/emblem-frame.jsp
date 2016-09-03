<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.08.2016
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="restaurantName" type="com.company.restaurant.web.user.application.UserMainController"--%>
<%--@elvariable id="restaurantEmblemImage" type="com.company.restaurant.web.user.application.UserMainController"--%>

<div class="horizontal-container ordinary-container">
    <div class="horizontal-part-holder">
        <div class="third around-emblem-text">
            Restaurant
        </div>
        <div class="third image-to-centre">
            <img src="data:image/jpeg;base64,${restaurantEmblemImage}"/>
        </div>
        <div class="third around-emblem-text">
            ${restaurantName}
        </div>
    </div>
</div>
