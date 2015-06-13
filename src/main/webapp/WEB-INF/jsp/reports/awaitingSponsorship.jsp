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
            <legend>Students awaiting sponsorship</legend>
                <a href="/lsf/report/awaitingSponsorship/school"><button type="button" class="btn btn-primary" id="filterSchool">Filter by school</button></a>
                <a href="/lsf/report/awaitingSponsorship/university"><button type="button" class="btn btn-primary" id="filterUniversity">Filter by university</button></a>
                <a href="#">
                    <button type="button" class="btn btn-primary" id="filterBank">Filter by bank</button></a>
                <input id="bankName" type="text" size="30"/>
                <p class="filterError" id="errorText"></p>
                    <%@ include file="/WEB-INF/jsp/reports/awaitingSponsorshipDetails.jspf" %>
                <p class="reportTotal">Total students: ${fn:length(students)}</p>
                <a href="/lsf/report/awaitingSponsorship/downloadCsv">Export to csv</a>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
        $(function(){
            $('#filterBank').click(function() {
                var anchor = $(this).parent();
                var bankName = $('#bankName').val();
                if (!bankName.trim()) {
                    $('#errorText').text("Please enter a bank name to filter by");
                } else {
                    anchor.attr("href", "/lsf/report/awaitingSponsorship/bank/"+bankName);
                }

            })
        })
</script>
</html>