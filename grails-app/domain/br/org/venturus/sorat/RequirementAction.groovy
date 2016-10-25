package br.org.venturus.sorat

import br.org.venturus.domain.ActionType;
import groovy.transform.ToString

@ToString(includes='title')
class RequirementAction {
  
  transient springSecurityService;
    
  String title;
  int actionOrder = 1;
  ActionType type;
  String description;
  String userCreated;
  String userUpdated;
  Date dateCreated;
  Date lastUpdated;
  String screens;
    
  static belongsTo = [requirement: Requirement];
  static hasOne = [actionModel: ActionModel];
  static hasMany = [testCaseList: TestCase];

  static constraints = {
    title blank: false;
    actionOrder blank: false;
    type blank: false;
    description nullable: true;
    screens nullable: true;
    actionModel nullable: true;
    userCreated nullable: true;
    userUpdated nullable: true;
  }
    
  static mapping = {
    description type: 'text';
    screens type: 'text';
  }
  
  def beforeInsert() {
    this.userCreated = springSecurityService.getCurrentUser().username;
  }

  def beforeUpdate() {
    this.userUpdated = springSecurityService.getCurrentUser().username;
  }
}
