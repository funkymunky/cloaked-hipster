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
            <legend>All Sponsors</legend>

                <p class="reportTotal">Total sponsors: ${fn:length(sponsors)}</p>
                <%@ include file="/WEB-INF/jsp/reports/allSponsorsDetails.jspf" %>
                <p class="reportTotal">Total sponsors: ${fn:length(sponsors)}</p>
                <a href="/lsf/report/allSponsors/downloadCsv">Export to csv</a>
            </div>
        </div>
    </div>
</body>

</html>