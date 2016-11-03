<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Create Template Test</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Create Template Test
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
          <g:link controller="project" action="testStructure" id="${projectInstance.id}">
            Project Test Structure
          </g:link>
        </li>
        <li class="active">Create Template Test</li>
      </ol>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.projectTestTemplate}">
        <g:eachError bean="${this.projectTestTemplate}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <g:form action="save">
        <g:hiddenField name="project" value="${projectInstance.id}" />
        <div class="row">
          <div class="col-sm-12 col-md-8 col-lg-6">
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="name">Name</label>
                  <g:field type="text" name="name" class="form-control" required="" value="${projectTestTemplate?.name}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="actionType">Action Type</label>
                  <g:select name="actionType" class="form-control" required="" from="${br.org.venturus.domain.TestType.values()}" value="${projectTestTemplate?.actionType}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="checkbox">
                  <label for="belongsField">
                    <g:checkBox name="belongsField" value="${projectTestTemplate?.belongsField}" />
                    Belongs to Field
                  </label>
                </div>
              </div>
              <div id="rule-type-container" class="col-lg-12 belongs-field hide">
                <div class="form-group required">
                  <label for="ruleTest">Rule Test</label>
                  <g:select name="ruleTest" class="form-control" required="" from="${br.org.venturus.domain.RuleTest.values()}" value="${projectTestTemplate?.ruleTest}"/>
                </div>
              </div>
              <div id="field-type-container" class="col-lg-12 belongs-field hide">
                <div class="form-group required">
                  <label for="fieldType">Field Type</label>
                  <g:select name="fieldType" class="form-control" required="" from="${br.org.venturus.domain.FieldType.values()}" value="${projectTestTemplate?.fieldType}"/>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="well">
              <h4>Available Tags:</h4>
              <p>
                <span>Replace to Domain Name: <strong>#DOMAIN_NAME</strong></span>
                <br />
                <span>Replace to Domain Menu: <strong>#DOMAIN_MENU</strong></span>
                <br />
                <span>Replace to Field Name: <strong>#FIELD_NAME</strong></span>
              </p>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group required">
              <label for="content">Content</label>
              <g:textArea name="content" class="form-control textarea-medium" required="" value="${projectTestTemplate?.content}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" controller="project" action="testStructure" id="${projectInstance.id}">
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
  <asset:javascript src="domain/project-test-template.js"/>
</body>
</html>
