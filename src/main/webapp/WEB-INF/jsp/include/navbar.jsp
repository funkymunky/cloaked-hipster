       <style type="text/css">
          body {
            padding-top: 60px;
            padding-bottom: 40px;
          }
        </style>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
   <div class="container">
       <div class="navbar-header">
           <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
           </button>
           <a class="navbar-brand" href="#">LSF Sponsorship</a>
       </div>
       <div class="navbar-collapse collapse">
           <ul class="nav navbar-nav">
               <li><a href="/HelloWorld/home">Home</a></li>
               <li><a href="/HelloWorld/student/list">Students</a></li>
               <li><a href="/HelloWorld/sponsor/list">Sponsors</a></li>
               <li><a href="/HelloWorld/report/awaitingSponsorship">Reports</a></li>
               <li><a href="/HelloWorld/manage/fees">Admin</a></li>
               <li class="dropdown">
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
                   <ul class="dropdown-menu" role="menu">
                       <li><a href="/HelloWorld/logout">Logout</a></li>
                       <li class="divider"></li>
                       <li class="dropdown-header">Nav header</li>
                       <li><a href="#">Separated link</a></li>
                       <li><a href="#">One more separated link</a></li>
                   </ul>
               </li>
           </ul>
       </div><!--/.nav-collapse -->
   </div>
</div>


<script>
   $(document).ready(function() {
       var pathNode = window.location.pathname.split('/')[2];
       var navListItems = $(".nav-top li a");
       navListItems.each(function () {
           var hrefPath = $(this).attr('href').toLowerCase().split('/')[2];
           if (pathNode.toLowerCase().indexOf(hrefPath) >= 0 ) {
               $(this).parent().addClass('active');
               return false;
           }
       });
   });
</script>