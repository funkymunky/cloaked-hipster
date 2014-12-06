<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
       <style type="text/css">
          body {
            padding-top: 60px;
            padding-bottom: 40px;
          }
        </style>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
   <div class="container">
       <div class="navbar-collapse collapse">
           <ul class="nav navbar-nav">
               <li><a href="/lsf/home">Home</a></li>
               <li><a href="/lsf/student/list">Students</a></li>
               <li><a href="/lsf/sponsor/list">Sponsors</a></li>
               <li><a href="/lsf/report/awaitingSponsorship">Reports</a></li>
               <li><a href="/lsf/manage/fees">Fees</a></li>
               <li class="dropdown">
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin<span class="caret"></span></a>
                   <ul class="dropdown-menu" role="menu">
                       <li><a href="/lsf/admin/resetPassword">Reset password</a></li>
                       <li class="divider"></li>
                       <li class="dropdown-header">Nav header</li>
                       <li><a href="#">Separated link</a></li>
                   </ul>
               </li>
               <li><a href="/lsf/logout"><span class="glyphicon glyphicon-log-out"></span></a></li>
           </ul>
           <ul class="nav navbar-nav navbar-right">
               <li class="version"><a href="">Version:<spring:message code="version.number" /></a></li>
           </ul>
       </div><!--/.nav-collapse -->
   </div>
</div>


<script>
   $(document).ready(function() {
       var pathNode = window.location.pathname.split('/')[2];
       var navListItems = $(".navbar-nav li a");
       navListItems.each(function () {
           var hrefPath = $(this).attr('href').toLowerCase().split('/')[2];
           if (pathNode.toLowerCase().indexOf(hrefPath) >= 0 ) {
               $(this).parent().addClass('active');
               return false;
           }
       });
   });
</script>