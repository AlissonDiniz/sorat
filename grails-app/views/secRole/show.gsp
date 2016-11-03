<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Show Role</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Show Role
      </h3>
    </div>
    <div class="row row-actions">
      <ol class="breadcrumb">
        <li>
          <g:link action="index">
            Role List
          </g:link>
        </li>
        <li class="active">Show Role</li>
      </ol>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="showOptions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          <i class="glyphicon glyphicon-cog"></i>
          Options
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="showOptions">
          <li>
            <g:link action="edit" resource="${this.secUser}">
              <i class="glyphicon glyphicon-pencil"></i>
              Edit
            </g:link>
          </li>
          <li>
            <g:link action="create">
              <i class="glyphicon glyphicon-plus-sign"></i>
              Create New Role
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
      <g:hasErrors bean="${this.secRole}">
        <g:eachError bean="${this.secRole}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <div id="show-secRole" data-id="${this.secRole.id}" class="col-sm-12 content scaffold-show" role="main">
        <div class="row">
          <ol class="property-list secRole">
            <li>
              <span id="authority-label" class="property-label">Authority</span>
              <div class="property-value" aria-labelledby="authority-label">${secRole.authority}</div>
            </li>
            <li>
              <span id="description-label" class="property-label">Description</span>
              <div class="property-value" aria-labelledby="description-label">${secRole.description}</div>
            </li>
          </ol>
        </div>
      </div>
    </div>
  </body>
</html>
