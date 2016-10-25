<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Create Project</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Create Project
      </h3>
    </div>
    <div class="row">
      <ol class="breadcrumb">
        <li>
          <g:link action="index">
            Project List
          </g:link>
        </li>
        <li class="active">Create Project</li>
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
      <g:form action="save">
        <div class="row">
          <div class="col-sm-12 col-md-8 col-lg-6">
            <div class="form-group required">
              <label for="name">Name</label>
              <g:field type="text" name="name" class="form-control" required="" value="${project?.name}"/>
            </div>
          </div>
          <div class="col-lg-12">
            <br />
            <div class="form-group multiselect-wrapper required">
              <label for="team">Team</label>
              <g:select name="users" class="form-control multiple" from="${br.org.venturus.sorat.security.SecUser.list()}" required="" value="" optionKey="id" multiple="true" />
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group">
              <label for="description">Description</label>
              <g:textArea name="description" class="form-control summernote" value="${project?.description}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" action="index">
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
