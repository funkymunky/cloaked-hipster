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
    <spring-form:form id="loginForm" action="<c:url value='login_check.htm'/>" method="post">
        Username: <spring-form:input id="username" name="username" width="20px" path="username"/>
        <br/><br/>
        Password: <spring-form:password id="password" name="password" width="20px" path="password"/>
        <input type="submit" value="Login"/>
    </spring-form:form>
</p>
</body>
</html>