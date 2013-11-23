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
                <%@ include file="/WEB-INF/jsp/include/student_navbar.jsp" %>
            </div>

            <div class="span10">
                <legend>Student information</legend>
                <div class="row-fluid">
                    <div class="span4">
                        <h4>Personal details</h4>
                        <spring-form:form method="POST" modelAttribute="student">
                        <fieldset>
                            <label>First name: <spring-form:errors path="firstName" class="alert-error"></spring-form:errors></label>
                            <spring-form:input path="firstName" placeholder="First name" />

                            <label>Last name: <spring-form:errors path="lastName" class="alert-error"></spring-form:errors></label>
                            <spring-form:input path="lastName" placeholder="Last name"/>

                            <label>Date of birth: <spring-form:errors path="dateOfBirth" class="alert-error"></spring-form:errors></label>
                            <spring-form:input path="dateOfBirth" placeholder="Date of birth" id="datepicker" />

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

                    <div class="span7" style="background-color: #e0ffff">
                        <ul class="nav nav-tabs">
                            <li><a href="#" class="educationInfo">Education</a></li>
                            <li><a href="#" class="addressInfo">Address</a></li>
                            <li><a href="#" class="bankInfo">Bank</a></li>
                            <li><a href="#" class="sponsorInfo">Sponsor</a></li>
                        </ul>
                        <%@ include file="/WEB-INF/jsp/student/educationDetails.jspf" %>
                        <%@ include file="/WEB-INF/jsp/student/addressDetails.jspf" %>
                        <%@ include file="/WEB-INF/jsp/student/bankDetails.jspf" %>
                        <%@ include file="/WEB-INF/jsp/student/sponsorDetails.jspf" %>
                    </div>
                </div>

            </div>
        </div>

            <c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
            <c:if test="${not empty showLink}"><div id="link"><a href="/HelloWorld/student">Return to Students page</a></div></c:if>

        </div>
    </body>


    <script type="text/javascript">

        <c:if test="${activeTab == 'education'}">
            hideAllExceptThis("#educationInfo");
            setActiveNavTab.call($(".educationInfo"));
        </c:if>

        <c:if test="${activeTab == 'address'}">
            hideAllExceptThis("#addressInfo");
            setActiveNavTab.call($(".addressInfo"));
        </c:if>

        function setActiveNavTab() {
            var selectedItem = $('li.myactive');
            selectedItem.removeClass();
            $(this).parent().addClass('myactive');
        }

        function hideAllExceptThis(divIdToShow) {
            $('#bankInfo').hide();
            $('#sponsorInfo').hide();
            $('#addressInfo').hide();
            $('#educationInfo').hide();

            $(divIdToShow).show();
        }

        $(function() {
            $(".educationInfo").click( function() {
                hideAllExceptThis("#educationInfo");
                setActiveNavTab.call(this);
            });
        });

        $(function() {
            $(".addressInfo").click( function() {
                hideAllExceptThis("#addressInfo");
                setActiveNavTab.call(this);
            });
        });

        $(function() {
            $(".bankInfo").click( function() {
                hideAllExceptThis("#bankInfo");
                setActiveNavTab.call(this);
            });
        });

        $(function() {
            $(".sponsorInfo").click( function() {
                hideAllExceptThis("#sponsorInfo");
                setActiveNavTab.call(this);
            });
        });

        $(function() {
            $("#datepicker").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'dd/mm/yy',
                defaultDate: '-13y'
            });

//            if ($("#datepicker").val().length > 0 ) {
//                var dateObject = new Date($("#datepicker").val());
//                var dateString = $.datepicker.formatDate("dd/mm/yy", dateObject);
//                $('#datepicker').val(dateString);
//            }
        });

    </script>
</html>