package br.org.venturus.sorat

import java.nio.file.Path;
import java.nio.file.Paths;
import static org.springframework.http.HttpStatus.*;
import org.springframework.security.access.annotation.Secured;
import br.org.venturus.helper.ProjectHelper;
import br.org.venturus.sorat.security.SecUser;
import grails.transaction.Transactional;
import grails.converters.JSON;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.grails.io.support.ResourceLoader;
import br.org.venturus.domain.BreakLine;

@Transactional(readOnly = true)
class ProjectController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
  def testProjectService;
  def reportService;
  def springSecurityService;
  ResourceLoader resourceLoader;

  def index(Integer max) {
    respond ProjectHelper.listMyProjects(springSecurityService.getCurrentUser());
  }

  def show(Project projectInstance) {
    def project = ProjectHelper.validateMember(projectInstance, springSecurityService.getCurrentUser());
    if (project == null) {
      notFound()
      return
    }
    respond project;
  }
  
  def testStructure(Project projectInstance) {
    def project = ProjectHelper.validateMember(projectInstance, springSecurityService.getCurrentUser());
    if (project == null) {
      notFound()
      return
    }
    render (view: "test_structure", model: [project: project]);
  }
    
  def report(Project projectInstance) {
    def project = ProjectHelper.validateMember(projectInstance, springSecurityService.getCurrentUser());
    if (project == null) {
      notFound()
      return
    }
    String documentContent = "";
    if(projectV.documentTemplate){
      documentContent = reportService.compile(project);
    }
    render (view: "report", model: [project: project, documentContent: documentContent]);
  }
  
  def reportTestCases(Project projectInstance) {
    def project = ProjectHelper.validateMember(projectInstance, springSecurityService.getCurrentUser());
    if (project == null) {
      notFound()
      return
    }
    render (view: "report_testcases", model: [project: project]);
  }
    
  def downloadTestProject(Project projectInstance) {
    def project = ProjectHelper.validateMember(projectInstance, springSecurityService.getCurrentUser());
    if (project == null) {
      notFound()
      return
    }
    response.setContentType('APPLICATION/OCTET-STREAM');
    response.setHeader('Content-Disposition', 'Attachment;Filename="'+project.name+'.zip"');
    Path currentRelativePath = Paths.get("");
    ZipOutputStream zip = new ZipOutputStream(response.outputStream);
    testProjectService.createTestProject(project, zip);
    zip.flush();
    zip.close();
  }
  
  @Secured(["ROLE_ADMIN", "ROLE_MANAGER"])
  def create() {
    respond new Project(params)
  }
  
  def docTemplate(Project projectInstance) {
    def project = ProjectHelper.validateMember(projectInstance, springSecurityService.getCurrentUser());
    if (project == null) {
      notFound()
      return
    }
    render (view: "show_doc_template", model: [project: project]);
  }
  
  def editDocTemplate(Project projectInstance) {
    def project = ProjectHelper.validateMember(projectInstance, springSecurityService.getCurrentUser());
    if (project == null) {
      notFound()
      return
    }
    render (view: "edit_doc_template", model: [project: project]);
  }
  
  def exportTemplate(Project projectInstance) {
    def project = ProjectHelper.validateMember(projectInstance, springSecurityService.getCurrentUser());
    if (project == null) {
      notFound()
      return
    }
    
    def templateModel = null;
    if(project.exportTemplate){
      templateModel = JSON.parse(project.exportTemplate);
    }
    render (view: "show_export_template", model: [project: project, templateModel: templateModel]);
  }
  
  def editExportTemplate(Project projectInstance) {
    def project = ProjectHelper.validateMember(projectInstance, springSecurityService.getCurrentUser());
    if (project == null) {
      notFound()
      return
    }
    
    def templateModel = null;
    if(project.exportTemplate){
      templateModel = JSON.parse(project.exportTemplate);
      templateModel.breakLine = BreakLine.valueOf(templateModel.breakLine);
    }
    render (view: "edit_export_template", model: [project: project, templateModel: templateModel]);
  }

  @Transactional
  def saveDocTemplate(Project projectInstance) {
    def project = ProjectHelper.validateMember(projectInstance, springSecurityService.getCurrentUser());
    if (project == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    project.documentTemplate = params.documentTemplate;
    project.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Project Document Template updated successfully."
        redirect (action: "docTemplate", id: project.id)
      }
      '*'{ respond project, [status: OK] }
    }
  }
  
  @Transactional
  def saveExportTemplate(Project projectInstance) {
    def project = ProjectHelper.validateMember(projectInstance, springSecurityService.getCurrentUser());
    if (project == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    boolean escapeXml = (params.escapeXml == "on");
    project.exportTemplate = new JSON([extension: params.extension, breakLine: params.breakLine, escapeXml: escapeXml, exportTemplate: params.exportTemplate]).toString();
    project.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Project Template to Export TestCases updated successfully."
        redirect (action: "exportTemplate", id: project.id)
      }
      '*'{ respond project, [status: OK] }
    }
  }
  
  @Secured(["ROLE_ADMIN", "ROLE_MANAGER"])
  @Transactional
  def save(Project project) {
    if (project == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (project.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond project.errors, view:'create'
      return
    }
    project.team = project.team ? project.team : [];
    params.users.each{id ->
      def secUser = SecUser.get(id);
      if (!project.team.contains(secUser)) {
        project.team << secUser;
      }
    }
    
    project.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Project created successfully."
        redirect project
      }
      '*' { respond project, [status: CREATED] }
    }
  }

  @Secured(["ROLE_ADMIN", "ROLE_MANAGER"])
  def edit(Project project) {
    respond project
  }

  @Secured(["ROLE_ADMIN", "ROLE_MANAGER"])
  @Transactional
  def update(Project project) {
    if (project == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (project.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond project.errors, view:'edit'
      return
    }
    params.users.each{id ->
      def secUser = SecUser.get(id);
      if (!project.team.contains(secUser)) {
        project.team << secUser;
      }
    }
    project.team.each{secUser ->
      def userId = params.users.find{it.toInteger() == secUser.id};
      if(!userId){
        project.team.remove(secUser);
      }
    }
    
    this.increaseVersion(project);
    project.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Project updated successfully."
        redirect project
      }
      '*'{ respond project, [status: OK] }
    }
  }

  @Secured(["ROLE_ADMIN", "ROLE_MANAGER"])
  @Transactional
  def delete(Project project) {
    if (project == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    project.delete flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Project deleted successfully."
        redirect action:"index", method:"GET"
      }
            '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = "Project not found."
        redirect action: "index", method: "GET"
      }
            '*'{ render status: NOT_FOUND }
    }
  }
    
  protected void increaseVersion(Project project){
    def projectVersion = project.projectVersion.tokenize('.');
    project.projectVersion = ((projectVersion[0]).toInteger() + 1) + ".0.0"; 
  }
}
