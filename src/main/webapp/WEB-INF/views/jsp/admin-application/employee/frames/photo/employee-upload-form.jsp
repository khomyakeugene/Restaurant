<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 19.09.2016
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<html>
<body>
<form method="post" id="form" action="${pageContext.request.contextPath}/upload-employee-photo"
      enctype="multipart/form-data">
    <div>
        <input type="file" id="file" name="file" class="button green" onchange="this.form.submit();"/>
    </div>
</form>
</body>
</html>

