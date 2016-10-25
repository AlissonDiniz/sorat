package br.org.venturus.sorat

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class SectionController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
  def show(Section section) {
    render (view: "show", model: [section: section, projectInstance: section.project]);
  }

  def create(Project projectInstance) {
    def section = new Section(params);
    section.project = projectInstance;
    render (view: "create", model: [section: section, projectInstance: section.project]);
  }

  @Transactional
  def save(Section section) {
    if (section == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (section.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "create", model: [section: section, projectInstance: section.project]);
      return
    }
    def project = section.project;
    this.increaseVersion(project);
    project.save flush:true;
    section.secOrder = project.sectionList.size() + 1;
    section.save flush:true;

    request.withFormat {
      form multipartForm {
        flash.message = "Section created successfully.";
        redirect section
      }
      '*' { respond section, [status: CREATED] }
    }
  }

  def edit(Section section) {
    render (view: "edit", model: [section: section, projectInstance: section.project]);
  }

  @Transactional
  def update(Section section) {
    if (section == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (section.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "edit", model: [section: section, projectInstance: section.project]);
      return
    }
    def project = section.project;
    this.increaseVersion(project);
    project.save flush:true;
        
    section.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Section updated successfully.";
        redirect section
      }
      '*'{ respond section, [status: OK] }
    }
  }

  @Transactional
  def changeOrder(Project project) {
    def sectionChanged = Section.get(params.section);
    def newOrder = params.order.toInteger() + 1;
    if(sectionChanged.secOrder > newOrder){
      project.sectionList.findAll{it.secOrder >= newOrder}.each{section ->
        section.secOrder++;
      }
      sectionChanged.secOrder = newOrder;
    }else{
      project.sectionList.findAll{it.secOrder <= newOrder}.each{section ->
        section.secOrder--;
      }
      sectionChanged.secOrder = newOrder;
    }
    this.reorderSectionOrder(project);
    render status: OK
  }
  
  @Transactional
  def delete(Section section) {
    if (section == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    def project = section.project;
    this.increaseVersion(project);
    project.save flush:true;
        
    section.delete flush:true;
    request.withFormat {
      form multipartForm {
        flash.message = "Section deleted successfully.";
        redirect(controller: "project", action: "show", id: project.id);
      }
      '*'{ render status: NO_CONTENT }
    }
  }
    
  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = "Section not found.";
        redirect controller: "project", action: "index", method: "GET"
      }
            '*'{ render status: NOT_FOUND }
    }
  }
    
  protected void reorderSectionOrder(Project project){
    def order = 1;
    project.sectionList.sort{it.secOrder}.each{section ->
      section.secOrder = order;
      order++;
      section.save flush:true;
    }
  }
    
  protected void increaseVersion(Project project){
    def projectVersion = project.projectVersion.tokenize('.');
    project.projectVersion = ((projectVersion[0]).toInteger() + 1) + ".0.0"; 
  }
}
