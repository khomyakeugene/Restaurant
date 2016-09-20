<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 19.09.2016
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Upload a file please</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/upload-employee-photo"
      enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit"/>
</form>
</body>
</html>

