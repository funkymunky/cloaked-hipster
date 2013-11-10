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

            <spring-form:form method="POST" modelAttribute="student">
            <table>
                <tr>
                    <td colspan="2">First name:</td>
                </tr>
                <tr>
                    <td colspan="2"><spring-form:input path="firstName" placeholder="First name" /></td>
                </tr>
                <tr>
                    <td colspan="2">Other names:</td>
                </tr>
                <tr>
                    <td colspan="2"><spring-form:input path="otherNames" placeholder="Other names" /></td>
                </tr>
                <tr>
                    <td colspan="2">Last name:</td>
                </tr>
                <tr>
                    <td colspan="2"><spring-form:input path="lastName" placeholder="Last name"/></td>
                </tr>
                <tr>
                    <td colspan="2">Date of birth:</td>
                </tr>
                <tr>
                    <td colspan="2"><spring-form:input path="dateOfBirth" placeholder="Date of birth" id="datepicker" /></td>
                </tr>
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${updateMode != true}">
                                <input type="submit" value="Add student"/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Update student"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td align="right">
                        <a href="/HelloWorld/student">Cancel</a>
                    </td>
                </tr>
            </table>
            </spring-form:form>

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