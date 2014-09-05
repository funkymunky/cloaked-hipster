<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
    <title>Reset password</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2">
                <%--<%@ include file="/WEB-INF/jsp/include/student_navbar.jsp" %>--%>
            </div>

            <div class="col-md-6">
            <legend>Reset password</legend>
                <spring-form:form class="form-horizontal" role="form" method="POST" modelAttribute="security">
                <div class="form-group">
                    <label class="control-label col-md-3" for="newPassword">New password:</label>
                    <div class="col-md-5"><spring-form:input class="form-control" path="newPassword" id="newPassword" type="password"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3" for="confirmPassword">Confirm password:</label>
                    <div class="col-md-5"><input id="confirmPassword" type="password" class="form-control"></div>
                </div>
                    <button type="submit" class="btn btn-primary">Reset password</button>
                </spring-form:form>
            </div>
        </div>
    </div>
</body>

</html>