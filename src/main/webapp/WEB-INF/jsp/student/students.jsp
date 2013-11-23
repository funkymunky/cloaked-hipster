<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
    <title>Students</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span2">
                <%@ include file="/WEB-INF/jsp/include/student_navbar.jsp" %>
            </div>
            <div class="span10">
                Perhaps the list view of students should go in here....
            </div>
        </div>
    </div>
    </body>
</html>