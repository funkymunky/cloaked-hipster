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
        <div class="row-fluid">
            <div class="span2">
                <%--<%@ include file="/WEB-INF/jsp/include/student_navbar.jsp" %>--%>
            </div>

            <div class="span10">
            <legend>Available Reports</legend>

                <table class="table table-striped">
                    <thead><tr>
                        <th>Standard reports</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr><td><a href="/HelloWorld/report/currentlySponsored">Currently sponsored students</a></td></tr>
                        <tr><td><a href="/HelloWorld/report/awaitingSponsorship">Students awaiting sponsorship</a></td></tr>
                        <tr><td><a href="/HelloWorld/report/allStudents">All Students (non jasper)</a></td></tr>
                    </tbody>
                </table>

                <table class="table table-striped">
                    <thead><tr>
                        <th>Custom reports</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript">

</script>

</html>