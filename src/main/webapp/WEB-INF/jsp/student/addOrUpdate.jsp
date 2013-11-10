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
        <div class="container">

        <div class="row-fluid">
            <div class="span12">
                <spring-form:form method="POST" modelAttribute="student">
                    <fieldset>
                        <legend>Student information</legend>
                        <div class="row-fluid">
                            <div class="span6">
                                <h4>Personal details</h4>
                                <label>First name:</label>
                                <spring-form:input path="firstName" placeholder="First name" />

                                <label>Other names:</label>
                                <spring-form:input path="otherNames" placeholder="Other names" />

                                <label>Last name:</label>
                                <spring-form:input path="lastName" placeholder="Last name"/>

                                <label>Date of birth:</label>
                                <spring-form:input path="dateOfBirth" placeholder="Date of birth" id="datepicker" />
                            </div>

                            <div class="span6">
                                <h4>Address details</h4>

                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${updateMode != true}">
                                <button type="submit" class="btn btn-primary">Add student</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-primary">Update student</button>
                            </c:otherwise>
                        </c:choose>
                        <button type="button" class="btn" onclick="location.href='/HelloWorld/student'">Cancel</button>
                    </fieldset>
                </spring-form:form>

             </div>
        </div>

            <c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
            <c:if test="${not empty showLink}"><div id="link"><a href="/HelloWorld/student">Return to Students page</a></div></c:if>
        </div>
    </body>


    <script type="text/javascript">
        $(function() {
            $("#datepicker").datepicker({ defaultDate: '-13y'});
            if ($("#datepicker").val().length > 0 ) {
                var dateObject = new Date($("#datepicker").val());
                var dateString = $.datepicker.formatDate("dd/mm/yy", dateObject);
                $('#datepicker').val(dateString);
            }
        });

    </script>
</html>