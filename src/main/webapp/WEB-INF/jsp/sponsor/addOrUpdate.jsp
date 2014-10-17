<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
        <title>Sponsors</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
        <div class="container-fluid">
        <div class="row">
            <div class="col-md-2">
                <%@ include file="/WEB-INF/jsp/include/sponsor_navbar.jsp" %>
            </div>

            <div class="col-md-10">
                <legend>Sponsor information</legend>
                <div class="row">
                        <c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
                    <fieldset class="main-content">
                        <div class="col-md-4">
                        <h4>Personal details</h4>
                        <spring-form:form role="form" method="POST" modelAttribute="sponsor">
                            <div class="form-group">
                                <label>First name: <spring-form:errors path="firstName" class="alert-error"></spring-form:errors></label>
                                <spring-form:input class="form-control" path="firstName" placeholder="First name" />
                            </div>
                            <div class="form-group">
                                <label>Last name: <spring-form:errors path="lastName" class="alert-error"></spring-form:errors></label>
                                <spring-form:input class="form-control" path="lastName" placeholder="Last name"/>
                            </div>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <spring-form:input path="email" id="email" class="form-control" placeholder="Email"/>
                            </div>
                            <div class="form-group">
                                <label>Phone 1: <spring-form:errors path="phone1" class="alert-error"></spring-form:errors></label>
                                <spring-form:input class="form-control" path="phone1" placeholder="Phone 1" id="phone1"/>
                            </div>
                            <div class="form-group">
                                <label>Phone 2: <spring-form:errors path="phone2" class="alert-error"></spring-form:errors></label>
                                <spring-form:input class="form-control" path="phone2" placeholder="Phone 2" id="phone2"/>
                            </div>
                            <br/>
                            <c:choose>
                                <c:when test="${updateMode != true}">
                                    <button type="submit" class="btn btn-primary">Add sponsor</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-primary">Update sponsor</button>
                                </c:otherwise>
                            </c:choose>
                            <button type="button" class="btn" onclick="location.href='/lsf/sponsor/list'">Cancel</button>
                        </spring-form:form>
                    </div>

                    <div class="col-md-7" style="background-color: #EBE0D6; display:none; padding-bottom: 20px; border-radius: 10px 10px 10px 10px;">
                        <%@ include file="/WEB-INF/jsp/sponsor/sponsorDetails.jspf" %>
                        <%@ include file="/WEB-INF/jsp/sponsor/addressDetails.jspf" %>
                    </div>

                    </fieldset>
                </div>
                <div class="row">
                    <c:if test="${not empty showLink}"><div id="link"><a href="/lsf/sponsor/list">Return to Sponsors page</a></div></c:if>
                </div>
            </div>
        </div>
        </div>
    </body>

    <script src="<c:url value='/js/sponsor-confirm-unsaved.js'/>"></script>

    <script type="text/javascript">
        $("#sponsorDetailedInfo").show();
        $('.col-md-7').show();

        <c:if test="${activeTab == 'address'}">
            hideAllExceptThis("#addressInfo");
            setActiveNavTab.call($(".addressInfo"));
        </c:if>

        function clearMessages() {
            $('.message').text("");
        }

        function hideAllExceptThis(divIdToShow) {
            $('#sponsorDetailedInfo').hide();
            $('#addressInfo').hide();

            $(divIdToShow).show();
            $('.col-md-7').show();
//            event.preventDefault();
        }

        $(function() {
            $(".addressInfo").click( function(evt) {
                evt.preventDefault();
                hideAllExceptThis("#addressInfo");
                setActiveNavTab.call(this);
                clearMessages();
            });
        });

        $(function() {
            $(":input[type=submit]").click( function() {
                submitClicked=true;
            })
        });

        $(function() {
            $(".datepicker").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'dd/mm/yy',
                defaultDate: '-13y'
            });
        });
    </script>
</html>