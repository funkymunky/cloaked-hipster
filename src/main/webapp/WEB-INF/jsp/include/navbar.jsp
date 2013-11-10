       <style type="text/css">
          body {
            padding-top: 60px;
            padding-bottom: 40px;
          }
        </style>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
        <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="brand" href="#">LSF Sponsorship</a>
        <div class="nav-collapse collapse">
            <ul class="nav">
                <li><a href="/HelloWorld/home">Home</a></li>
                <li><a href="/HelloWorld/student">Students</a></li>
                <li><a href="#contact">Games</a></li>
                <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li class="nav-header">Nav header</li>
                    <li><a href="#">Separated link</a></li>
                    <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
                <li><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
            </ul>
        <!--
        <form class="navbar-form pull-right">
            <input class="span2" type="text" placeholder="Email">
            <input class="span2" type="password" placeholder="Password">
            <button type="submit" class="btn">Sign in</button>
        </form>
        -->
        </div><!--/.nav-collapse -->
        </div>
    </div>
</div>


<script>
   $(document).ready(function() {
       var path = window.location.pathname;
       var navListItems = $(".nav li a");
       navListItems.each(function () {
           var hrefPath = $(this).attr('href').toLowerCase();
           if (path.toLowerCase().indexOf(hrefPath) >= 0 ) {
               $(this).parent().addClass('active');
               return false;
           }
       });
   });
</script>