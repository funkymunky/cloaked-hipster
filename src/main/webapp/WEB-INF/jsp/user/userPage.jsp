<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/include/head.jsp" %>

<!-- This is needed for the logout to work properly. -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>

        <p>${message}</p>
        <p><a href="/HelloWorld/member/userHome">Go to Member page 1</a></p>
        <p><a href="/HelloWorld/admin/userHome">Go to Admin page 1</a></p>
        <p><a href="../index.jsp">Go to Index page</a></p>
    </body>
</html>
