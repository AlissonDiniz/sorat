package br.org.venturus.sorat

import br.org.venturus.domain.TestType;
import br.org.venturus.domain.FieldType;
import br.org.venturus.domain.RuleTest;
import groovy.transform.ToString

@ToString(includes='name')
class ProjectTestTemplate {
  
  transient springSecurityService;

  String name;
  FieldType fieldType;
  TestType actionType;
  RuleTest ruleTest;
  boolean belongsField = false;
  String content;
  String userCreated;
  String userUpdated;
  Date dateCreated;
  Date lastUpdated;
  
  static belongsTo = [project: Project];
  static hasOne = [testCaseTemplate: TestCaseTemplate];

  static constraints = {
    name blank: false;
    content nullable: true;
    actionType blank: false;
    fieldType blank: false;
    ruleTest blank: false;
    belongsField blank: false;
    testCaseTemplate nullable: true;
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
