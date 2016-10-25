package br.org.venturus.sorat

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;
import br.org.venturus.helper.ClonerHelper;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Transactional(readOnly = true)
class RequirementController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
  def testProjectService;

  def show(Requirement requirement) {
    render (view: "show", model: [requirement: requirement, projectInstance: requirement.project]);
  }

  def copy(Requirement sourceInstance) {
    def requirement = ClonerHelper.cloneRequirement(sourceInstance);
    render (view: "create", model: [requirement: requirement, projectInstance: requirement.project]);
  }

  def create(Project projectInstance) {
    def requirement = new Requirement(params);
    requirement.project = projectInstance;
    render (view: "create", model: [requirement: requirement, projectInstance: requirement.project]);
  }

  @Transactional
  def save(Requirement requirement) {
    if (requirement == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (requirement.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "create", model: [requirement: requirement, projectInstance: requirement.project]);
      return
    }
    def project = requirement.project;
    this.increaseVersion(project);
    project.save flush:true;
    requirement.reqOrder = project.requirementList.size() + 1;
    requirement.save flush:true;
    
    request.withFormat {
      form multipartForm {
        flash.message = "Requirement created successfully.";
        redirect requirement
      }
      '*' { respond requirement, [status: CREATED] }
    }
  }

  def edit(Requirement requirement) {
    render (view: "edit", model: [requirement: requirement, projectInstance: requirement.project]);
  }

  @Transactional
  def update(Requirement requirement) {
    if (requirement == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (requirement.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "edit", model: [requirement: requirement, projectInstance: requirement.project]);
      return
    }
    def project = requirement.project;
    this.increaseVersion(project);
    project.save flush:true;
    requirement.save flush:true;
    
    request.withFormat {
      form multipartForm {
        flash.message = "Requirement updated successfully.";
        redirect requirement
      }
      '*'{ respond requirement, [status: OK] }
    }
  }
  
  @Transactional
  def changeOrder(Project project) {
    def requirementChanged = Requirement.get(params.requirement);
    def newOrder = params.order.toInteger() + 1;
    if(requirementChanged.reqOrder > newOrder){
      project.requirementList.findAll{it.reqOrder >= newOrder}.each{requirement ->
        requirement.reqOrder++;
      }
      requirementChanged.reqOrder = newOrder;
    }else{
      project.requirementList.findAll{it.reqOrder <= newOrder}.each{requirement ->
        requirement.reqOrder--;
      }
      requirementChanged.reqOrder = newOrder;
    }
    this.reorderRequirementOrder(project);
    render status: OK
  }

  @Transactional
  def delete(Requirement requirement) {
    if (requirement == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    def project = requirement.project;
    this.increaseVersion(project);
    project.save flush:true;
    requirement.delete flush:true;
    this.reorderRequirementOrder(project);
    
    request.withFormat {
      form multipartForm {
        flash.message = "Requirement deleted successfully.";
        redirect(controller: "project", action: "show", id: project.id);
      }
      '*'{ render status: NO_CONTENT }
    }
  }
    
  def downloadPartialTestProject(Requirement requirement) {
    response.setContentType('APPLICATION/OCTET-STREAM');
    response.setHeader('Content-Disposition', 'Attachment;Filename="'+requirement.project.name+'.zip"');
    ZipOutputStream zip = new ZipOutputStream(response.outputStream);
    testProjectService.createPartialTestProject(requirement, zip);
    zip.flush();
    zip.close();
  }
  
  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = "Requirement not found.";
        redirect controller: "project", action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
  
  protected void reorderRequirementOrder(Project project){
    def order = 1;
    project.requirementList.sort{it.reqOrder}.each{requirement ->
      requirement.reqOrder = order;
      order++;
      requirement.save flush:true;
    }
  }
  
  protected void increaseVersion(Project project){
    def projectVersion = project.projectVersion.tokenize('.');
    project.projectVersion = ((projectVersion[0]).toInteger() + 1) + ".0.0"; 
  }
}
