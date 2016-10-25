package br.org.venturus.sorat

import groovy.transform.ToString

@ToString(includes='name')
class Section {
  
  transient springSecurityService;
  
  String name;
  int secOrder = 1;
  String description;
  String userCreated;
  String userUpdated;
  Date dateCreated;
  Date lastUpdated;

  static belongsTo = [project: Project];
  
  static constraints = {
    name blank: false;
    secOrder blank: false;
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
