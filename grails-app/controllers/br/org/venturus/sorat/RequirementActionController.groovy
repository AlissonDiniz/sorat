package br.org.venturus.sorat

import static org.springframework.http.HttpStatus.*;
import br.org.venturus.helper.ClonerHelper;
import grails.transaction.Transactional;
import grails.converters.JSON;
import br.org.venturus.domain.BreakLine;

@Transactional(readOnly = true)
class RequirementActionController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"];
  
  def exportService;

  def show(RequirementAction requirementAction) {
    render (view: "show", model: [requirementAction: requirementAction, requirementInstance: requirementAction.requirement, projectInstance: requirementAction.requirement.project]);
  }

  def copy(RequirementAction sourceInstance) {
    def requirementAction = ClonerHelper.cloneRequirementAction(sourceInstance);
    render (view: "create", model: [requirementAction: requirementAction, requirementInstance: requirementAction.requirement, projectInstance: requirementAction.requirement.project]);
  }
  
  def create(Requirement requirementInstance) {
    def requirementAction = new RequirementAction(params);
    requirementAction.requirement = requirementInstance;
    render (view: "create", model: [requirementAction: requirementAction, requirementInstance: requirementAction.requirement, projectInstance: requirementAction.requirement.project]);
  }

  @Transactional
  def save(RequirementAction requirementAction) {
    if (requirementAction == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (requirementAction.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "create", model: [requirementAction: requirementAction, requirementInstance: requirementAction.requirement, projectInstance: requirementAction.requirement.project]);
      return
    }
                
    def project = requirementAction.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
    requirementAction.actionOrder = requirementAction.requirement.actionList.size() + 1;
        
    requirementAction.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Action created successfully.";
        redirect requirementAction
      }
      '*' { respond requirementAction, [status: CREATED] }
    }
  }

  def edit(RequirementAction requirementAction) {
    render (view: "edit", model: [requirementAction: requirementAction, requirementInstance: requirementAction.requirement, projectInstance: requirementAction.requirement.project]);
  }

  @Transactional
  def update(RequirementAction requirementAction) {
    if (requirementAction == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (requirementAction.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "edit", model: [requirementAction: requirementAction, requirementInstance: requirementAction.requirement, projectInstance: requirementAction.requirement.project]);
      return
    }
        
    def project = requirementAction.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    requirementAction.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Action updated successfully.";
        redirect requirementAction
      }
      '*'{ respond requirementAction, [status: OK] }
    }
  }
  
  @Transactional
  def changeOrder(Requirement requirement) {
    def actionChanged = RequirementAction.get(params.requirementAction);
    def newOrder = params.order.toInteger() + 1;
    if(actionChanged.actionOrder > newOrder){
      requirement.actionList.findAll{it.actionOrder >= newOrder}.each{action ->
        action.actionOrder++;
      }
      actionChanged.actionOrder = newOrder;
    }else{
      requirement.actionList.findAll{it.actionOrder <= newOrder}.each{action ->
        action.actionOrder--;
      }
      actionChanged.actionOrder = newOrder;
    }
    this.reorderActionOrder(requirement);
    render status: OK
  }

  @Transactional
  def delete(RequirementAction requirementAction) {
    def requirementId = requirementAction.requirement.id;
    if (requirementAction == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
                
    def project = requirementAction.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    requirementAction.delete flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Action deleted successfully.";
        redirect(controller: "requirement", action: "show", id: requirementId);
      }
      '*'{ render status: NO_CONTENT }
    }
  }
  
  def exportTestCases(RequirementAction requirementAction) {
    def project = requirementAction.requirement.project;
    def templateModel = null;
    if(project.exportTemplate){
      templateModel = JSON.parse(project.exportTemplate);
      templateModel.breakLine = BreakLine.valueOf(templateModel.breakLine);
    }
    def content = exportService.compile(templateModel, requirementAction);
    response.setHeader "Content-disposition", "attachment; filename=${requirementAction.title}-TestCases" + templateModel?.extension;
    response.contentType = 'application/octet-stream;charset=UTF-8';
    response.outputStream << new String(content.getBytes('UTF-8'));
    response.outputStream.flush();
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = "Action not found.";
        redirect controller: "project", action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
  
  protected void reorderActionOrder(Requirement requirement){
    def order = 1;
    requirement.actionList.sort{it.actionOrder}.each{action ->
      action.actionOrder = order;
      order++;
      action.save flush:true;
    }
  }
    
  protected void increaseVersion(Project project){
    def projectVersion = project.projectVersion.tokenize('.');
    project.projectVersion = projectVersion[0] + "." + ((projectVersion[1]).toInteger() + 1) + ".0"; 
  }
}
