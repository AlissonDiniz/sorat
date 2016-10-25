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
        <g:link class="btn btn-primary pull-right" action="create">
          <i class="glyphicon glyphicon-plus-sign"></i>
          Create New Role
        </g:link>
      </h3>
    </div>
    <div class="row">
      <ol class="breadcrumb">
        <li>
          <g:link action="index">
            Role List
          </g:link>
        </li>
        <li class="active">Show Role</li>
      </ol>
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
          <div class="col-lg-12">
            <div class="row">
              <g:form class="pull-left" resource="${this.secRole}" method="DELETE">
                <g:link class="btn btn-primary" action="edit" resource="${this.secRole}">
                  <i class="glyphicon glyphicon-pencil"></i>
                  Edit
                </g:link>
                <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?');" />
                  <i class="glyphicon glyphicon-trash"></i>
                  Delete
                </button>
              </g:form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
