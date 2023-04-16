<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
</head>
<body>
<h1>Menu</h1>
<c:forEach var="entry" items="${counterMap}">
    <tr>
        <td>${entry.key}</td>
        <td>${entry.value}</td>
    </tr>
</c:forEach>

<ul>
    <c:forEach items="${counterMap.entrySet()}" var="entry" varStatus="loop">
        <c:if test="${loop.index lt 5}">
            <li><a href="${entry.key}">${entry.key}</a></li>
        </c:if>
    </c:forEach>
</ul>
</body>
</html>