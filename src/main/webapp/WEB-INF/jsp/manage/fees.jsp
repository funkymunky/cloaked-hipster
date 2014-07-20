<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
    <title>Fees</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span2">
                <%--<%@ include file="/WEB-INF/jsp/include/report_navbar.jsp" %>--%>
            </div>

            <div class="span10">
                <legend>Set up fees</legend>
                <spring-form:form method="POST" modelAttribute="fees">
                    <span>Current fees issue date:</span> <spring-form:input type="text" path="issueDate" id="issueDate" class="datepickerFee" />
                    <a href="">Edit date</a>
                    <button type="submit" class="btn btn-primary">Save</button>
                </spring-form:form>
                <div>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Sponsor</th>
                                <th>Student</th>
                                <th>Amount outstanding</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%--<c:forEach var="student" items="${sponsoredStudents}">--%>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            <%--</c:forEach>--%>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</body>

</html>

<script type="text/javascript">
    $(function() {
        $(".datepickerFee").datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: 'dd/mm/yy'
        });
    });

</script>