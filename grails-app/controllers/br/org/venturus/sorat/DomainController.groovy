package br.org.venturus.sorat

import static org.springframework.http.HttpStatus.*;
import br.org.venturus.generator.GeneratorHelper;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class DomainController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(Domain domain) {
        render (view: "show", model: [domain: domain, requirementInstance: domain.requirement, projectInstance: domain.requirement.project]);
    }

    def create(Requirement requirementInstance) {
        def domain = new Domain(params);
        domain.requirement = requirementInstance;
        render (view: "create", model: [domain: domain, requirementInstance: domain.requirement, projectInstance: domain.requirement.project]);
    }

    @Transactional
    def save(Domain domain) {
        if (domain == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        if (domain.hasErrors()) {
            transactionStatus.setRollbackOnly()
            render (view: "create", model: [domain: domain, requirementInstance: domain.requirement, projectInstance: domain.requirement.project]);
            return
        }
        domain.menu = "Menu.Main." + GeneratorHelper.getDomainEnum(domain.name);
        
        def project = domain.requirement.project;
        this.increaseVersion(project); 
        project.save flush:true;
        
        domain.save flush:true
        request.withFormat {
            form multipartForm {
                flash.message = "Domain created successfully.";
                redirect domain
            }
            '*' { respond domain, [status: CREATED] }
        }
    }

    def edit(Domain domain) {
        render (view: "edit", model: [domain: domain, requirementInstance: domain.requirement, projectInstance: domain.requirement.project]);
    }

    @Transactional
    def update(Domain domain) {
        if (domain == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        if (domain.hasErrors()) {
            transactionStatus.setRollbackOnly()
            render (view: "edit", model: [domain: domain, requirementInstance: domain.requirement, projectInstance: domain.requirement.project]);
            return
        }
        domain.menu = "Menu.Main." + GeneratorHelper.getDomainEnum(domain.name);
        
        def project = domain.requirement.project;
        this.increaseVersion(project); 
        project.save flush:true;
        
        domain.save flush:true
        request.withFormat {
            form multipartForm {
                flash.message = "Domain updated successfully.";
                redirect domain
            }
            '*'{ respond domain, [status: OK] }
        }
    }

    @Transactional
    def delete(Domain domain) {
        if (domain == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        
        def requirement = domain.requirement;
        def project = domain.requirement.project;
        requirement.domain = null;
        this.increaseVersion(project);
        requirement.save flush:true;
        project.save flush:true;
        
        domain.delete flush:true
        request.withFormat {
            form multipartForm {
                flash.message = "Domain deleted successfully.";
                redirect(controller: "requirement", action: "show", id: requirement.id);
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = "Domain not found.";
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
