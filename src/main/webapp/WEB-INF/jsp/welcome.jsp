<%@ include file="/WEB-INF/jsp/include/include.jsp" %>

<html>
<head>

    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
    <%--<link href="<c:url value="/css/main.css"/>" rel="stylesheet" type="text/css" media="screen" />--%>

<%--<style type="text/css">--%>
        <%--.mytest a {--%>
            <%--color: green;--%>
        <%--}--%>

        <%--.mytest1 {--%>
            <%--color: blue;--%>
        <%--}--%>
    <%--</style>--%>


    <title> Spring mvc 3.2</title>

</head>
<body>
<h2>${message}</h2>
<c:url value="/account" var="accountPage"/>

<span class="something">
    <p>hi there i am testing some text</p>
</span>
<span class="mytest">
    <a href="${accountPage}">List accounts</a>
</span>

</body>
</html>