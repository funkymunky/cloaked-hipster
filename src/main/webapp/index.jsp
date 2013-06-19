<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/include/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<% Calendar cal = Calendar.getInstance();%>

<html>
<head>
    <title> Index </title>
</head>
<body>

    <%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>

    <div class="container">

    <!-- Main hero unit for a primary marketing message or call to action -->
    <div class="hero-unit">
    <c:if test="${not empty username}">
       <h1>Goodbye ${username}</h1>
    </c:if>
    <h1>Hello, world!</h1>
    <p>This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
    <p><a href="#" class="btn btn-primary btn-large">Learn more &raquo;</a></p>
    </div>

    <!-- Example row of columns -->
    <div class="row">
        <div class="span6">
        <h2>Heading</h2>
        <p>Hello and welcome. This is a sample project to learn some basics.</p>
        <p><a class="btn" href="/HelloWorld/login">Login</a></p>
        </div>

        <div class="span6">
        <h2>Heading</h2>
        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        </div>
    </div>
<hr>

<footer>
<p>&copy; Company 2013</p>
</footer>

</div> <!-- /container -->



</body>
</html>