<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.09.2016
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="jobPositionNames" type="List<String>"--%>
<%--@elvariable id="jobPositionName" type="String"--%>

<form:select multiple="sigle" path="jobPositionNames" id="jobPositionName"
             name="jobPositionName" cssClass="input-control">
    <form:option selected="true" value="${jobPositionName}" />
    <form:options items="${jobPositionNames}"/>
</form:select>
