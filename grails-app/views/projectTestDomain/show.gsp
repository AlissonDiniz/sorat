<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Show Domain Test File</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Show Domain Test File
      </h3>
    </div>
    <div class="row row-actions">
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
        <li class="active">Show Domain Test File</li>
      </ol>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="showOptions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          <i class="glyphicon glyphicon-cog"></i>
          Options
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="showOptions">
          <li>
            <g:link action="edit" params="[id: projectTestDomain.id, p: projectInstance.id]">
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
      <g:hasErrors bean="${this.projectTestDomain}">
        <g:eachError bean="${this.projectTestDomain}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <div id="show-requirement" class="col-sm-12 content scaffold-show" role="main">
        <div class="row">
          <ol class="property-list requirement">
            <li>
              <span id="name-label" class="property-label">Name</span>
              <div class="property-value" aria-labelledby="name-label">${projectTestDomain.name}</div>
            </li>
            <li>
              <span id="path-label" class="property-label">Path</span>
              <div class="property-value" aria-labelledby="path-label">${projectTestDomain.path}</div>
            </li>
            <li>
              <span id="content-label" class="property-label">Content</span>
              <div class="property-value" aria-labelledby="content-label">
                <pre>${projectTestDomain.content}</pre>
              </div>
            </li>
            <li>
              <span id="userCreated-label" class="property-label">User Created</span>
              <div class="property-value" aria-labelledby="userCreated-label">${projectTestDomain.userCreated}</div>
            </li>
            <li>
              <span id="dateCreated-label" class="property-label">Date Created</span>
              <div class="property-value" aria-labelledby="dateCreated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${projectTestDomain.dateCreated}"/></div>
            </li>
            <li>
              <span id="userUpdated-label" class="property-label">User Updated</span>
              <div class="property-value" aria-labelledby="userUpdated-label">${projectTestDomain.userUpdated}</div>
            </li>
            <li>
              <span id="lastUpdated-label" class="property-label">Last Updated</span>
              <div class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${projectTestDomain.lastUpdated}"/></div>
            </li>
          </ol>
        </div>
      </div>
    </div>
  </body>
</html>
