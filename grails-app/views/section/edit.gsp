<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Edit Section</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Edit Section
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
          <g:link action="show" resource="${this.section}">
            ${this.section.name}
          </g:link>
        </li>
        <li class="active">Edit Section</li>
      </ol>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.section}">
        <g:eachError bean="${this.section}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <g:form resource="${this.section}" method="PUT">
        <g:hiddenField name="version" value="${this.section?.version}" />
        <div class="row">
          <div class="col-sm-12 col-md-8 col-lg-6">
            <div class="form-group required">
              <label for="name">Name</label>
              <g:field type="text" name="name" class="form-control" required="" value="${section?.name}"/>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group">
              <label for="description">Description</label>
              <g:textArea name="description" class="form-control summernote" value="${section?.description}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" action="show" resource="${this.section}">
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
      <g:form name="formDelete" class="pull-left" resource="${this.section}" method="DELETE"></g:form>
    </div>
  </body>
</html>
