/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function(){
  $('select#type').change(function(){
    changeType($(this));
  });
  
  $('input#visibleInTable').click(function(){
    clickTable($(this));
  });
  
  function changeType(element){
    var value = element.val();
    var hasLimit = $('select#templateType option[value="'+value+'"]').text();
    if(hasLimit === 'true'){
      $('div#field-limit-container').removeClass('hide');
      $('div#quantity-decimal-container').removeClass('hide');
    }else{
      $('div#field-limit-container').addClass('hide');
      $('div#quantity-decimal-container').addClass('hide');
    }
    
    if(value === 'NUMERIC_FLOAT'){
      $('div#quantity-decimal-container').removeClass('hide');
    }else{
      $('div#quantity-decimal-container').addClass('hide');
    }
  }
  
  function clickTable(element){
    if(element.is(':checked')){
      $('div.visibleInTable').removeClass('hide');
    }else{
      $('div.visibleInTable').addClass('hide');
    }
  }
  
  changeType($('select#type'));
  clickTable($('input#visibleInTable'));
});
  
