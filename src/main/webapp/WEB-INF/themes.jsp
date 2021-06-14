<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Name</th>
    </tr>
    <c:forEach items="${requestScope.themes}" var="themes">
        <tr>
            <td><c:out value="${themes.name}"/></td>
            <td>
                <form method="POST" action="?command=delete_theme">
                    <input id="themeId" name="themeId" value="${themes.id}" hidden/>
                    <button>Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="?command=add_theme">Add theme</a>
</body>
</html>
