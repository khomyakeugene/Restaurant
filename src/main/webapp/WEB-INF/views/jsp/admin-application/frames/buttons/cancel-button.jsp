<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 04.09.2016
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <button class="button green" onclick="goBack()" value="cancel">
        Cancel
    </button>

    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</div>
