<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Project Document Template</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Project Document Template
      </h3>
    </div>
    <div class="row">
      <ol class="breadcrumb">
        <li>
          <g:link action="index">
            Project List
          </g:link>
        </li>
        <li>
          <g:link action="show" resource="${this.project}">
            Show Project
          </g:link>
        </li>
        <li class="active">Project Document Template</li>
      </ol>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.project}">
        <g:eachError bean="${this.project}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <g:form action="saveDocTemplate" resource="${this.project}" method="POST">
        <div class="row">
          <div class="col-lg-12">
            <div class="form-group required">
              <label for="name">Name</label>
              <br />
              <span>${this.project.name}</span>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="well">
              <h4>Available Tags:</h4>
              <p>
                <span>Replace to Project Name: <strong>{project.name}</strong></span>
                <br />
                <span>Replace to Project Description: <strong>{project.description}</strong></span>
                <br />
                <span>Replace to Project Version: <strong>{project.projectVersion}</strong></span>
                <br />
              </p>
              <p>
                <span>Sections Block: <strong>#sections ... #/sections</strong></span>
                <br />
                <span>Replace to Section Iterator Index: <strong>{section.index}</strong></span>
                <br />
                <span>Replace to Section Name: <strong>{section.name}</strong></span>
                <br />
                <span>Replace to Section Description: <strong>{section.description}</strong></span>
              </p>
              <p>
                <span>Requirements Block: <strong>#requirements ... #/requirements</strong></span>
                <br />
                <span>Replace to Requirement Iterator Index: <strong>{requirement.index}</strong></span>
                <br />
                <span>Replace to Requirement Name: <strong>{requirement.name}</strong></span>
                <br />
                <span>Replace to Requirement Description: <strong>{requirement.description}</strong></span>
              </p>
              <p>
                <span>Domain Block (inside in requirements block and replace if exist): <strong>#domain ... #/domain</strong></span>
                <br />
                <span>Replace to Domain Name: <strong>{domain.name}</strong></span>
              </p>
              <p>
                <span>Domain Fields Block (inside in domain block): <strong>#domainfields ... #/domainfields</strong></span>
                <br />
                <span>Replace to Field Name: <strong>{field.name}</strong></span>
              </p>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group required">
              <label for="documentTemplate">Document Template</label>
              <g:textArea name="documentTemplate" class="form-control textarea-large" required="" value="${this.project.documentTemplate}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" action="docTemplate" resource="${this.project}">
              Cancel
            </g:link>
            <button type="submit" class="btn btn-primary">
              <i class="glyphicon glyphicon-floppy-disk"></i>
              Update
            </button>
          </div>
        </div>
      </g:form>
    </div>
  </body>
</html>
