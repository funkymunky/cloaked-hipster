<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
    <title> Welcome</title>

</head>
<body>
<div class="container">
    <div class="hero-unit">
    <h1><span class="something">Awesome responsive layout</span></h1>
    <p>Hello guys i am a ".hero-unit" and you can use me if you wanna say something important.</p>
    <p><a class="btn btn-primary btn-large">Super important &raquo;</a></p>
    </div><!-- .hero-unit -->

        <h2>${message}</h2>
        <c:url value="/account" var="accountPage"/>

        <span class="something">
            <p>hi there i am testing some text</p>
        </span>
        <span class="mytest">
            <a href="${accountPage}">List accounts</a>
        </span>
        <br/>
        <a href="/HelloWorld/login">Back to login page </a>
</div>
</body>
</html>