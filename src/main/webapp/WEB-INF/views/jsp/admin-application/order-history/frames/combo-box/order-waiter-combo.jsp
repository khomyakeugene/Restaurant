<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.09.2016
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="orderWaiters" type="java.util.TreeMap<java.lang.Integer,java.lang.String>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:select multiple="sigle" path="orderWaiters" id="waiterId"
             name="waiterId" cssClass="input-control" cssStyle="font-weight: bold">
    <form:option selected="true" value="--- Select waiter ---"/>
    <form:options items="${orderWaiters}" />
</form:select>
