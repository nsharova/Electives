<%--
  Created by IntelliJ IDEA.
  User: natalisharova
  Date: 04.06.2021
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add theme</title>
</head>
<body>
<form method="POST" action="?command=add_theme">
    <input id="name" name="name" type="text"/>
    <label for="name">Name</label>
    <c:if test="${not empty errors['name']}">
        <span>${errors['name']}</span>
    </c:if>

    <button>Create</button>
</form>
</body>
</html>
