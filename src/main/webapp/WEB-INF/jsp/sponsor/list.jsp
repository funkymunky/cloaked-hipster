<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
    <title>Sponsors</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span2">
                <%@ include file="/WEB-INF/jsp/include/sponsor_navbar.jsp" %>
            </div>

            <div class="span10">
            <legend>Spnsor list</legend>

                <form class="navbar-search pull-right">
                    <input type="text" class="search-query" placeholder="Search" id="searchSponsor">
                </form>

                <table class="table table-striped">
                    <thead><tr>
                        <th>Sponsor Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${empty sponsors}">
                           <tr><td>Nothing to display</td></tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="sponsor" items ="${sponsors}">
                                <tr>
                                    <td><a href="/HelloWorld/sponsor/edit/${sponsor.sponsorId}">${sponsor.lastName}, ${sponsor.firstName}</a></td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript">
    $(function() { $("#searchSponsor")
            .autocomplete({source: [${sponsors}]});

    });

</script>

</html>