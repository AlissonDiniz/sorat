/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function(){
  $('input#belongsField').click(function(){
    belongsField($(this));
  });
  
  function belongsField(element){
    if(element.is(':checked')){
      $('div.belongs-field').removeClass('hide');
    }else{
      $('div.belongs-field').addClass('hide');
    }
  }
  belongsField($('input#belongsField'));
});
  
