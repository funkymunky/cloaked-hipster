
var originalSponsor
var originalAddressContent

$(document).ready(function() {
    originalSponsor = jQuery('#sponsor input[type=text]').serialize()
    originalAddressContent = jQuery('#sponsor\\.address input[type=text]').serialize()
});



function onClose() {
    var sponsorContent = jQuery('#sponsor input[type=text]').serialize()
    var addressContent = jQuery('#sponsor\\.address input[type=text]').serialize()
    if ( (sponsorContent != originalSponsor) ||
         (addressContent != originalAddressContent)) {
        return "There are some unsaved changes.";
    }
}

window.onbeforeunload = onClose;
