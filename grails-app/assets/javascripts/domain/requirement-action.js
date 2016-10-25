/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
  var screensContainer = $('div#screens-container');
  var input = $('input#screens');
  $('input#addScreen').change(function () {
    var file = $(this)[0].files[0];
    if (file) {
      var reader = new FileReader();
      reader.onload = function (readerEvt) {
        var binaryString = readerEvt.target.result;
        var image = btoa(binaryString);
        input.val(input.val()+'<img src="data:image/png;base64,' + image + '" /><br />');
        var button = $('<i class="glyphicon glyphicon-trash"></i><br />');
        var img = $('<img src="data:image/png;base64,' + image + '" /><br />');
        screensContainer.append(button).append(img);
        bindImgButton(img, button);
      };
      reader.readAsBinaryString(file);
    }
  });
  screensContainer.find('img').each(function(){
    var button = $('<i class="glyphicon glyphicon-trash"></i><br />');
    var img = $(this);
    img.before(button);
    bindImgButton(img, button);
  });
  
  function bindImgButton(img, button){
    button.click(function(){
      $(this).next().remove();
      $(this).remove();
      img.next().remove();
      img.remove();
      input.val('');
      screensContainer.find('img').each(function(){
        input.val(input.val()+'<img src="' + $(this).attr('src') + '" /><br />');
      });
    });
  }
});

