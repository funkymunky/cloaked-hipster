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
                <%@ include file="/WEB-INF/jsp/include/report_navbar.jsp" %>
            </div>

            <div class="span10">
            <legend>Students awaiting sponsorship</legend>

                <a href="/HelloWorld/report/awaitingSponsorship/downloadCsv">Export to csv</a>
                <table class="table table-striped">
                    <thead><tr>
                        <th>Id</th>
                        <th>Student name</th>
                        <th>Year of study</th>
                        <th>Account name</th>
                        <th>Bank</th>
                        <th>Branch</th>
                        <th>Account number</th>
                        <th>Standing order number</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <c:choose>
                                <c:when test="${student.education.institutionType eq null}">
                                    <c:set var="qualification" value=""/>
                                </c:when>
                                <c:when test="${student.education.institutionType eq 'School'}">
                                    <c:set var="qualification" value="${student.education.institutionName} (${student.education.yearOfStudy})"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="qualification" value="${student.education.degreeName} (${student.education.yearOfStudy})"/>
                                </c:otherwise>
                            </c:choose>
                            <tr>
                                <td>${student.id}</td>
                                <td><a href="/HelloWorld/student/edit/${student.id}">${student.lastName}, ${student.firstName}</a></td>
                                <td>${qualification}</td>
                                <td>${student.bank.accountName}</td>
                                <td>${student.bank.bank}</td>
                                <td>${student.bank.branch}</td>
                                <td>${student.bank.accountNumber}</td>
                                <td>${student.bank.standingOrder}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</body>

</html>