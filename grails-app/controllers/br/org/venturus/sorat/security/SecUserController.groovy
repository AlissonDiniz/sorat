package br.org.venturus.sorat.security

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class SecUserController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
  
  def springSecurityService;
  def passwordEncoder;
    
  def index(Integer max) {
    params.max = Math.min(max ?: 15, 100)
    respond SecUser.list(params), model:[secUserCount: SecUser.count()]
  }

  def show(SecUser secUser) {
    respond secUser
  }
  
  def create() {
    respond new SecUser(params)
  }

  @Transactional
  def save(SecUser secUser) {
    if (secUser == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (secUser.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond secUser.errors, view:'create'
      return
    }
    secUser.save flush:true
    
    params.roles.each{id ->
      def secRole = SecRole.get(id);
      SecUserSecRole.create(secUser, secRole);
    }
    request.withFormat {
      form multipartForm {
        flash.message = "User created successfully."
        redirect secUser
      }
      '*' { respond secUser, [status: CREATED] }
    }
  }

  def edit(SecUser secUser) {
    respond secUser
  }
  
  def changePassword() {
    def secUser = SecUser.get(springSecurityService.currentUser.id);
    respond secUser;
  }
  
  @Transactional
  def updatePassword() {
    def secUser = SecUser.get(springSecurityService.currentUser.id);
    secUser.password = params.newPassword;
    secUser.save flush: true;
    flash.message = "Password updated successfully."
    render model:[secUser: secUser], view:'changePassword'
  }

  @Transactional
  def update(SecUser secUser) {
    if (secUser == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (secUser.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond secUser.errors, view:'edit'
      return
    }
    
    params.roles.each{id ->
      def secRole = SecRole.get(id);
      if (!secUser.authorities.contains(secRole)) {
        SecUserSecRole.create(secUser, secRole);
      }
    }
    secUser.authorities.each{secRole ->
      def roleId = params.roles.find{it.toInteger() == secRole.id};
      if(!roleId){
        SecUserSecRole.remove(secUser, secRole);
      }
    }
    
    secUser.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "User updated successfully."
        redirect secUser
      }
      '*'{ respond secUser, [status: OK] }
    }
  }

  @Transactional
  def delete(SecUser secUser) {
    if (secUser == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    secUser.delete flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "User deleted successfully."
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = "User not found."
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
}
