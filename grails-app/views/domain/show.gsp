<%@ defaultCodec="none" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Show Domain</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Show Domain
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
          <g:link controller="requirement" action="show" id="${domain.requirement.id}">
            Show Requirement
          </g:link>
        </li>
        <li class="active">Show Domain</li>
      </ol>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="showOptions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          <i class="glyphicon glyphicon-cog"></i>
          Options
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="showOptions">
          <li>
            <g:link action="edit" resource="${this.domain}">
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
      <g:hasErrors bean="${this.domain}">
        <g:eachError bean="${this.domain}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <div id="show-domain" data-id="${this.domain.id}" class="col-sm-12 content scaffold-show" role="main">
        <div class="row">
          <ol class="property-list project">
            <li>
              <span id="name-label" class="property-label">Name</span>
              <div class="property-value" aria-labelledby="name-label">${domain.name}</div>
            </li>
            <li>
              <span id="elementId-label" class="property-label">Menu Element Id</span>
              <div class="property-value" aria-labelledby="elementId-label">${domain.elementId}</div>
            </li>
            <li>
              <span id="path-label" class="property-label">Url Path</span>
              <div class="property-value" aria-labelledby="path-label">${domain.path}</div>
            </li>
            <li>
              <span id="description-label" class="property-label">Description</span>
              <div class="property-value" aria-labelledby="description-label">${domain.description}</div>
            </li>
            <li>
              <span id="userCreated-label" class="property-label">User Created</span>
              <div class="property-value" aria-labelledby="userCreated-label">${domain.userCreated}</div>
            </li>
            <li>
              <span id="dateCreated-label" class="property-label">Date Created</span>
              <div class="property-value" aria-labelledby="dateCreated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${domain.dateCreated}"/></div>
            </li>
            <li>
              <span id="userUpdated-label" class="property-label">User Updated</span>
              <div class="property-value" aria-labelledby="userUpdated-label">${domain.userUpdated}</div>
            </li>
            <li>
              <span id="lastUpdated-label" class="property-label">Last Updated</span>
              <div class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${domain.lastUpdated}"/></div>
            </li>
          </ol>
          <div class="col-lg-12">
            <div class="row">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <i class="glyphicon glyphicon-th-list"></i> 
                  <strong>Field List</strong>
                  <g:link class="btn-action btn btn-primary" controller="domainField" action="create" id="${domain.id}">
                    <i class="glyphicon glyphicon-plus-sign"></i>
                  </g:link>
                </div>
                <div class="panel-body">
                  <div id="domainField-sortable-container" class="list-group">
                    <g:each var="field" in="${domain.fieldList.sort{it.fieldOrder}}">
                      <g:link class="ui-state-default list-group-item" data-id="${field.id}" action="show" resource="${field}">
                        <i class="glyphicon glyphicon-eye-open"></i>
                        &nbsp;&nbsp;${field.name}
                      </g:link>
                    </g:each>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
