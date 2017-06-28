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
        <div class="row">
            <div class="col-md-2">
                <%@ include file="/WEB-INF/jsp/include/student_navbar.jsp" %>
            </div>

            <div class="col-md-10">
                <legend>Student information</legend>
                <c:choose>
                    <c:when test="${warn eq true}">
                        <div id="alertFeedback" class="message warning">${message}</div>
                    </c:when>
                    <c:otherwise>
                        <div id="alertFeedback" class="message green">${message}</div>
                    </c:otherwise>
                </c:choose>
                <fieldset class="main-content">
                    <div class="row">
                        <div class="col-md-4">
                            <h4>Personal details</h4>
                            <spring-form:form role="form" method="POST" modelAttribute="student">
                                <div class="form-group">
                                    <label for="inputFirstName">First name: <spring-form:errors path="firstName" class="alert-error"></spring-form:errors></label>
                                    <spring-form:input id="inputFirstName" class="form-control" path="firstName" placeholder="First name" />
                                </div>
                                <div class="form-group">
                                    <label for="inputLastName">Last name: <spring-form:errors path="lastName" class="alert-error"></spring-form:errors></label>
                                    <spring-form:input id="inputLastName" class="form-control" path="lastName" placeholder="Last name"/>
                                </div>
                                <div class="form-group">
                                    <label for="dateOfBirth">Date of birth: <spring-form:errors path="dateOfBirth" class="alert-error"></spring-form:errors></label>
                                    <spring-form:input path="dateOfBirth" placeholder="Date of birth" id="dateOfBirth" class="form-control datepickerDob" />
                                </div>
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <spring-form:input path="email" id="email" class="form-control" placeholder="Email"/>
                                </div>
                                <div class="form-group">
                                    <label for="telephone">Telephone:</label>
                                    <spring-form:input path="telephone" id="telephone" class="form-control" placeholder="Telephone"/>
                                </div>
                                <br/>
                                <c:choose>
                                    <c:when test="${updateMode != true}">
                                        <button type="submit" class="btn btn-primary submit">Add student</button>
                                    </c:when>
                                    <c:otherwise>
                                        <%--<sec:authorize ifAllGranted="ROLE_ADMIN">--%>
                                            <button type="submit" class="btn btn-primary">Update student</button>
                                        <%--</sec:authorize>--%>
                                    </c:otherwise>
                                </c:choose>
                                <button type="button" class="btn" onclick="location.href='/lsf/student/list'">Cancel</button>
                            </spring-form:form>
                        </div>

                        <div class="col-md-7" style="background-color: #EBE0D6; display:none; padding-bottom: 20px; border-radius: 10px 10px 10px 10px;">
                            <%@ include file="/WEB-INF/jsp/student/profile.jspf" %>
                            <%@ include file="/WEB-INF/jsp/student/addressDetails.jspf" %>
                            <%@ include file="/WEB-INF/jsp/student/educationDetails.jspf" %>
                            <%@ include file="/WEB-INF/jsp/student/bankDetails.jspf" %>
                            <%@ include file="/WEB-INF/jsp/student/sponsorshipDetails.jspf" %>
                            <%@ include file="/WEB-INF/jsp/student/studentFeeDetails.jspf" %>
                            <%@ include file="/WEB-INF/jsp/student/comments.jspf" %>
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>

            <c:if test="${not empty showLink}"><div id="link"><a href="/lsf/student">Return to Students page</a></div></c:if>

        </div>
    </body>

    <%--<script src="<c:url value='/js/datepicker-defaults.js'/>"></script>--%>
    <script src="<c:url value='/js/student-confirm-unsaved.js'/>"></script>

    <script type="text/javascript">

        $("#profileInfo").show();
        $('.col-md-7').show();

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

        <c:if test="${activeTab == 'studentfee'}">
            hideAllExceptThis("#studentFeeInfo");
            setActiveNavTab.call($(".studentFeeInfo"));
        </c:if>

        <c:if test="${activeTab == 'comment'}">
            hideAllExceptThis("#commentInfo");
            setActiveNavTab.call($(".commentInfo"));
        </c:if>

        function clearMessages() {
            $('.message').text("");
        }

        function hideAllExceptThis(divIdToShow) {
            $('#profileInfo').hide();
            $('#bankInfo').hide();
            $('#sponsorInfo').hide();
            $('#addressInfo').hide();
            $('#educationInfo').hide();
            $('#studentFeeInfo').hide();
            $('#commentInfo').hide();

            $(divIdToShow).show();
            $('.col-md-7').show();
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
            $(".studentFeeInfo").click( function(evt) {
                evt.preventDefault();
                hideAllExceptThis("#studentFeeInfo");
                setActiveNavTab.call(this);
            });
        });

        $(function() {
            $(".commentInfo").click( function(evt) {
                evt.preventDefault();
                hideAllExceptThis("#commentInfo");
                setActiveNavTab.call(this);
            });
        });

        $(function() {
            $(".datepickerDob").datepicker({
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