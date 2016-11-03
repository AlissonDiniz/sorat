<%@ defaultCodec="none" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Show Requirement</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Show Requirement
      </h3>
    </div>
    <div class="row row-actions">
      <ol class="breadcrumb">
        <li>
          <g:link controller="project" action="show" id="${projectInstance.id}">
            ${projectInstance.name}
          </g:link>
        </li>
        <li class="active">Show Requirement</li>
      </ol>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="projectActions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          <i class="glyphicon glyphicon-cog"></i>
          Options
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="projectActions">
          <li>
            <g:link action="create" id="${projectInstance.id}">
              <i class="glyphicon glyphicon-plus-sign"></i>
              Create New Requirement
            </g:link>
          </li>
          <li>
            <g:link action="copy" id="${requirement.id}">
              <i class="glyphicon glyphicon-copy"></i>
              Copy Requirement
            </g:link>
          </li>
          <li>
            <g:link action="edit" resource="${this.requirement}">
              <i class="glyphicon glyphicon-pencil"></i>
              Edit
            </g:link>
          </li>
          <g:if test="${requirement.domain}">
          <li>
            <g:link action="downloadPartialTestProject" resource="${this.requirement}" method="POST" target="_blank">
              <i class="glyphicon glyphicon-download-alt"></i>
              Download Partial Test Project
            </g:link>
          </li>
          </g:if>
        </ul>
      </div>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.requirement}">
        <g:eachError bean="${this.requirement}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <div id="show-requirement" data-id="${this.requirement.id}" class="col-sm-12 content scaffold-show" role="main">
        <div class="row">
          <ol class="property-list requirement">
            <li>
              <span id="name-label" class="property-label">Name</span>
              <div class="property-value" aria-labelledby="name-label">${requirement.name}</div>
            </li>
            <li>
              <span id="description-label" class="property-label">Description</span>
              <div class="property-value" aria-labelledby="description-label">${requirement.description}</div>
            </li>
            <li>
              <span id="domian-label" class="property-label">Domain</span>
              <div class="property-value" aria-labelledby="domain-label">
                <g:if test="${requirement.domain != null}">
                  <g:link controller="domain" action="show" id="${requirement.domain.id}">
                    <i class="glyphicon glyphicon-eye-open"></i>
                    ${requirement.domain.name}
                  </g:link>
                </g:if>
                <g:else>
                  <g:link class="btn btn-primary" controller="domain" action="create" id="${requirement.id}">
                    <i class="glyphicon glyphicon-plus-sign"></i>
                    Create Domain
                  </g:link>
                </g:else>
              </div>
            </li>
            <li>
              <span id="userCreated-label" class="property-label">User Created</span>
              <div class="property-value" aria-labelledby="userCreated-label">${requirement.userCreated}</div>
            </li>
            <li>
              <span id="dateCreated-label" class="property-label">Date Created</span>
              <div class="property-value" aria-labelledby="dateCreated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${requirement.dateCreated}"/></div>
            </li>
            <li>
              <span id="userUpdated-label" class="property-label">User Updated</span>
              <div class="property-value" aria-labelledby="userUpdated-label">${requirement.userUpdated}</div>
            </li>
            <li>
              <span id="lastUpdated-label" class="property-label">Last Updated</span>
              <div class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${requirement.lastUpdated}"/></div>
            </li>
          </ol>
          <div class="col-lg-12">
            <div class="row">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <i class="glyphicon glyphicon-th-list"></i> 
                  <strong>Action List</strong>
                  <g:link class="btn-action btn btn-primary" controller="requirementAction" action="create" id="${requirement.id}">
                    <i class="glyphicon glyphicon-plus-sign"></i>
                  </g:link>
                </div>
                <div class="panel-body">
                  <div id="action-sortable-container" class="list-group">
                    <g:each var="requirementAction" in="${requirement.actionList.sort{it.actionOrder}}">
                      <g:link class="ui-state-default list-group-item" data-id="${requirementAction.id}" action="show" resource="${requirementAction}">
                        <i class="glyphicon glyphicon-eye-open"></i>
                        &nbsp;&nbsp;${requirementAction.title}
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
