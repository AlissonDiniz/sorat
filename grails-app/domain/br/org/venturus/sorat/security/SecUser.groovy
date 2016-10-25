package br.org.venturus.sorat.security

import groovy.transform.EqualsAndHashCode;
import groovy.transform.ToString;
import br.org.venturus.sorat.Project;

@EqualsAndHashCode(includes='username')
@ToString(includes='name')
class SecUser implements Serializable {

  private static final long serialVersionUID = 1;
  transient springSecurityService;

  String name;
  String email;
  String photo;
  String username;
  boolean enabled = true;
  boolean accountExpired;
  boolean accountLocked;
  boolean passwordExpired;
  String userCreated;
  String userUpdated;
  Date dateCreated;
  Date lastUpdated;
  
  static constraints = {
    name blank: false
    email blank: false
    photo nullable: true
    username blank: false, unique: true
    userCreated nullable: true;
    userUpdated nullable: true;
  }
  
  static mapping = {
    photo type: 'text';
  }

  Set<SecRole> getAuthorities() {
    SecUserSecRole.findAllBySecUser(this)*.secRole
  }
  
  def beforeInsert() {
    this.userCreated = springSecurityService.getCurrentUser().username;
  }

  def beforeUpdate() {
    this.userUpdated = springSecurityService.getCurrentUser().username;
  }
  
  public String toString(){
    return this.name;
  }
  
}
