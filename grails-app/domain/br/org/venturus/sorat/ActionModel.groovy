package br.org.venturus.sorat

import groovy.transform.ToString

@ToString(includes='name')
class ActionModel {
  
  transient springSecurityService;

  String name;
  String description;
  String userCreated;
  String userUpdated;
  Date dateCreated;
  Date lastUpdated;
  
  static belongsTo = [requirementAction: RequirementAction];
  static hasMany = [fieldList: ActionModelField];

  static constraints = {
    name blank: false;
    description nullable: true;
    requirementAction blank: false;
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
