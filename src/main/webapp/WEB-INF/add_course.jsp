<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add course</title>
</head>
<body>
<form method="POST" action="?command=add_course">
    <div>
        <label for="name">Name</label>
        <input id="name" name="name" type="text"/>
        <c:if test="${not empty errors['name']}">
            <span>${errors['name']}</span>
        </c:if>
    </div>
    <div>
        <label for="description">Description</label>
        <input id="description" name="description" type="text"/>
        <c:if test="${not empty errors['description']}">
            <span>${errors['description']}</span>
        </c:if>
    </div>
    <div>
        <label for="duration">Duration</label>
        <input id="duration" name="duration" type="text"/>
        <c:if test="${not empty errors['duration']}">
            <span>${errors['duration']}</span>
        </c:if>
    </div>
    <div>
        <label for="level">Choose Level:</label>
        <select name="level" id="level">
            <c:forEach items="${requestScope.levels}" var="level">
                <option value="${level.accessLevel}">${level.name()}</option>
            </c:forEach>
        </select>
    </div>
    <%--! <div>
        <label for="ownerId">Choose moderator:</label>
        <select name="ownerId" id="ownerId">
            <c:forEach items="${requestScope.teachers}" var="owner">
                <option value="${owner.id}">${owner.firstName()}</option>
            </c:forEach>
        </select>
    </div> --%>
    <button>Create</button>
</form>
</body>
</html>
