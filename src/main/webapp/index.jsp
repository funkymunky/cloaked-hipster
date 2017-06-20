<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<% Calendar cal = Calendar.getInstance();%>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
    <title> Index </title>



</head>
<body>

    <%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>

    <div class="container">

    <!-- Main hero unit for a primary marketing message or call to action -->
    <div class="hero-unit">
        <c:if test="${not empty name}">
           <h1>Goodbye ${name}</h1>
        </c:if>
        <p><h1> Welcome to the LSF database</h1></p>
        Please select an option from the above menu bar to begin.


    </div> <!-- /container -->



</body>
</html>