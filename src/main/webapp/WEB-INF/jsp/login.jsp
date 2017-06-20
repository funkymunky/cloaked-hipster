<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Need the above three lines to get stylesheets across all pages -->

<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter"%>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.core.AuthenticationException"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>

        <style type="text/css">
            h1 {
                text-align: center;
            }
            .form-signin {
                max-width: 230px;
                margin: 0 auto;
            }
        </style>

    </head>
    <body>
    <div class="container">
        <div class="jumbotron">
        <h1><span class="lsf-jumbo">LSF Sponsorships</span></h1>

        <form class="form-signin" action="j_spring_security_check" method="POST" >
            <input type="text" class="form-control" name="j_username" id="j_username" placeholder="Username"/>
            <input type="password" class="form-control" name="j_password" id="j_password" placeholder="Password" />
            <label class="checkbox">
                <input type="checkbox" name="_spring_security_remember_me" value="remember-me"/> Remember me on this computer.
            </label>
            <button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">Login</button>
            <br/>
            <c:if test="${not empty message}"><span class="error">${message}</span></c:if>
        </form>
        </div>
    </div>
    </body>
</html>
