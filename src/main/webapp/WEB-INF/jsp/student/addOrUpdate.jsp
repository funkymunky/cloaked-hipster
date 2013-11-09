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
                    <td>First name:</td>
                    <td><spring-form:input path="firstName" /></td>
                </tr>
                <tr>
                    <td>Other names:</td>
                    <td><spring-form:input path="otherNames" /></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td><spring-form:input path="lastName" /></td>
                </tr>
            </table>
                <c:choose>
                    <c:when test="${updateMode != true}">
                        <input type="submit" value="Add student"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Update student"/>
                    </c:otherwise>
                </c:choose>
            </spring-form:form>

            <c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
            <c:if test="${not empty showLink}"><div id="link"><a href="/HelloWorld/student">Return to Students page</a></div></c:if>
        </div>
    </body>
</html>