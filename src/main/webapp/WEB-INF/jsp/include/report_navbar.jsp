<div class="row-fluid">
    <ul class="nav nav-tabs nav-stacked nav-side">
        <li class=""><a href="/HelloWorld/report/awaitingSponsorship" class="reportA">Awaiting Sponsorship</a></li>
        <li class=""><a href="/HelloWorld/report/currentlySponsored" class="reportC">Currently Sponsored</a></li>
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

</script>