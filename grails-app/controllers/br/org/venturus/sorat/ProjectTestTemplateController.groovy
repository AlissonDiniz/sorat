package br.org.venturus.sorat

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class ProjectTestTemplateController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
  def show(ProjectTestTemplate projectTestTemplate) {
    render (view: "show", model: [projectTestTemplate: projectTestTemplate, projectInstance: projectTestTemplate.project]);
  }

  def create(Project projectInstance) {
    def projectTestTemplate = new ProjectTestTemplate(params);
    projectTestTemplate.project = projectInstance;
    render (view: "create", model: [projectTestTemplate: projectTestTemplate, projectInstance: projectTestTemplate.project]);
  }
  
  @Transactional
  def save(ProjectTestTemplate projectTestTemplate) {
    if (projectTestTemplate == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (projectTestTemplate.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "create", model: [projectTestTemplate: projectTestTemplate, projectInstance: projectTestTemplate.project]);
      return
    }
                
    projectTestTemplate.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Test Template created successfully.";
        redirect projectTestTemplate
      }
      '*' { respond projectTestTemplate, [status: CREATED] }
    }
  }

  def edit(ProjectTestTemplate projectTestTemplate) {
    render (view: "edit", model: [projectTestTemplate: projectTestTemplate, projectInstance: projectTestTemplate.project]);
  }

  @Transactional
  def update(ProjectTestTemplate projectTestTemplate) {
    if (projectTestTemplate == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
        
    if (projectTestTemplate.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "edit", model: [projectTestTemplate: projectTestTemplate, projectInstance: projectTestTemplate.project]);
      return
    }
        
    projectTestTemplate.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Test Template updated successfully.";
        redirect projectTestTemplate
      }
      '*'{ respond projectTestTemplate, [status: OK] }
    }
  }
  
  @Transactional
  def delete(ProjectTestTemplate projectTestTemplate) {
    def projectId = projectTestTemplate.project.id;
    if (projectTestTemplate == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
                
    projectTestTemplate.delete flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Test Template deleted successfully.";
        redirect(controller: "project", action: "show", id: projectId);
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = "Test Template not found.";
        redirect controller: "project", action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
  
}   