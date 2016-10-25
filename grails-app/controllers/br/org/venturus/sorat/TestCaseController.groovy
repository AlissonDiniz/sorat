package br.org.venturus.sorat

import static org.springframework.http.HttpStatus.*;
import br.org.venturus.helper.ClonerHelper;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class TestCaseController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
  def show(TestCase testCase) {
    render (view: "show", model: [testCase: testCase, requirementInstance: testCase.requirementAction.requirement, requirementActionInstance: testCase.requirementAction, projectInstance: testCase.requirementAction.requirement.project]);
  }

  def create(RequirementAction requirementActionInstance) {
    def testCase = new TestCase(params);
    testCase.requirementAction = requirementActionInstance;
    render (view: "create", model: [testCase: testCase, requirementInstance: testCase.requirementAction.requirement, requirementActionInstance: testCase.requirementAction, projectInstance: testCase.requirementAction.requirement.project]);
  }
  
  def copy(TestCase sourceInstance) {
    def testCase = ClonerHelper.cloneTestCase(sourceInstance);
    render (view: "create", model: [testCase: testCase, requirementInstance: testCase.requirementAction.requirement, requirementActionInstance: testCase.requirementAction, projectInstance: testCase.requirementAction.requirement.project]);
  }

  @Transactional
  def save(TestCase testCase) {
    if (testCase == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    if (testCase.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "create", model: [testCase: testCase, requirementInstance: testCase.requirementAction.requirement, requirementActionInstance: testCase.requirementAction, projectInstance: testCase.requirementAction.requirement.project]);
      return
    }
                
    def project = testCase.requirementAction.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    testCase.testOrder = testCase.requirementAction.testCaseList.size() + 1;
    testCase.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Test Case created successfully.";
        redirect testCase
      }
      '*' { respond testCase, [status: CREATED] }
    }
  }

  def edit(TestCase testCase) {
    render (view: "edit", model: [testCase: testCase, requirementInstance: testCase.requirementAction.requirement, requirementActionInstance: testCase.requirementAction, projectInstance: testCase.requirementAction.requirement.project]);
  }

  @Transactional
  def update(TestCase testCase) {
    if (testCase == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
        
    if (testCase.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render (view: "edit", model: [testCase: testCase, requirementInstance: testCase.requirementAction.requirement, requirementActionInstance: testCase.requirementAction, projectInstance: testCase.requirementAction.requirement.project]);
      return
    }
        
    def project = testCase.requirementAction.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    testCase.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Test Case updated successfully.";
        redirect testCase
      }
      '*'{ respond testCase, [status: OK] }
    }
  }
  
  @Transactional
  def changeOrder(RequirementAction requirementAction) {
    def testCaseChanged = TestCase.get(params.testCase);
    def newOrder = params.order.toInteger() + 1;
    if(testCaseChanged.testOrder > newOrder){
      requirementAction.testCaseList.findAll{it.testOrder >= newOrder}.each{testCase ->
        testCase.testOrder++;
      }
      testCaseChanged.testOrder = newOrder;
    }else{
      requirementAction.testCaseList.findAll{it.testOrder <= newOrder}.each{testCase ->
        testCase.testOrder--;
      }
      testCaseChanged.testOrder = newOrder;
    }
    this.reorderTestCaseOrder(requirementAction);
    render status: OK
  }

  @Transactional
  def delete(TestCase testCase) {
    def requirementActionId = testCase.requirementAction.id;
    if (testCase == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
                
    def project = testCase.requirementAction.requirement.project;
    this.increaseVersion(project); 
    project.save flush:true;
        
    testCase.delete flush:true
    request.withFormat {
      form multipartForm {
        flash.message = "Test Case deleted successfully.";
        redirect(controller: "requirementAction", action: "show", id: requirementActionId);
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = "Test Case not found.";
        redirect controller: "project", action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
  
  protected void reorderTestCaseOrder(RequirementAction requirementAction){
    def order = 1;
    requirementAction.testCaseList.sort{it.testOrder}.each{testCase ->
      testCase.testOrder = order;
      order++;
      testCase.save flush:true;
    }
  }
    
  protected void increaseVersion(Project project){
    def projectVersion = project.projectVersion.tokenize('.');
    project.projectVersion = projectVersion[0] + "." + projectVersion[1] + "." + ((projectVersion[2]).toInteger() + 1); 
  }   
}   