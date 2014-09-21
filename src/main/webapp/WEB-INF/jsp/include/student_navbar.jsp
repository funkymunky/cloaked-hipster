<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row">
    <ul class="nav nav-pills nav-stacked nav-side">
        <sec:authorize ifAllGranted="ROLE_ADMIN">
            <li><a href="/lsf/student/add">New student</a></li>
        </sec:authorize>
        <sec:authorize ifAllGranted="ROLE_MEMBER">
            <li class="disabled"><a href="/lsf/student/add">New student</a></li>
        </sec:authorize>
        <li class="disabled"><a href="" class="addressInfo">Address Info</a></li>
        <li class="disabled"><a href="" class="educationInfo">Education Info</a></li>
        <li class="disabled"><a href="" class="bankInfo">Bank Info</a></li>
        <li class="disabled"><a href="" class="sponsorInfo">Sponsor Info</a></li>
        <li class="disabled"><a href="" class="commentInfo">Comments</a></li>
    </ul>
</div>
<script src="<c:url value='/js/navbar-activation.js'/>"></script>