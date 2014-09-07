<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
    <title>Fees</title>

    <style type="text/css">
        .focusedInput {
            border-color: rgba(82,168,236,.8);
            outline: 0;
            box-shadow: 0 0 8px rgba(82,168,236,.6) !important;
        }

    </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2">
                <%--<%@ include file="/WEB-INF/jsp/include/report_navbar.jsp" %>--%>
            </div>

            <div class="col-md-10">
                <legend>Set up fees</legend>
                <spring-form:form role="form" method="POST" modelAttribute="fees">
                    <span>Current fees issue date:</span> <spring-form:input type="text" path="issueDate" id="issueDate" class="datepickerFee" disabled="true"/>
                    <a href="" id="editDate">Edit date</a>
                    <button type="submit" class="btn btn-sm">Save</button>
                </spring-form:form>
                <div>
                    <table class="table table-striped mytable">
                        <thead>
                            <tr>
                                <th class="span1">Id</th>
                                <th class="span2">Sponsor</th>
                                <th class="span4">Student</th>
                                <th class="span2" style="text-align: right">Amount outstanding</th>
                                <th class="span1" style="text-align: center">Paid</th>
                            </tr>
                        </thead>
                        <tbody>
                        <spring-form:form role="form" method="POST" action="/lsf/manage/payments" modelAttribute="outstandingPayments">
                            <c:forEach var="sponsorshipFee" items="${sponsoredStudents}">
                                <tr>
                                    <td>${sponsorshipFee.id}</td>
                                    <td><a href="/lsf/sponsor/edit/${sponsorshipFee.sponsor.id}">${sponsorshipFee.sponsor.firstName} ${sponsorshipFee.sponsor.lastName}</a></td>
                                    <td>${sponsorshipFee.student.firstName} ${sponsorshipFee.student.lastName}</td>
                                    <td style="text-align: right">${sponsorshipFee.amountOutstanding}</td>
                                    <td style="text-align: center"><spring-form:checkbox path="paidFees" id="paymentReceived" value="${sponsorshipFee.id}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                        <button type="submit" class="btn btn-primary">Update payments</button>
                        </spring-form:form>
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

    $(function() {
        $("#editDate").click(function(evt) {
            evt.preventDefault();
            $('.datepickerFee').removeAttr('disabled');
            $('.datepickerFee').addClass('focusedInput');
        })
    });

</script>