<div class="row-fluid">
    <ul class="nav nav-tabs nav-stacked nav-side">
        <li style="visibility:visible"><a href="/HelloWorld/sponsor/add" class="1st-element">Add</a></li>
        <li style="visibility:visible"><a href="" class="addressInfo">Address Info</a></li>
    </ul>
</div>

<script type="text/javascript">

    $('.1st-element').click(function() {
        $('ul.nav-side li').each(function() {
//            alert($(this).text());
            $(this).show();
        });
    });

</script>