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

            <p>This is the index page for new students.</p>
            <p><a href="/HelloWorld/student/add">Add new student</a></p>
            <p><a href="/HelloWorld/student/view">View student</a></p>
        </div>
    </body>
</html>