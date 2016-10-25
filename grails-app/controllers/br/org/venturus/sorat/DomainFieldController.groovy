package br.org.venturus.sorat

import static org.springframework.http.HttpStatus.*;
import br.org.venturus.helper.ClonerHelper;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class DomainFieldController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def show(DomainField domainField) {
    render (view: "show", model: [domainField: domainField, domainInstance: domainField.domain, requirementInstance: domainField.domain.requirement, projectInstance: domainField.domain.requirement.project]);
  }

  def copy(DomainField sourceInstance) {
    def domainField = ClonerHelper.cloneDomainField(sourceInstance);
    render (view: "create", model: [domainField: domainField, domainInstance: domainField.domain, requirementInstance: domainField.domain.requirement, projectInstance: domainField.domain.requirement.project]);
  }
  
  def create(Domain domainInstance) {
    def domainField = new DomainField(params);
    domainField.domain = domainInstance;
    render (view: "create", model: [domainField: domainField, domainInstance: domainField.domain, requirementInstance: domainField.domain.requirement, projectInstance: domainField.domain.requirement.project]);
  }

  @Transactional
  def save(DomainField domainField) {
    if (domainField == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (domainField.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "create", model: [domainField: domainField, domainInstance: domainField.domain, requirementInstance: domainField.domain.requirement, projectInstance: domainField.domain.requirement.project]);
      return
    }
                
    def project = domainField.domain.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    domainField.fieldOrder = domainField.domain.fieldList.size() + 1;
    domainField.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Field created successfully.";
        redirect domainField;
      }
      '*' { respond domainField, [status: CREATED] }
    }
  }

  def edit(DomainField domainField) {
    render (view: "edit", model: [domainField: domainField, domainInstance: domainField.domain, requirementInstance: domainField.domain.requirement, projectInstance: domainField.domain.requirement.project]);
  }

  @Transactional
  def update(DomainField domainField) {
    if (domainField == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (domainField.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "edit", model: [domainField: domainField, domainInstance: domainField.domain, requirementInstance: domainField.domain.requirement, projectInstance: domainField.domain.requirement.project]);
      return
    }
        
    def project = domainField.domain.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    domainField.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Field updated successfully.";
        redirect domainField
      }
      '*'{ respond domainField, [status: OK] }
    }
  }
  
  @Transactional
  def changeOrder(Domain domain) {
    def fieldChanged = DomainField.get(params.field);
    def newOrder = params.order.toInteger() + 1;
    if(fieldChanged.fieldOrder > newOrder){
      domain.fieldList.findAll{it.fieldOrder >= newOrder}.each{testCase ->
        testCase.fieldOrder++;
      }
      fieldChanged.fieldOrder = newOrder;
    }else{
      domain.fieldList.findAll{it.fieldOrder <= newOrder}.each{testCase ->
        testCase.fieldOrder--;
      }
      fieldChanged.fieldOrder = newOrder;
    }
    this.reorderFieldOrder(domain);
    render status: OK
  }

  @Transactional
  def delete(DomainField domainField) {
    def domainId = domainField.domain.id;
    if (domainField == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
                        
    def project = domainField.domain.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    domainField.delete flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Field deleted successfully.";
        redirect(controller: "domain", action: "show", id: domainId);
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = "Field not found.";
        redirect controller: "project", action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
  
  protected void reorderFieldOrder(Domain domain){
    def order = 1;
    domain.fieldList.sort{it.fieldOrder}.each{field ->
      field.fieldOrder = order;
      order++;
      field.save flush:true;
    }
  }
        
  protected void increaseVersion(Project project){
    def projectVersion = project.projectVersion.tokenize('.');
    project.projectVersion = projectVersion[0] + "." + projectVersion[1] + "." + ((projectVersion[2]).toInteger() + 1); 
  }
}
