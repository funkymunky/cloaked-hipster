<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/include/head.jsp" %>

<html>
<head>
    <title> Hello </title>
       <style type="text/css">
          body {
            padding-top: 60px;
            padding-bottom: 40px;
          }
        </style>
</head>
<body>
    <%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>

    <div class="container">
        <h1>${message}</h1>
        <p>Use this document as a way to quick start any new project.<br> All you get is this message and a barebones HTML document.</p>
        <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </div> <!-- /container -->
</body>
</html>