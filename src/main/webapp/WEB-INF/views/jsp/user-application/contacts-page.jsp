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

<table class="ordinary-container" style="width: 100%">
    <tr style="vertical-align: top">
        <td style="width: 66%">
            <div>
                <%@ include file="/WEB-INF/views/jsp/user-application/frames/transport-map-frame.jsp" %>
            </div>
        </td>
        <td>
            <div>
                <%@ include file="/WEB-INF/views/jsp/user-application/frames/contacts-frame.jsp" %>
            </div>
        </td>
    </tr>
</table>

</body>
</html>
