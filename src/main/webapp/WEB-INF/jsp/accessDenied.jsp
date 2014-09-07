<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/include/head.jsp" %>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ACCESS DENIED</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>

        <p>Access denied. You don't have the required permissions to visit that page.</p>
        <p><a href="/lsf/member/userHome">Go to Member page 1</a></p>
        <p><a href="/lsf/admin/userHome">Go to Admin page 1</a></p>
    </body>
</html>
