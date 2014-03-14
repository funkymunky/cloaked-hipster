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
                        <c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
                    <fieldset class="main-content">
                        <div class="span4">
                        <h4>Personal details</h4>
                        <spring-form:form method="POST" modelAttribute="student">
                        <%--<fieldset>--%>
                            <label>First name: <spring-form:errors path="firstName" class="alert-error"></spring-form:errors></label>
                            <spring-form:input path="firstName" placeholder="First name" />

                            <label>Last name: <spring-form:errors path="lastName" class="alert-error"></spring-form:errors></label>
                            <spring-form:input path="lastName" placeholder="Last name"/>

                            <label>Date of birth: <spring-form:errors path="dateOfBirth" class="alert-error"></spring-form:errors></label>
                            <spring-form:input path="dateOfBirth" placeholder="Date of birth" id="dateOfBirth" class="datepicker" />

                            <c:choose>
                            <c:when test="${updateMode != true}">
                            <button type="submit" class="btn btn-primary">Add student</button>
                            </c:when>
                            <c:otherwise>
                            <button type="submit" class="btn btn-primary">Update student</button>
                            </c:otherwise>
                            </c:choose>
                            <button type="button" class="btn" onclick="location.href='/HelloWorld/student/list'">Cancel</button>
                        <%--</fieldset>--%>
                        </spring-form:form>
                    </div>

                    <div class="span7" style="background-color: #EBE0D6; display:none; padding-bottom: 20px; border-radius: 10px 10px 10px 10px;">
                        <%@ include file="/WEB-INF/jsp/student/addressDetails.jspf" %>
                        <%@ include file="/WEB-INF/jsp/student/educationDetails.jspf" %>
                        <%@ include file="/WEB-INF/jsp/student/bankDetails.jspf" %>
                        <%@ include file="/WEB-INF/jsp/student/sponsorDetails.jspf" %>
                    </div>

                    </fieldset>
                </div>
                <div class="row-fluid">
                </div>
            </div>
        </div>

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

        <c:if test="${activeTab == 'bank'}">
            hideAllExceptThis("#bankInfo");
            setActiveNavTab.call($(".bankInfo"));
        </c:if>

        <c:if test="${activeTab == 'sponsor'}">
            hideAllExceptThis("#sponsorInfo");
            setActiveNavTab.call($(".sponsorInfo"));
        </c:if>

        function clearMessages() {
            $('.message').text("");
        }

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
            $('.span7').show();
//            event.preventDefault();
        }

        $(function() {
            $(".educationInfo").click( function(evt) {
                evt.preventDefault();
                hideAllExceptThis("#educationInfo");
                setActiveNavTab.call(this);
                clearMessages();
            });
        });

        $(function() {
            $(".addressInfo").click( function(evt) {
                evt.preventDefault();
                hideAllExceptThis("#addressInfo");
                setActiveNavTab.call(this);
                clearMessages();
            });
        });

        $(function() {
            $(".bankInfo").click( function(evt) {
                evt.preventDefault();
                hideAllExceptThis("#bankInfo");
                setActiveNavTab.call(this);
            });
        });

        $(function() {
            $(".sponsorInfo").click( function(evt) {
                evt.preventDefault();
                hideAllExceptThis("#sponsorInfo");
                setActiveNavTab.call(this);
            });
        });

        $(function() {
            $(".datepicker").datepicker({
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