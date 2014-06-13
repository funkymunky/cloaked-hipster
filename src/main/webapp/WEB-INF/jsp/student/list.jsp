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
            <legend>Student list</legend>

                <div class="navbar-search pull-right">
                    <input type="text" class="search-query" placeholder="Search" id="searchStudent">
                    <button class="btn btn-primary" id="edit" type="button">Edit</button>
                </div>

                <table class="table table-striped">
                    <thead><tr>
                        <th>Student Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items ="${students}">
                        <tr>
                            <td><a href="/HelloWorld/student/edit/${student.id}">${student.lastName}, ${student.firstName}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript">
    function lookupId(studentName) {
        var lastName = studentName.split(",")[0].trim();
        var firstName = studentName.split(",")[1].trim();

        var studentId;
        <c:forEach var="s" items="${students}">
        if (lastName == "${s.lastName}") {
            if (firstName == "${s.firstName}") {
                studentId = "${s.id}";
            }
        }
        </c:forEach>
        return studentId;

    }

    $(function() {
        var listOfAllStudents = [];
        <c:forEach var="s" items="${students}">
        listOfAllStudents.push("${s.lastName}" + ", " + "${s.firstName}");
        </c:forEach>

        $("#searchStudent").autocomplete({
            source: listOfAllStudents
        });
    });

    $(function() {
        $("#edit").click(function() {
            var selectedStudent = $("#searchStudent").val();
            var studentId = lookupId(selectedStudent);
            window.location = "/HelloWorld/student/edit/"+studentId;
        });
    });


</script>

</html>