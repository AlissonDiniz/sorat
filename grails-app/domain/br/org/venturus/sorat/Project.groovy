package br.org.venturus.sorat

import br.org.venturus.sorat.security.SecUser;
import groovy.transform.ToString

@ToString(includes='name')
class Project {
  
  transient springSecurityService;
    
  String name;
  String description;
  String documentTemplate;
  String exportTemplate;
  String projectVersion = "1.0.0";
  String userCreated;
  String userUpdated;
  Date dateCreated;
  Date lastUpdated;
  ProjectTestDomain domainClass;
  ProjectTestDomain domainTestClass;
  
  static hasMany = [sectionList: Section, requirementList: Requirement, team: SecUser, testFileList: ProjectTestFile, templateTestList: ProjectTestTemplate];
  
  static constraints = {
    name blank: false, unique: true;
    description nullable: true;
    documentTemplate nullable: true;
    exportTemplate nullable: true;
    projectVersion blank: false;
    domainClass nullable: true;
    domainTestClass nullable: true;
    userCreated nullable: true;
    userUpdated nullable: true;
  }
    
  static mapping = {
    description type: 'text'
    documentTemplate type: 'text'
    exportTemplate type: 'text'
  }
  
  def beforeInsert() {
    this.userCreated = springSecurityService.getCurrentUser().username;
  }

  def beforeUpdate() {
    this.userUpdated = springSecurityService.getCurrentUser().username;
  }
}
