<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<c:forEach items="${requestScope.users}" var="user">
    <h1>Hello, <c:out value="${user.firstName}" default="Anonymous"/> <c:out value="${user.lastName}" default="Anonymous"/>,
        your login is <c:out value="${user.login}" default="Login"/> </h1>
</c:forEach>
</body>
</html>
