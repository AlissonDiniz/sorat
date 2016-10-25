package br.org.venturus.sorat

import groovy.transform.ToString

@ToString(includes='name')
class Domain {
  
  transient springSecurityService;
    
  String name;
  String elementId;
  String path;
  String menu;
  String description;
  String userCreated;
  String userUpdated;
  Date dateCreated;
  Date lastUpdated;
  static belongsTo = [requirement: Requirement];
  static hasMany = [fieldList: DomainField];

  static constraints = {
    name blank: false;
    elementId blank: false;
    path blank: false;
    menu nullable: true;
    description nullable: true;
    requirement blank: false;
    userCreated nullable: true;
    userUpdated nullable: true;
  }
    
  static mapping = {
    description type: 'text'
    fieldList cascade: 'all-delete-orphan'
  }
  
  def beforeInsert() {
    this.userCreated = springSecurityService.getCurrentUser().username;
  }

  def beforeUpdate() {
    this.userUpdated = springSecurityService.getCurrentUser().username;
  }
}
