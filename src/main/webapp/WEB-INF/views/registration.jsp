<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Registration:</h2>
<sf:form method="POST" modelAttribute="user">
    <fieldset>
        <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
        <table cellspacing="0">
            <tr>
                <th><label for="userName">Name:</label></th>
                <td>
                    <sf:input path="userName" size="15" id="userName"/>

                    <br>
                    <sf:errors path="userName"/>
                </td>
            </tr>

            <tr>
                <th><label for="login">Login:</label></th>
                <td>
                    <sf:input path="login" size="30" id="login"/>
                    <sf:errors path="login"/>
                </td>
            </tr>
            <tr>
                <th><label for="password">Password:</label></th>
                <td>
                    <sf:password path="password" size="10" id="password"/>
                    <sf:errors path="password"/>
                </td>
            </tr>
            <tr>
                <th></th>
                <td><input userName="commit" type="submit" value="Accept"/></td>
            </tr>
        </table>
    </fieldset>
</sf:form>
</body>
</html>
