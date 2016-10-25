package br.org.venturus.sorat

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class ProjectTestFileController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
  def show(ProjectTestFile projectTestFile) {
    render (view: "show", model: [projectTestFile: projectTestFile, projectInstance: projectTestFile.project]);
  }

  def create(Project projectInstance) {
    def projectTestFile = new ProjectTestFile(params);
    projectTestFile.project = projectInstance;
    render (view: "create", model: [projectTestFile: projectTestFile, projectInstance: projectTestFile.project]);
  }
  
  @Transactional
  def save(ProjectTestFile projectTestFile) {
    if (projectTestFile == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (projectTestFile.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "create", model: [projectTestFile: projectTestFile, projectInstance: projectTestFile.project]);
      return
    }
                
    projectTestFile.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Test File created successfully.";
        redirect projectTestFile
      }
      '*' { respond projectTestFile, [status: CREATED] }
    }
  }

  def edit(ProjectTestFile projectTestFile) {
    render (view: "edit", model: [projectTestFile: projectTestFile, projectInstance: projectTestFile.project]);
  }

  @Transactional
  def update(ProjectTestFile projectTestFile) {
    if (projectTestFile == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
        
    if (projectTestFile.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "edit", model: [projectTestFile: projectTestFile, projectInstance: projectTestFile.project]);
      return
    }
        
    projectTestFile.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Test File updated successfully.";
        redirect projectTestFile
      }
      '*'{ respond projectTestFile, [status: OK] }
    }
  }
  
  @Transactional
  def delete(ProjectTestFile projectTestFile) {
    def projectId = projectTestFile.project.id;
    if (projectTestFile == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
                
    projectTestFile.delete flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Test File deleted successfully.";
        redirect(controller: "project", action: "show", id: projectId);
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = "Test File not found.";
        redirect controller: "project", action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
  
}   