<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
    <title> Hello </title>
</head>
<body>
    <%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>

    <div class="container">
        <h1>${message}</h1>
        <p>Use this document as a way to quick start any new project.<br> All you get is this message and a barebones HTML document.</p>
    </div> <!-- /container -->
</body>
</html>