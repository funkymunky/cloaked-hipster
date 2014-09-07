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
        <p class="lead">${message}</p>
        <p><a href="/lsf/member/userHome">Go to Member page 1</a></p>
        <p><a href="/lsf/admin/userHome">Go to Admin page 1</a></p>
    </div>
</body>
</html>