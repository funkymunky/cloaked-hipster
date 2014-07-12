$(document).ready(function() {
    var pathNode = window.location.pathname;

    var navListItems = $(".nav-side li a");

    if (pathNode.indexOf('list') < 0 && pathNode.indexOf('search') < 0) {
        navListItems.each(function () {
            var currentClass = $(this).parent().attr('class');
            $(this).parent().attr('class','enabled');
            $(this).parent().addClass(currentClass);
        });
        return false;
    }

});

$(".disabled").click(function(event) {
    event.preventDefault();
});

function setActiveNavTab() {
    var selectedItem = $('li.myactive');
    selectedItem.removeClass();
    $(this).parent().addClass('myactive');
}