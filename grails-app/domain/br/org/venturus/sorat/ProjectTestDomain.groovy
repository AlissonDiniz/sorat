package br.org.venturus.sorat

import groovy.transform.ToString

@ToString(includes='name')
class ProjectTestDomain {
  
  transient springSecurityService;

  String name;
  String path;
  String content;
  boolean isTest = false;
  String userCreated;
  String userUpdated;
  Date dateCreated;
  Date lastUpdated;
  
  static constraints = {
    name blank: false;
    path blank: false;
    content nullable: true;
    userCreated nullable: true;
    userUpdated nullable: true;
  }
    
  static mapping = {
    content type: 'text'
  }
  
  def beforeInsert() {
    this.userCreated = springSecurityService.getCurrentUser().username;
  }

  def beforeUpdate() {
    this.userUpdated = springSecurityService.getCurrentUser().username;
  }
}
