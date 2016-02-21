<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
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
    <div class="row">
        <div class="col-md-2">
            <%@ include file="/WEB-INF/jsp/include/report_navbar.jsp" %>
        </div>

        <div class="col-md-10">
            <legend>Please select a report from the menu</legend>
        </div>
        <div>
            <c:if test="${not empty error}" >
                <p>Something went wrong: ${error}</p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>