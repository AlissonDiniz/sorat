<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Create Action</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Create Action
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
        <li class="active">Create Action</li>
      </ol>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.requirementAction}">
        <g:eachError bean="${this.requirementAction}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <g:uploadForm action="save">
        <g:hiddenField name="requirement" value="${requirementInstance.id}" />
        <div class="row">
          <div class="col-sm-12 col-md-8 col-lg-6">
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="title">Title</label>
                  <g:field type="text" name="title" class="form-control" required="" value="${requirementAction?.title}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="type">Type</label>
                  <g:select name="type" class="form-control" required="" from="${br.org.venturus.domain.ActionType.values()}" value="${requirementAction?.type}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <g:hiddenField name="screens" value="${requirementAction.screens}" />
                <div id="screens-container" class="form-screens-container">
                  ${requirementAction.screens}
                </div>
                <div class="form-group">
                  <label for="addScreen">Add Image</label>
                  <g:field type="file" name="addScreen" class="form-control" />
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group">
              <label for="description">Description</label>
              <g:textArea name="description" class="form-control summernote" value="${requirementAction?.description}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" controller="requirement" action="show" id="${requirementInstance.id}">
              Cancel
            </g:link>
            <button type="submit" class="btn btn-primary">
              <i class="glyphicon glyphicon-floppy-disk"></i>
              Create
            </button>
          </div>
        </div>
      </g:uploadForm>
    </div>
    <asset:javascript src="domain/requirement-action.js"/>
  </body>
</html>
