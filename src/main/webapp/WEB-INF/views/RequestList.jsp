<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requests</title>
</head>
<body>
<h2>Requests:</h2>
<table border="1">
    <tr>
        <td>Country</td>
        <td>Month</td>
        <td>Start</td>
        <td>End</td>
    </tr>
    <c:forEach var="request" items="${requests}">
        <tr>
            <td>
                <c:out value="${request.country}"/>
            </td>
            <td>
                <c:out value="${request.month}"/>
            </td>
            <td>
                <c:out value="${request.startDay}"/>
            </td>
            <td>
                <c:out value="${request.endDay}"/>
            </td>

        </tr>
    </c:forEach>
</table>
<br>
<a href="addRequest">
    <button>Add new request</button>
</a>
<a href="processRequests">
    <button>Process requests</button>
</a>
</body>
</html>
