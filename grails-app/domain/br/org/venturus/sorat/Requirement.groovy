package br.org.venturus.sorat

import groovy.transform.ToString

@ToString(includes='name')
class Requirement {
  
  transient springSecurityService;
    
  String name;
  int reqOrder = 1;
  String description;
  String userCreated;
  String userUpdated;
  Date dateCreated;
  Date lastUpdated;
  static hasOne = [domain: Domain];
  static belongsTo = [project: Project];
  static hasMany = [actionList: RequirementAction];

  static constraints = {
    name blank: false;
    reqOrder blank: false;
    description nullable: true;
    domain nullable: true;
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
