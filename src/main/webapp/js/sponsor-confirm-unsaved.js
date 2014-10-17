var originalSponsor
var originalAddressContent

$(document).ready(function() {
    originalSponsor = jQuery('#sponsor input[type=text]').serialize()
    originalAddressContent = jQuery('#sponsor\\.address input[type=text]').serialize()
});

submitClicked=false;

$(function() {
    $(":input[type=submit]").click( function() {
        submitClicked=true;
    })
});

function anyChanges() {
    var sponsorContent = jQuery('#sponsor input[type=text]').serialize()
    var addressContent = jQuery('#sponsor\\.address input[type=text]').serialize()
    if ( (sponsorContent != originalSponsor) ||
         (addressContent != originalAddressContent)) {
        return true;
    }
}

window.onbeforeunload = function(myEvt) {
    if (anyChanges() && !submitClicked) {
        return "You have some unsaved changes.";
    }
}
