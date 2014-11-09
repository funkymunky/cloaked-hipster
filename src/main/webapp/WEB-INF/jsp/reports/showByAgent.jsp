<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
            <legend>Students by Agent</legend>
            <div class="col-md-2">
                <select class="form-control" path="agent" id="agent" type="text">
                    <option value="">-- Select an agent --</option>
                    <c:forEach items="${agentTypeValues}" var="agentName">
                        <c:choose>
                            <c:when test="${agentName.name == selectedAgent}">
                                <option value="${agentName.name}" selected>${agentName.description}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${agentName.name}">${agentName.description}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <p class="filterError" id="errorText"></p>
            <button type="button" class="btn btn-primary" id="filterSchool">Filter by school</button>
            <button type="button" class="btn btn-primary" id="filterUniversity">Filter by university</button>

            <%@ include file="/WEB-INF/jsp/reports/reportDetails.jspf" %>
            <p class="reportTotal">Total students: ${fn:length(students)}</p>
            <button type="button" class="btn btn-link" id="downloadCsv">Export to csv</button>

        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $(function(){
        $('#agent').change(function() {
            var agentName = $('#agent').find(":selected").val();
            var url = "/lsf/report/agent/"+agentName;
            window.location.href = url;
        })
    });

    $('#filterSchool').click(function() {
        var agentName = $('#agent').find(":selected").text();
        var url = "/lsf/report/agent/school/"+agentName;
        window.location.href = url;
    });

    $('#filterUniversity').click(function() {
        var agentName = $('#agent').find(":selected").text();
        var url = "/lsf/report/agent/university/"+agentName;
        window.location.href = url;
    });

    $('#downloadCsv').click(function() {
        var agentName = $('#agent').find(":selected").text();
        var url = "/lsf/report/agent/downloadCsv/"+agentName;
        window.location.href = url;
    });

</script>

</html>
