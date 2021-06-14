
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>
<h1>Вход в систему</h1><br/>
<form method="get" action="${pageContext.request.contextPath}/electives/login">

    <input type="text" name="login"><br/>
    <input type="password" name="password"><br/><br/>
    <input class="button" type="submit" value="LogIn">

</form>
<br/>
<a href="${pageContext.request.contextPath}/electives/logout">Back to main page</a>

</body>
</html>
