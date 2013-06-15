<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Calendar" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<% Calendar cal = Calendar.getInstance();%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>JSP Page</title>
</head>
<body>
<h1>Current time is <%= cal.getTime() %> </h1>
<p><span class="something">Hello world</span></p>
<%--<a href="/HelloWorld/account">List accounts</a>--%>
<p>
Hi!
</p>
</body>
</html>