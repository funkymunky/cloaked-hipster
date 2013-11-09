<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>

    <title>Students</title>
</head>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>

    <p>Listing all students.</p>

    <table>
        <tr>
            <th>Student Name</th>
        </tr>
        <c:forEach var="student" items ="${students}">
            <tr>
                <td><a href="/HelloWorld/student/edit/${student.id}">${student.lastName}, ${student.firstName}</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>