<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h2>New contact:</h2>
<sf:form method="POST" modelAttribute="contact">
    <fieldset>
        <table cellspacing="0">
            <tr>
                <th><label for="entryName">Name:</label></th>
                <td>
                    <sf:input path="entryName" size="15" id="entryName"/>
                    <small>No spaces. Name length is less than than 20 characters</small>

                    <br>
                    <sf:errors path="entryName"/>
                </td>
            </tr>
            <tr>
                <th><label for="email">Email:</label></th>
                <td>
                    <sf:input path="email" size="15" maxlength="15" id="email"/>
                    <sf:errors path="email"/>
                </td>
            </tr>
            <tr>
                <th><label for="birthDate">Date of birth:</label></th>
                <td>
                    <sf:input type="date" path="birthDate" size="30" id="birthDate"/>
                    <sf:errors path="birthDate"/>
                </td>
            </tr>
            <tr>
                <th><label for="phoneNumber">Phone number:</label></th>
                <td>
                    <sf:input path="phoneNumber" size="10" id="phoneNumber"/>
                    <sf:errors path="phoneNumber"/>
                </td>
            </tr>
            <tr>
                <th></th>
                <td><input name="commit" type="submit" value="Accept"/></td>
            </tr>
        </table>
    </fieldset>
</sf:form>
</body>
</html>
