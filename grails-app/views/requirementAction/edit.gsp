<%@ defaultCodec="none" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Edit Action</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Edit Action
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
          <g:link controller="requirementAction" action="show" id="${this.requirementAction.id}">
            ${this.requirementAction.title}
          </g:link>
        </li>
        <li class="active">Edit Action</li>
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
      <g:form resource="${this.requirementAction}" method="PUT">
        <g:hiddenField name="version" value="${this.requirementAction.version}" />
        <div class="row">
          <div class="col-sm-12 col-md-8 col-lg-6">
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="title">Title</label>
                  <g:field type="text" name="title" class="form-control" required="" value="${requirementAction.title}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="type">Type</label>
                  <g:select name="type" class="form-control" required="" from="${br.org.venturus.domain.ActionType.values()}" value="${requirementAction.type}"/>
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
              <g:textArea name="description" class="form-control summernote" value="${requirementAction.description}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" controller="requirementAction" action="show" id="${requirementAction.id}">
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
      <g:form name="formDelete" class="pull-left" resource="${this.requirementAction}" method="DELETE"></g:form>
    </div>
  <asset:javascript src="domain/requirement-action.js"/>
  </body>
</html>
