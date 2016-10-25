<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Edit Domain Test File</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Edit Domain Test File
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
        <li class="active">Edit Domain Test File</li>
      </ol>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.projectTestDomain}">
        <g:eachError bean="${this.projectTestDomain}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <g:form resource="${this.projectTestDomain}" method="PUT">
        <g:hiddenField name="version" value="${this.projectTestDomain?.version}" />
        <g:hiddenField name="project" value="${projectInstance.id}" />
        <div class="row">
          <div class="col-sm-12 col-md-8 col-lg-6">
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="nameView">Name</label>
                  <g:field type="text" name="nameView" class="form-control" value="${projectTestDomain?.name}" disabled="disabled" />
                  <g:hiddenField name="name" value="${projectTestDomain?.name}" />
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="path">Path</label>
                  <g:field type="text" name="path" class="form-control" value="${projectTestDomain?.path}" />
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="well">
              <h4>Available Tags:</h4>
              <p>
                <span>Replace to Package Name: <strong>#PACKAGE_NAME</strong></span>
                <br />
                <span>Replace to Domain Name: <strong>#DOMAIN_NAME</strong></span>
                <br />
                <span>Replace to Default Views Enum Content to Domain Class: <strong>#DEFAULT_VIEWS</strong></span>
                <br />
                <span>Replace to Map Attributes Enum Content to Domain Class: <strong>#DOMAIN_MAP</strong></span>
                <br />
                <span>Replace to Tests Auto Generated to Test Class: <strong>#TESTS_GENERATED</strong></span>
              </p>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group required">
              <label for="content">Content</label>
              <g:textArea name="content" class="form-control textarea-large" required="" value="${projectTestDomain?.content}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" controller="projectTestDomain" action="show" id="${projectTestDomain.id}">
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
      <g:form name="formDelete" class="pull-left" resource="${this.projectTestDomain}" method="DELETE"></g:form>
      </div>
    </body>
  </html>
