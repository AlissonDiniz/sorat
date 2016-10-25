<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Create Model</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Create Model
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
        <li class="active">Create Model</li>
      </ol>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.actionModel}">
        <g:eachError bean="${this.actionModel}" var="error">
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
                  <label for="name">Name</label>
                  <g:field type="text" name="name" class="form-control" required="" value="${actionModel?.name}"/>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group">
              <label for="description">Description</label>
              <g:textArea name="description" class="form-control summernote" value="${actionModel?.description}" rows="5" cols="40"/>
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
