package br.org.venturus.sorat

import br.org.venturus.domain.FieldType;
import groovy.transform.ToString

@ToString(includes='name')
class DomainField {
  
  transient springSecurityService;
    
  String name;
  FieldType type;
  String elementId;
  int fieldLimitMin;
  int fieldLimitMax;
  int fieldDecimal;
  boolean required;
  boolean fieldUnique;
  boolean searchable;
  boolean filterable;
  boolean joinedField;
  boolean sortable;
  boolean visibleInTable;
  int orderInTable;
  int fieldOrder = 1;
  String description;
  String userCreated;
  String userUpdated;
  Date dateCreated;
  Date lastUpdated;
  
  static belongsTo = [domain: Domain];
    
  static constraints = {
    name blank: false;
    type blank: false;
    elementId blank: false;
    fieldLimitMin blank: false;
    fieldLimitMax blank: false;
    fieldDecimal blank: false;
    required blank: false;
    fieldUnique blank: false;
    searchable blank: false;
    filterable blank: false;
    joinedField blank: false;
    sortable blank: false;
    visibleInTable blank: false;
    orderInTable blank: false;
    fieldOrder blank: false;
    description nullable: true;
    userCreated nullable: true;
    userUpdated nullable: true;
  }
    
  static mapping = {
    description type: 'text'
  }
  
  def beforeInsert() {
    this.userCreated = springSecurityService.getCurrentUser().username;
  }

  def beforeUpdate() {
    this.userUpdated = springSecurityService.getCurrentUser().username;
  }
}
