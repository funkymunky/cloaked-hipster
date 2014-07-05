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
        <div class="row-fluid">
            <div class="span2">
                <%@ include file="/WEB-INF/jsp/include/sponsor_navbar.jsp" %>
            </div>

            <div class="span10">
                <legend>Sponsor information</legend>
                <div class="row-fluid">
                        <c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
                    <fieldset class="main-content">
                        <div class="span4">
                        <h4>Personal details</h4>
                        <spring-form:form method="POST" modelAttribute="sponsor">
                            <label>First name: <spring-form:errors path="firstName" class="alert-error"></spring-form:errors></label>
                            <spring-form:input path="firstName" placeholder="First name" />

                            <label>Last name: <spring-form:errors path="lastName" class="alert-error"></spring-form:errors></label>
                            <spring-form:input path="lastName" placeholder="Last name"/>

                            <label>Phone 1: <spring-form:errors path="phone1" class="alert-error"></spring-form:errors></label>
                            <spring-form:input path="phone1" placeholder="Phone 1" id="phone1"/>

                            <label>Phone 2: <spring-form:errors path="phone2" class="alert-error"></spring-form:errors></label>
                            <spring-form:input path="phone2" placeholder="Phone 2" id="phone2"/>
                            <br/>
                            <c:choose>
                            <c:when test="${updateMode != true}">
                            <button type="submit" class="btn btn-primary">Add sponsor</button>
                            </c:when>
                            <c:otherwise>
                            <button type="submit" class="btn btn-primary">Update sponsor</button>
                            </c:otherwise>
                            </c:choose>
                            <button type="button" class="btn" onclick="location.href='/HelloWorld/sponsor/list'">Cancel</button>
                        </spring-form:form>
                    </div>

                    <div class="span7" style="background-color: #EBE0D6; display:none; padding-bottom: 20px; border-radius: 10px 10px 10px 10px;">
                        <%@ include file="/WEB-INF/jsp/sponsor/addressDetails.jspf" %>
                    </div>

                    </fieldset>
                </div>
                <div class="row-fluid">
                    <c:if test="${not empty showLink}"><div id="link"><a href="/HelloWorld/sponsor/list">Return to Sponsors page</a></div></c:if>
                </div>
            </div>
        </div>
        </div>
    </body>


    <script type="text/javascript">

        <c:if test="${activeTab == 'address'}">
            hideAllExceptThis("#addressInfo");
            setActiveNavTab.call($(".addressInfo"));
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
            $('#addressInfo').hide();

            $(divIdToShow).show();
            $('.span7').show();
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
            $(".datepicker").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'dd/mm/yy',
                defaultDate: '-13y'
            });
        });
    </script>
</html>