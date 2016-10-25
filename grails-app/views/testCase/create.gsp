<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Create Test Case</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Create Test Case
      </h3>
    </div>
    <div class="row">
      <ol class="breadcrumb">
        <li>
          <g:link controller="project" action="show" id="${projectInstance.id}">
            Show Project
          </g:link>
        </li>
        <li>
          <g:link controller="requirement" action="show" id="${requirementInstance.id}">
            Show Requirement
          </g:link>
        </li>
        <li>
          <g:link controller="requirementAction" action="show" id="${requirementActionInstance.id}">
            Show Action
          </g:link>
        </li>
        <li class="active">Create Test Case</li>
      </ol>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.testCase}">
        <g:eachError bean="${this.testCase}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <g:form action="save">
        <g:hiddenField name="requirementAction" value="${requirementActionInstance.id}" />
        <div class="row">
          <div class="col-sm-12 col-md-8 col-lg-6">
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="title">Title</label>
                  <g:field type="text" name="title" class="form-control" required="" value="${testCase?.title}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="keyWord">Key Word</label>
                  <g:select name="keyWord" class="form-control" required="" from="${br.org.venturus.domain.KeyWord.values()}" value="${testCase?.keyWord}"/>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group required">
              <label for="summary">Summary</label>
              <g:textArea name="summary" class="form-control textarea-medium" required="" value="${testCase?.summary}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group required">
              <label for="preConditions">Pre-Conditions</label>
              <g:textArea name="preConditions" class="form-control textarea-medium" required="" value="${testCase?.preConditions}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group required">
              <label for="steps">Steps</label>
              <g:textArea name="steps" class="form-control textarea-medium" required="" value="${testCase?.steps}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group required">
              <label for="expectedResults">Expected Results</label>
              <g:textArea name="expectedResults" class="form-control textarea-medium" required="" value="${testCase?.expectedResults}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" controller="requirementAction" action="show" id="${requirementActionInstance.id}">
              Cancel
            </g:link>
            <button type="submit" class="btn btn-primary">
              <i class="glyphicon glyphicon-floppy-disk"></i>
              Create
            </button>
          </div>
        </div>
      </g:form>
    </div>
  </body>
</html>
