<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses</title>
</head>
<body>

<c:when test="${sessionScope.loggedUser.userRole == 1}">
   hello, admin
</c:when>



<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Duration</th>
        <th>Owner Id</th>
        <th>User Level</th>
    </tr>
    <c:forEach items="${requestScope.courses}" var="course">
        <tr>
            <td><c:out value="${course.name}"/></td>
            <td><c:out value="${course.description}"/></td>
            <td><c:out value="${course.duration}"/></td>
            <td><c:out value="${course.ownerId}"/></td>
            <td><c:out value="${course.userLevel}"/></td>
            <td>
                <form method="POST" action="?command=delete_course">
                    <input id="courseId" name="courseId" value="${course.id}" hidden/>
                    <button>Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="?command=add_course">Add course</a>

</body>
</html>
