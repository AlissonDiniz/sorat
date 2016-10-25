package br.org.venturus.sorat

import static org.springframework.http.HttpStatus.*;
import br.org.venturus.generator.GeneratorHelper;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class ActionModelController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def show(ActionModel actionModel) {
    render (view: "show", model: [actionModel: actionModel, requirementActionInstance: actionModel.requirementAction, requirementInstance: actionModel.requirementAction.requirement, projectInstance: actionModel.requirementAction.requirement.project]);
  }

  def create(RequirementAction requirementActionInstance) {
    def actionModel = new ActionModel(params);
    actionModel.requirementAction = requirementActionInstance;
    render (view: "create", model: [actionModel: actionModel, requirementActionInstance: requirementActionInstance, requirementInstance: requirementActionInstance.requirement, projectInstance: requirementActionInstance.requirement.project]);
  }

  @Transactional
  def save(ActionModel actionModel) {
    if (actionModel == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (actionModel.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "create", model: [actionModel: actionModel, requirementActionInstance: actionModel.requirementAction, requirementInstance: actionModel.requirementAction.requirement, projectInstance: actionModel.requirementAction.requirement.project]);
      return
    }
        
    def project = actionModel.requirementAction.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    actionModel.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Model created successfully.";
        redirect actionModel
      }
            '*' { respond actionModel, [status: CREATED] }
    }
  }

  def edit(ActionModel actionModel) {
    render (view: "edit", model: [actionModel: actionModel, requirementActionInstance: actionModel.requirementAction, requirementInstance: actionModel.requirementAction.requirement, projectInstance: actionModel.requirementAction.requirement.project]);
  }

  @Transactional
  def update(ActionModel actionModel) {
    if (actionModel == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (actionModel.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "edit", model: [actionModel: actionModel, requirementActionInstance: actionModel.requirementAction, requirementInstance: actionModel.requirementAction.requirement, projectInstance: actionModel.requirementAction.requirement.project]);
      return
    }
        
    def project = actionModel.requirementAction.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    actionModel.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Model updated successfully.";
        redirect actionModel
      }
            '*'{ respond actionModel, [status: OK] }
    }
  }

  @Transactional
  def delete(ActionModel actionModel) {
    if (actionModel == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    def requirementAction = actionModel.requirementAction;
    def project = requirementAction.requirement.project;
    requirementAction.actionModel = null;
    this.increaseVersion(project);
    requirementAction.save flush:true;
    project.save flush:true;
        
    actionModel.delete flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Model deleted successfully.";
        redirect(controller: "requirementAction", action: "show", id: requirementAction.id);
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = "Model not found.";
        redirect controller: "project", action: "index", method: "GET"
      }
            '*'{ render status: NOT_FOUND }
    }
  }
    
  protected void increaseVersion(Project project){
    def projectVersion = project.projectVersion.tokenize('.');
    project.projectVersion = projectVersion[0] + "." + projectVersion[1] + "." + ((projectVersion[2]).toInteger() + 1); 
  }
}

