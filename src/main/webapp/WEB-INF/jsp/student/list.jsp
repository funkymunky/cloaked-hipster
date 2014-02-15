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
            <legend>Student list</legend>

                <form class="navbar-search pull-right">
                    <input type="text" class="search-query" placeholder="Search" id="searchStudent">
                </form>

                <table class="table table-striped">
                    <thead><tr>
                        <th>Student Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items ="${students}">
                        <tr>
                            <td>${student.lastName}, ${student.firstName}</td>
                            <td><a href="/HelloWorld/student/edit/${student.id}">View</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript">
    $(function() { $("#searchStudent")
            .autocomplete({source: [${students}]});

    });

</script>

</html>