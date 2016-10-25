package br.org.venturus.sorat

import static org.springframework.http.HttpStatus.*;
import br.org.venturus.helper.ClonerHelper;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class ActionModelFieldController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def show(ActionModelField actionModelField) {
    render (view: "show", model: [actionModelField: actionModelField, actionModelInstance: actionModelField.actionModel, requirementActionInstance: actionModelField.actionModel.requirementAction, requirementInstance: actionModelField.actionModel.requirementAction.requirement, projectInstance: actionModelField.actionModel.requirementAction.requirement.project]);
  }

  def copy(ActionModelField sourceInstance) {
    def actionModelField = ClonerHelper.cloneActionModelField(sourceInstance);
    render (view: "create", model: [actionModelField: actionModelField, actionModelInstance: actionModelField.actionModel, requirementActionInstance: actionModelField.actionModel.requirementAction, requirementInstance: actionModelField.actionModel.requirementAction.requirement, projectInstance: actionModelField.actionModel.requirementAction.requirement.project]);
  }
  
  def create(ActionModel actionModelInstance) {
    def actionModelField = new ActionModelField(params);
    actionModelField.actionModel = actionModelInstance;
    render (view: "create", model: [actionModelField: actionModelField, actionModelInstance: actionModelInstance, requirementActionInstance: actionModelInstance.requirementAction, requirementInstance: actionModelInstance.requirementAction.requirement, projectInstance: actionModelInstance.requirementAction.requirement.project]);
  }

  @Transactional
  def save(ActionModelField actionModelField) {
    if (actionModelField == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (actionModelField.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "create", model: [actionModelField: actionModelField, actionModelInstance: actionModelField.actionModel, requirementActionInstance: actionModelField.actionModel.requirementAction, requirementInstance: actionModelField.actionModel.requirementAction.requirement, projectInstance: actionModelField.actionModel.requirementAction.requirement.project]);
      return
    }
                
    def project = actionModelField.actionModel.requirementAction.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    actionModelField.fieldOrder = actionModelField.actionModel.fieldList.size() + 1;
    actionModelField.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Field created successfully.";
        redirect actionModelField
      }
      '*' { respond actionModelField, [status: CREATED] }
    }
  }

  def edit(ActionModelField actionModelField) {
    render (view: "edit", model: [actionModelField: actionModelField, actionModelInstance: actionModelField.actionModel, requirementActionInstance: actionModelField.actionModel.requirementAction, requirementInstance: actionModelField.actionModel.requirementAction.requirement, projectInstance: actionModelField.actionModel.requirementAction.requirement.project]);
  }

  @Transactional
  def update(ActionModelField actionModelField) {
    if (actionModelField == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (actionModelField.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "edit", model: [actionModelField: actionModelField, actionModelInstance: actionModelField.actionModel, requirementActionInstance: actionModelField.actionModel.requirementAction, requirementInstance: actionModelField.actionModel.requirementAction.requirement, projectInstance: actionModelField.actionModel.requirementAction.requirement.project]);
      return
    }
        
    def project = actionModelField.actionModel.requirementAction.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    actionModelField.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Field updated successfully.";
        redirect actionModelField
      }
      '*'{ respond actionModelField, [status: OK] }
    }
  }
  
  @Transactional
  def changeOrder(ActionModel actionModel) {
    def fieldChanged = ActionModelField.get(params.field);
    def newOrder = params.order.toInteger() + 1;
    if(fieldChanged.fieldOrder > newOrder){
      actionModel.fieldList.findAll{it.fieldOrder >= newOrder}.each{testCase ->
        testCase.fieldOrder++;
      }
      fieldChanged.fieldOrder = newOrder;
    }else{
      actionModel.fieldList.findAll{it.fieldOrder <= newOrder}.each{testCase ->
        testCase.fieldOrder--;
      }
      fieldChanged.fieldOrder = newOrder;
    }
    this.reorderFieldOrder(actionModel);
    render status: OK
  }

  @Transactional
  def delete(ActionModelField actionModelField) {
    def actionModelId = actionModelField.actionModel.id;
    if (actionModelField == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
                        
    def project = actionModelField.actionModel.requirementAction.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    actionModelField.delete flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Field deleted successfully.";
        redirect(controller: "actionModel", action: "show", id: actionModelId);
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
  
  protected void reorderFieldOrder(ActionModel actionModel){
    def order = 1;
    actionModel.fieldList.sort{it.fieldOrder}.each{field ->
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
