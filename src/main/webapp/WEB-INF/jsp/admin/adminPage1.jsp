<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- This is needed for the logout to work properly. -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN PAGE 1</title>
    </head>
    <body>
        <p>This is an admin page.</p>
        <p><a href='<spring:url value="/j_spring_security_logout" htmlEscape="true" />'>Logout</a></p>
        <p><a href="../member/memberPage1.jsp">Go to Member page 1</a></p>
        <p><a href="adminPage1.jsp">Go to Admin page 1</a></p>
        <p><a href="../index.jsp">Go to Index page</a></p>
    </body>
</html>
