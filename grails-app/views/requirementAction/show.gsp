<%@ defaultCodec="none" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Show Action</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Show Action
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
          <g:link controller="requirement" action="show" id="${requirementInstance.id}">
            Show Requirement
          </g:link>
        </li>
        <li class="active">Show Action</li>
      </ol>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="showOptions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          <i class="glyphicon glyphicon-cog"></i>
          Options
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="showOptions">
          <li>
            <g:link action="edit" resource="${this.requirementAction}">
              <i class="glyphicon glyphicon-pencil"></i>
              Edit
            </g:link>
          </li>
          <li>
            <g:link action="copy" id="${requirementAction.id}">
              <i class="glyphicon glyphicon-duplicate"></i>
              Copy Action
            </g:link>
          </li>
          <li>
            <g:link action="create" id="${requirementInstance.id}">
              <i class="glyphicon glyphicon-plus-sign"></i>
              Create New Action
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
      <g:hasErrors bean="${this.requirementAction}">
        <g:eachError bean="${this.requirementAction}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <div id="show-requirementAction" data-id="${this.requirementAction.id}" class="col-sm-12 content scaffold-show" role="main">
        <div class="row">
          <ol class="property-list requirementAction">
            <li>
              <span id="title-label" class="property-label">Action Title</span>
              <div class="property-value" aria-labelledby="title-label">${requirementAction.title}</div>
            </li>
            <li>
              <span id="type-label" class="property-label">Type</span>
              <div class="property-value" aria-labelledby="type-label">${requirementAction.type}</div>
            </li>
            <li>
              <span id="screens-label" class="property-label">Screens</span>
              <div class="property-value" aria-labelledby="screens-label">${requirementAction.screens}</div>
            </li>
            <li>
              <span id="description-label" class="property-label">Description</span>
              <div class="property-value" aria-labelledby="description-label">${requirementAction.description}</div>
            </li>
            <li>
              <span id="domain-label" class="property-label">Model</span>
              <div class="property-value" aria-labelledby="actionModel-label">
                <g:if test="${requirementAction.actionModel != null}">
                  <g:link controller="actionModel" action="show" id="${requirementAction.actionModel.id}">
                    <i class="glyphicon glyphicon-eye-open"></i>
                    ${requirementAction.actionModel.name}
                  </g:link>
                </g:if>
                <g:else>
                  <g:link class="btn btn-primary" controller="actionModel" action="create" id="${requirementAction.id}">
                    <i class="glyphicon glyphicon-plus-sign"></i>
                    Create Model
                  </g:link>
                </g:else>
              </div>
            </li>
            <li>
              <span id="userCreated-label" class="property-label">User Created</span>
              <div class="property-value" aria-labelledby="userCreated-label">${requirementAction.userCreated}</div>
            </li>
            <li>
              <span id="dateCreated-label" class="property-label">Date Created</span>
              <div class="property-value" aria-labelledby="dateCreated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${requirementAction.dateCreated}"/></div>
            </li>
            <li>
              <span id="userUpdated-label" class="property-label">User Updated</span>
              <div class="property-value" aria-labelledby="userUpdated-label">${requirementAction.userUpdated}</div>
            </li>
            <li>
              <span id="lastUpdated-label" class="property-label">Last Updated</span>
              <div class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${requirementAction.lastUpdated}"/></div>
            </li>
          </ol>
          <div class="col-lg-12">
            <div class="row">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <i class="glyphicon glyphicon-th-list"></i> 
                  <strong>Test Case List</strong>

                  <div class="dropdown panel-dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="testCasesActions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                      <i class="glyphicon glyphicon-cog"></i>
                      Actions
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="testCasesActions">
                      <li>
                        <g:link action="exportTestCases" resource="${this.requirementAction}" method="POST" target="_blank">
                          <i class="glyphicon glyphicon-download-alt"></i>
                          Export Test Cases
                        </g:link>
                      </li>
                      <li>
                        <g:link action="downloadTestLinkCSV" resource="${this.requirementAction}" method="POST" target="_blank">
                          <i class="glyphicon glyphicon-download-alt"></i>
                          Download Test Link CSV
                        </g:link>
                      </li>
                    </ul>
                  </div>
                  <g:link class="btn-action btn btn-primary" controller="testCase" action="create" id="${requirementAction.id}">
                    <i class="glyphicon glyphicon-plus-sign"></i>
                  </g:link>
                </div>
                <div class="panel-body">
                  <div id="testcase-sortable-container" class="list-group">
                    <g:each var="testCase" in="${requirementAction.testCaseList.sort{it.testOrder}}">
                      <g:link class="ui-state-default list-group-item" data-id="${testCase.id}" action="show" resource="${testCase}">
                        <i class="glyphicon glyphicon-eye-open"></i>
                        &nbsp;&nbsp;${testCase.title}
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
