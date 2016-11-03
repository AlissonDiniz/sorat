<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Show Template Test</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Show Template Test
      </h3>
    </div>
    <div class="row row-actions">
      <ol class="breadcrumb">
        <li>
          <g:link controller="project" action="show" id="${projectInstance.id}">
            ${projectInstance.name}
          </g:link>
        </li>
        <li>
          <g:link controller="project" action="testStructure" id="${projectInstance.id}">
            Project Test Structure
          </g:link>
        </li>
        <li class="active">Show Template Test</li>
      </ol>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="showOptions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          <i class="glyphicon glyphicon-cog"></i>
          Options
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="showOptions">
          <li>
            <g:link action="edit" resource="${this.projectTestTemplate}">
              <i class="glyphicon glyphicon-pencil"></i>
              Edit
            </g:link>
          </li>
          <li>
            <g:link action="create" id="${projectInstance.id}">
              <i class="glyphicon glyphicon-plus-sign"></i>
              Create New Template Test
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
      <g:hasErrors bean="${this.projectTestTemplate}">
        <g:eachError bean="${this.projectTestTemplate}" var="error">
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
              <div class="property-value" aria-labelledby="name-label">${projectTestTemplate.name}</div>
            </li>
            <li>
              <span id="actionType-label" class="property-label">Action Type</span>
              <div class="property-value" aria-labelledby="actionType-label">${projectTestTemplate.actionType}</div>
            </li>
            <li>
              <span id="belongsField-label" class="property-label">Belongs to Field</span>
              <div class="property-value" aria-labelledby="belongsField-label">
                <g:formatBoolean boolean="${projectTestTemplate.belongsField}" />
              </div>
            </li>
            <g:if test="${projectTestTemplate.belongsField}">
              <li>
                <span id="ruleTest-label" class="property-label">Rule Test</span>
                <div class="property-value" aria-labelledby="ruleTest-label">${projectTestTemplate.ruleTest}</div>
              </li>
              <li>
                <span id="fieldType-label" class="property-label">Field Type</span>
                <div class="property-value" aria-labelledby="fieldType-label">${projectTestTemplate.fieldType}</div>
              </li>
            </g:if>
            <li>
              <span id="content-label" class="property-label">Content</span>
              <div class="property-value" aria-labelledby="content-label">
                <pre>${projectTestTemplate.content}</pre>
              </div>
            </li>
            <li>
              <span id="userCreated-label" class="property-label">User Created</span>
              <div class="property-value" aria-labelledby="userCreated-label">${projectTestTemplate.userCreated}</div>
            </li>
            <li>
              <span id="dateCreated-label" class="property-label">Date Created</span>
              <div class="property-value" aria-labelledby="dateCreated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${projectTestTemplate.dateCreated}"/></div>
            </li>
            <li>
              <span id="userUpdated-label" class="property-label">User Updated</span>
              <div class="property-value" aria-labelledby="userUpdated-label">${projectTestTemplate.userUpdated}</div>
            </li>
            <li>
              <span id="lastUpdated-label" class="property-label">Last Updated</span>
              <div class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${projectTestTemplate.lastUpdated}"/></div>
            </li>
          </ol>
        </div>
      </div>
    </div>
  </body>
</html>
