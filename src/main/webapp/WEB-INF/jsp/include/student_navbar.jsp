<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="row-fluid">
    <ul class="nav nav-tabs nav-stacked nav-side">
        <sec:authorize ifAllGranted="ROLE_ADMIN">
            <li><a href="/HelloWorld/student/add">Add</a></li>
        </sec:authorize>
        <sec:authorize ifAllGranted="ROLE_MEMBER">
            <li class="disabled"><a href="/HelloWorld/student/add">Add</a></li>
        </sec:authorize>
        <li class="disabled"><a href="" class="addressInfo">Address Info</a></li>
        <li class="disabled"><a href="" class="educationInfo">Education Info</a></li>
        <li class="disabled"><a href="" class="bankInfo">Bank Info</a></li>
        <li class="disabled"><a href="" class="sponsorInfo">Sponsor Info</a></li>
    </ul>
</div>
<script src="<c:url value='/js/navbar-activation.js'/>"></script>