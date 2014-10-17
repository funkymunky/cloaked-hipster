var originalStudent
var originalAddressContent
var originalEducationContent
var originalBankContent
var originalSponsorshipContent
var originalCommentContent

$(document).ready(function() {
    originalStudent = jQuery('#student input[type=text]').serialize()
    originalAddressContent = jQuery('#student\\.address input[type=text]').serialize()
    originalEducationContent = jQuery('#student\\.education input[type=text], select').serialize()
    originalBankContent = jQuery('#student\\.bank input[type=text], select').serialize()
    originalSponsorshipContent = jQuery('#student\\.sponsorship input[type=text], select').serialize()
    originalCommentContent = jQuery('#student\\.commenta input[type=text], textarea').serialize()
});

submitClicked=false;

$(function() {
    $(":input[type=submit]").click( function() {
        submitClicked=true;
    })
});

function anyChanges() {
    var studentContent = jQuery('#student input[type=text]').serialize()
    var addressContent = jQuery('#student\\.address input[type=text]').serialize()
    var educationContent = jQuery('#student\\.education input[type=text], select').serialize()
    var bankContent = jQuery('#student\\.bank input[type=text], select').serialize()
    var sponsorshipContent = jQuery('#student\\.sponsorship input[type=text], select').serialize()
    var commentContent = jQuery('#student\\.comments input[type=text], textarea').serialize()
    if ( (studentContent != originalStudent) ||
         (addressContent != originalAddressContent) ||
         (educationContent != originalEducationContent) ||
         (bankContent != originalBankContent) ||
         (sponsorshipContent != originalSponsorshipContent) ||
         (commentContent != originalCommentContent)) {
        return true;

    }
    return false;
}

window.onbeforeunload = function(myEvt) {
    if (anyChanges() && !submitClicked) {
        return "You have some unsaved changes.";
    }
}
