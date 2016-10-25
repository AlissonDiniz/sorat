/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.venturus.domain

/**
 *
 * @author alisson
 */
enum FieldType {
    
  ALPHABETIC(true), NUMERIC(true), NUMERIC_FLOAT(true), ALPHANUMERIC(true), SPECIAL_CHARACTER(true), DATE(false), BOOLEAN(false), SELECT(false), ALL(true), IMAGE(false);
    
  boolean hasLimit;
  
  FieldType(boolean hasLimit){
    this.hasLimit = hasLimit;
  }

}

