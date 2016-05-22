<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contacts</title>
</head>
<body>
<h2>Contacts:</h2>
<table border="1">
    <tr>
        <td>Name</td>
        <td>Date of birth</td>
        <td>Email</td>
        <td>Phone number</td>
    </tr>
    <c:forEach var="contact" items="${contacts}">
        <tr>
            <td>
                <c:out value="${contact.entryName}"/>
            </td>
            <td>
                <c:out value="${contact.birthDate}"/>
            </td>
            <td>
                <c:out value="${contact.email}"/>
            </td>
            <td>
                <c:out value="${contact.phoneNumber}"/>
            </td>
            <td>
                <a href="edit?name=<c:out value="${contact.entryName}"/>">
                    <button>Edit</button>
                </a>
            </td>
            <td>
                <a href="delete?name=<c:out value="${contact.entryName}"/>">
                    <button>Delete</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="add">
    <button>Add new contact</button>
</a>
</body>
</html>
