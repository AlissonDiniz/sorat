package br.org.venturus.sorat

import br.org.venturus.domain.KeyWord
import groovy.transform.ToString

@ToString(includes='title')
class TestCase {
  
  transient springSecurityService;

  String title;
  String summary;
  String preConditions;
  String steps;
  String expectedResults;
  KeyWord keyWord;
  int testOrder = 1;
  String userCreated;
  String userUpdated;
  Date dateCreated;
  Date lastUpdated;
  static belongsTo = [requirementAction: RequirementAction];

  static constraints = {
    title blank: false;
    summary blank: false;
    preConditions blank: false;
    steps blank: false;
    expectedResults blank: false;
    keyWord blank: false;
    testOrder blank: false;
    requirementAction blank: false;
    userCreated nullable: true;
    userUpdated nullable: true;
  }
    
  static mapping = {
    summary type: 'text'
    preConditions type: 'text'
    steps type: 'text'
    expectedResults type: 'text'
  }
  
  def beforeInsert() {
    this.userCreated = springSecurityService.getCurrentUser().username;
  }

  def beforeUpdate() {
    this.userUpdated = springSecurityService.getCurrentUser().username;
  }
}
