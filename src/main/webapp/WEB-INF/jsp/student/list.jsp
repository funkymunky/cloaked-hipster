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
                <%@ include file="/WEB-INF/jsp/include/student_navbar.jsp" %>
            </div>

            <div class="col-md-10">
            <legend>Student list</legend>
                <c:if test="${showAllButton eq true}">
                    <button id="allStudents" type="button" class="btn-info">Show all</button>
                </c:if>

                <div class="navbar-search pull-right">
                    <input id="searchStudent" type="text" class="search-query" placeholder="Search by first/last name or standing order or account number" >
                    <button id="search" type="button" class="btn btn-sm">Search</button>
                    <%--<button class="btn btn-primary" id="edit" type="button">Edit</button>--%>
                </div>

                <table class="table table-striped">
                    <thead><tr>
                        <th>Student Name</th>
                        <th>Sponsorship Type</th>
                        <th>Assigned to Sponsor</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items ="${students}">
                        <tr>
                            <td><a href="/lsf/student/edit/${student.id}">${student.lastName}, ${student.firstName}</a></td>
                            <td style="text-align: center">
                                <c:choose>
                                    <c:when test="${student.sponsorship.sponsorshipType == 'FormerlySponsored'}"><span class="glyphicon glyphicon-remove-circle"/></c:when>
                                    <c:when test="${student.sponsorship.sponsorshipType == 'AwaitingSponsorship'}"><span class="glyphicon glyphicon-question-sign"/></c:when>
                                    <c:when test="${student.sponsorship.sponsorshipType == 'CurrentlySponsored'}"><span class="glyphicon glyphicon-ok-circle"/></c:when>
                                </c:choose>
                            </td>
                            <td style="text-align: center">
                                <c:choose>
                                    <c:when test="${student.sponsorship.sponsor ne null}"><span class="glyphicon glyphicon-ok" style="color: green"/></td></c:when>
                                    <c:otherwise><span class="glyphicon glyphicon-remove" style="color: red"/></c:otherwise>
                                </c:choose>
                            </td>
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

    <%-- To autocomplete list of students based on name --%>
    <%--$(function() {--%>
        <%--var listOfAllStudents = [];--%>
        <%--<c:forEach var="s" items="${students}">--%>
        <%--listOfAllStudents.push("${s.lastName}" + ", " + "${s.firstName}");--%>
        <%--</c:forEach>--%>

        <%--$("#searchStudent").autocomplete({--%>
            <%--source: listOfAllStudents--%>
        <%--});--%>
    <%--});--%>

    $(function() {
        $("#edit").click(function() {
            var selectedStudent = $("#searchStudent").val();
            var studentId = lookupId(selectedStudent);
            window.location = "/lsf/student/edit/"+studentId;
        });
    });

    $(function() {
        $("#allStudents").click(function() {
            window.location = "/lsf/student/list";
        });
    });

    $(function() {
        $("#search").click(function() {
            var searchString = $("#searchStudent").val();
            window.location = "/lsf/student/search/" + searchString;

        })
    });


</script>

</html>