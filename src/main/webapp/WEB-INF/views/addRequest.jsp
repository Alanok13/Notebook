<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h2>New request:</h2>
<sf:form method="POST" modelAttribute="request">
    <fieldset>
        <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
        <table cellspacing="0">
            <tr>
                <th><label for="country">Country:</label></th>
                <td>
                    <sf:input path="country" size="15" id="country"/>

                    <br>
                    <sf:errors path="country"/>
                </td>
            </tr>
            <tr>
                <th><label for="month">Month:</label></th>
                <td>
                    <sf:input path="month" size="15" maxlength="15" id="month"/>
                    <sf:errors path="month"/>
                </td>
            </tr>
            <tr>
                <th><label for="startDay">Start day:</label></th>
                <td>
                    <sf:input path="startDay" size="30" id="startDay"/>
                    <sf:errors path="startDay"/>
                </td>
            </tr>
            <tr>
                <th><label for="endDay">End day:</label></th>
                <td>
                    <sf:input path="endDay" size="10" id="endDay"/>
                    <sf:errors path="endDay"/>
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
