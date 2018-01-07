<div class="row">
    <ul class="nav nav-pills nav-stacked nav-side">
        <li class=""><a href="/lsf/report/awaitingSponsorship" class="reportA">Awaiting Sponsorship</a></li>
        <li class=""><a href="/lsf/report/currentlySponsored" class="reportC">Currently Sponsored</a></li>
        <li class=""><a href="/lsf/report/formerlySponsored" class="reportF">Formerly Sponsored</a></li>
        <li class=""><a href="/lsf/report/applicationExpired" class="reportAE">Application Expired</a></li>
        <li class=""><a href="/lsf/report/agent" class="reportAgent">By Agent</a></li>
        <li class=""><a href="/lsf/report/allSponsors" class="reportS">Sponsors</a></li>
    </ul>
</div>
<script src="<c:url value='/js/navbar-activation.js'/>"></script>

<script type='text/javascript'>

    <c:if test="${activeTab == 'reportA'}">
    setActiveNavTab.call($(".reportA"));
    </c:if>

    <c:if test="${activeTab == 'reportC'}">
    setActiveNavTab.call($(".reportC"));
    </c:if>

    <c:if test="${activeTab == 'reportF'}">
    setActiveNavTab.call($(".reportF"));
    </c:if>

    <c:if test="${activeTab == 'reportAE'}">
    setActiveNavTab.call($(".reportAE"));
    </c:if>

    <c:if test="${activeTab == 'reportAgent'}">
    setActiveNavTab.call($(".reportAgent"));
    </c:if>

    <c:if test="${activeTab == 'reportS'}">
    setActiveNavTab.call($(".reportS"));
    </c:if>

</script>
