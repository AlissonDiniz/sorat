<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Edit Test Case</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Edit Test Case
      </h3>
    </div>
    <div class="row">
      <ol class="breadcrumb">
        <li>
          <g:link controller="project" action="show" id="${projectInstance.id}">
            ${projectInstance.name}
          </g:link>
        </li>
        <li>
          <g:link controller="requirement" action="show" id="${requirementInstance.id}">
            ${requirementInstance.name}
          </g:link>
        </li>
        <li>
          <g:link controller="requirementAction" action="show" id="${requirementActionInstance.id}">
            ${requirementActionInstance.title}
          </g:link>
        </li>
        <li>
          <g:link controller="testCase" action="show" id="${this.testCase.id}">
            ${this.testCase.title}
          </g:link>
        </li>
        <li class="active">Edit Test Case</li>
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
      <g:form resource="${this.testCase}" method="PUT">
        <g:hiddenField name="version" value="${this.testCase?.version}" />
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
            <div class="form-group required">
              <label for="requirementAction">Action</label>
              <g:select name="requirementAction" class="form-control" required="" from="${requirementInstance.actionList}" optionKey="id" optionValue="title" value="${testCase.requirementAction?.id}"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" controller="testCase" action="show" id="${testCase.id}">
              Cancel
            </g:link>
            <button type="button" class="btn btn-danger btn-delete" />
              <i class="glyphicon glyphicon-trash"></i>
              Delete
            </button>
            <button type="submit" class="btn btn-primary">
              <i class="glyphicon glyphicon-floppy-disk"></i>
              Update
            </button>
          </div>
        </div>
      </g:form>
      <g:form name="formDelete" class="pull-left" resource="${this.testCase}" method="DELETE"></g:form>
    </div>
  </body>
</html>
