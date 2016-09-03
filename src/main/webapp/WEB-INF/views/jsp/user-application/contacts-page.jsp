<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.08.2016
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/jsp/properties/common-page-properties.jsp" %>

<html>
<body>
<%@ include file="/WEB-INF/views/jsp/user-application/frames/main-menu-frame.jsp" %>

<div class="horizontal-container ordinary-container">
    <div class="horizontal-part-holder ordinary-container">
        <div class="two-thirds">
            <%@ include file="/WEB-INF/views/jsp/user-application/frames/transport-map-frame.jsp" %>
        </div>
        <div class="third">
            <%@ include file="/WEB-INF/views/jsp/user-application/frames/contacts-frame.jsp" %>
        </div>
    </div>
</div>

</body>
</html>
