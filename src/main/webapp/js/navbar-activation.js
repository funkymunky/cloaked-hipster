$(document).ready(function() {
    var pathNode = window.location.pathname;

    var navListItems = $(".nav-side li a");

    if (pathNode.indexOf('list') < 0 && pathNode.indexOf('search') < 0) {
        navListItems.each(function () {
            $(this).parent().attr('class','enabled');
        });
        return false;
    }

});

$(".disabled").click(function(event) {
    event.preventDefault();
});

