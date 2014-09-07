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
                <%--<%@ include file="/WEB-INF/jsp/include/student_navbar.jsp" %>--%>
            </div>

            <div class="col-md-10">
            <legend>All Students report</legend>

                <a href="/lsf/report/allStudents/downloadCSV">Export to csv</a>
                <table class="table table-striped">
                    <thead><tr>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Date of birth</th>
                        <th>School type</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.firstName}</td>
                                <td>${student.lastName}</td>
                                <td>${student.dateOfBirth}</td>
                                <td>${student.education.institutionType}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</body>

<script type="text/javascript">

</script>

</html>