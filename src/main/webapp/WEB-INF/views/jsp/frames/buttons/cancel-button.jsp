<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 06.09.2016
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <button class="button green" onclick="goBack()" name="submitButtonValue" value="cancel">
        Cancel
    </button>

    <script>
        function goBack() {
            window.history.back();
        }
    </script>

    <%--<button type="submit" class="button green" name="submitButtonValue" value="cancel">--%>
    <%--Cancel--%>
    <%--</button>--%>
</div>
