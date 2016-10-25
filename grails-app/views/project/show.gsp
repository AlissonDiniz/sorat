<%@ defaultCodec="none" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Show Project</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Show Project
      </h3>
    </div>
    <div class="row row-actions">
      <ol class="breadcrumb">
        <li>
          <g:link action="index">
            Project List
          </g:link>
        </li>
        <li class="active">Show Project</li>
      </ol>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="showOptions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          <i class="glyphicon glyphicon-cog"></i>
          Options
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="showOptions">
          <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_MANAGER">
          <li>
            <g:link action="create">
              <i class="glyphicon glyphicon-plus-sign"></i>
              Create New Project
            </g:link>
          </li>
          <li>
            <g:link action="edit" resource="${this.project}">
              <i class="glyphicon glyphicon-pencil"></i>
              Edit
            </g:link>
          </li>
          </sec:ifAnyGranted>
          <li>
            <g:link action="docTemplate" resource="${this.project}">
              <i class="glyphicon glyphicon-list-alt"></i>
              Document Template
            </g:link>
          </li>
          <li>
            <g:link action="exportTemplate" resource="${this.project}">
              <i class="glyphicon glyphicon-list-alt"></i>
              Template to Export TestCases
            </g:link>
          </li>
          <li>
            <g:link action="testStructure" resource="${this.project}">
              <i class="glyphicon glyphicon-th"></i>
              Test Structure
            </g:link>
          </li>
          <li role="separator" class="divider"></li>
          <li class="dropdown-header">Actions</li>
          <li>
            <g:link action="report" resource="${this.project}" target="_blank">
              <i class="glyphicon glyphicon-file"></i>
              Requirements Doc
            </g:link>
          </li>
          <li>
            <g:link action="reportTestCases" resource="${this.project}" target="_blank">
              <i class="glyphicon glyphicon-file"></i>
              Test Cases Doc
            </g:link>
          </li>
          <li>
            <g:link action="downloadTestProject" resource="${this.project}" target="_blank">
              <i class="glyphicon glyphicon-download-alt"></i>
              Download Test Project
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
      <div id="show-project" data-id="${this.project.id}" class="col-sm-12 content scaffold-show" role="main">
        <div class="row">
          <ol class="property-list project">
            <li>
              <span id="name-label" class="property-label">Name</span>
              <div class="property-value" aria-labelledby="name-label">${this.project.name}</div>
            </li>
            <li>
              <span id="description-label" class="property-label">Description</span>
              <div class="property-value" aria-labelledby="description-label">${this.project.description}</div>
            </li>
            <li>
              <span id="projectVersion-label" class="property-label">Version</span>
              <div class="property-value" aria-labelledby="projectVersion-label">${this.project.projectVersion}</div>
            </li>
            <li>
              <span id="team-label" class="property-label">Team</span>
              <div class="property-value" aria-labelledby="team-label">
                <g:each var="project" in="${this.project.team}">
                  ${project.name}, 
                </g:each>
              </div>
            </li>
            <li>
              <span id="userCreated-label" class="property-label">User Created</span>
              <div class="property-value" aria-labelledby="userCreated-label">${this.project.userCreated}</div>
            </li>
            <li>
              <span id="dateCreated-label" class="property-label">Date Created</span>
              <div class="property-value" aria-labelledby="dateCreated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${this.project.dateCreated}"/></div>
            </li>
            <li>
              <span id="userUpdated-label" class="property-label">User Updated</span>
              <div class="property-value" aria-labelledby="userUpdated-label">${this.project.userUpdated}</div>
            </li>
            <li>
              <span id="lastUpdated-label" class="property-label">Last Updated</span>
              <div class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${this.project.lastUpdated}"/></div>
            </li>
          </ol>
          <div class="col-lg-12">
            <div class="row">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <i class="glyphicon glyphicon-th-list"></i> 
                  <strong>Section List</strong>
                  <g:link class="btn-action btn btn-primary" controller="section" action="create" id="${this.project.id}">
                    <i class="glyphicon glyphicon-plus-sign"></i>
                  </g:link>
                </div>
                <div class="panel-body">
                  <div id="section-sortable-container" class="list-group">
                    <g:each var="section" in="${this.project.sectionList.sort{it.secOrder}}">
                      <g:link class="ui-state-default list-group-item" data-id="${section.id}" action="show" resource="${section}">
                        <i class="glyphicon glyphicon-eye-open"></i>
                        &nbsp;&nbsp;${section.name}
                      </g:link>
                    </g:each>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="row">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <i class="glyphicon glyphicon-th-list"></i> 
                  <strong>Requirement List</strong>
                  <g:link class="btn-action btn btn-primary" controller="requirement" action="create" id="${this.project.id}">
                    <i class="glyphicon glyphicon-plus-sign"></i>
                  </g:link>
                </div>
                <div class="panel-body">
                  <div id="requirement-sortable-container" class="list-group">
                    <g:each var="requirement" in="${this.project.requirementList.sort{it.reqOrder}}">
                      <g:link class="ui-state-default list-group-item" data-id="${requirement.id}" action="show" resource="${requirement}">
                        <i class="glyphicon glyphicon-eye-open"></i>
                        &nbsp;&nbsp;${requirement.name}
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
