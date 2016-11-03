<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Project Document Template</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Project Document Template
      </h3>
    </div>
    <div class="row row-actions">
      <ol class="breadcrumb">
        <li>
          <g:link action="index">
            Project List
          </g:link>
        </li>
        <li>
          <g:link action="show" resource="${this.project}">
            ${this.project.name}
          </g:link>
        </li>
        <li class="active">Project Document Template</li>
      </ol>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="showOptions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          <i class="glyphicon glyphicon-cog"></i>
          Options
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="showOptions">
          <li>
            <g:link action="editDocTemplate" resource="${this.project}">
              <i class="glyphicon glyphicon-pencil"></i>
              Edit
            </g:link>
          </li>
        </ul>
      </div>
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
      <div id="show-docTemplate" class="col-sm-12 content scaffold-show" role="main">
        <div class="row">
          <ol class="property-list docTemplate">
            <li>
              <span id="name-label" class="property-label">Name</span>
              <div class="property-value" aria-labelledby="name-label">${this.project.name}</div>
            </li>
            <li>
              <span id="documentTemplate-label" class="property-label">Document Template</span>
              <div class="property-value" aria-labelledby="documentTemplate-label">
                <pre>${this.project.documentTemplate}</pre>
              </div>
            </li>
          </ol>
        </div>
      </div>
    </div>
  </body>
</html>
