// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require jquery-ui
//= require bootstrap/js/bootstrap
//= require summernote/summernote
//= require multiselect/bootstrap-multiselect
//= require_self

$(document).ready(function () {
  $('button.btn-delete').click(function(){
    if(confirm('Are you sure?')){
      console.log('OK');
      $('form#formDelete').submit();
    }
  });
  $('select.multiple').multiselect();
  $('textarea.summernote').summernote({height: 300});
  $("div#requirement-sortable-container").sortable({
    update: function (e, ui) {
      $.post("/requirement/changeOrder/" + $('div#show-project').data('id'), {requirement: ui.item.data('id'), order: ui.item.index()}).fail(function () {
        alert("An error has occurred");
      });
    }
  });
  $("div#section-sortable-container").sortable({
    update: function (e, ui) {
      $.post("/section/changeOrder/" + $('div#show-project').data('id'), {section: ui.item.data('id'), order: ui.item.index()}).fail(function () {
        alert("An error has occurred");
      });
    }
  });
  $("div#action-sortable-container").sortable({
    update: function (e, ui) {
      $.post("/requirementAction/changeOrder/" + $('div#show-requirement').data('id'), {requirementAction: ui.item.data('id'), order: ui.item.index()}).fail(function () {
        alert("An error has occurred");
      });
    }
  });
  $("div#testcase-sortable-container").sortable({
    update: function (e, ui) {
      $.post("/testCase/changeOrder/" + $('div#show-requirementAction').data('id'), {testCase: ui.item.data('id'), order: ui.item.index()}).fail(function () {
        alert("An error has occurred");
      });
    }
  });
  $("div#actionModelField-sortable-container").sortable({
    update: function (e, ui) {
      $.post("/actionModelField/changeOrder/" + $('div#show-actionModel').data('id'), {field: ui.item.data('id'), order: ui.item.index()}).fail(function () {
        alert("An error has occurred");
      });
    }
  });
  $("div#domainField-sortable-container").sortable({
    update: function (e, ui) {
      $.post("/domainField/changeOrder/" + $('div#show-domain').data('id'), {field: ui.item.data('id'), order: ui.item.index()}).fail(function () {
        alert("An error has occurred");
      });
    }
  });
});