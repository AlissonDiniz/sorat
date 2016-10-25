<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Create Structure File</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Create Structure File
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
          <g:link controller="project" action="testStructure" id="${projectInstance.id}">
            Project Test Structure
          </g:link>
        </li>
        <li class="active">Create Structure File</li>
      </ol>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.projectTestFile}">
        <g:eachError bean="${this.projectTestFile}" var="error">
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
                  <g:field type="text" name="name" class="form-control" required="" value="${projectTestFile?.name}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="path">Path</label>
                  <g:field type="text" name="path" class="form-control" required="" value="${projectTestFile?.path}"/>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group required">
              <label for="content">Content</label>
              <g:textArea name="content" class="form-control textarea-large" required="" value="${projectTestFile?.content}" rows="5" cols="40"/>
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
  </body>
</html>
