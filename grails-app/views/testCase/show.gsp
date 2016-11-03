<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Show Test Case</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Show Test Case
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
          <g:link controller="requirement" action="show" id="${requirementInstance.id}">
            ${requirementInstance.name}
          </g:link>
        </li>
        <li>
          <g:link controller="requirementAction" action="show" id="${requirementActionInstance.id}">
            ${requirementActionInstance.title}
          </g:link>
        </li>
        <li class="active">Show Test Case</li>
      </ol>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="showOptions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          <i class="glyphicon glyphicon-cog"></i>
          Options
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="showOptions">
          <li>
            <g:link action="edit" resource="${this.testCase}">
              <i class="glyphicon glyphicon-pencil"></i>
              Edit
            </g:link>
          </li>
          <li>
            <g:link action="copy" id="${testCase.id}">
              <i class="glyphicon glyphicon-duplicate"></i>
              Copy Test Case
            </g:link>
          </li>
          <li>
            <g:link action="create" id="${requirementActionInstance.id}">
              <i class="glyphicon glyphicon-plus-sign"></i>
              Create New Test Case
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
      <g:hasErrors bean="${this.testCase}">
        <g:eachError bean="${this.testCase}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <div id="show-testCase" class="col-sm-12 content scaffold-show" role="main">
        <div class="row">
          <ol class="property-list testCase">
            <li>
              <span id="title-label" class="property-label">Test Case Title</span>
              <div class="property-value" aria-labelledby="title-label">${testCase.title}</div>
            </li>
            <li>
              <span id="keyWord-label" class="property-label">Key Word</span>
              <div class="property-value" aria-labelledby="keyWord-label">${testCase.keyWord}</div>
            </li>
            <li>
              <span id="summary-label" class="property-label">Summary</span>
              <div class="property-value" aria-labelledby="summary-label">
                <pre>${testCase.summary}</pre>
              </div>
            </li>
            <li>
              <span id="preConditions-label" class="property-label">Pre-Conditions</span>
              <div class="property-value" aria-labelledby="preConditions-label">
                <pre>${testCase.preConditions}</pre>
              </div>
            </li>
            <li>
              <span id="steps-label" class="property-label">Steps</span>
              <div class="property-value" aria-labelledby="steps-label">
                <pre>${testCase.steps}</pre>
              </div>
            </li>
            <li>
              <span id="expectedResults-label" class="property-label">Expected Results</span>
              <div class="property-value" aria-labelledby="expectedResults-label">
                <pre>${testCase.expectedResults}</pre>
              </div>
            </li>
            <li>
              <span id="userCreated-label" class="property-label">User Created</span>
              <div class="property-value" aria-labelledby="userCreated-label">${testCase.userCreated}</div>
            </li>
            <li>
              <span id="dateCreated-label" class="property-label">Date Created</span>
              <div class="property-value" aria-labelledby="dateCreated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${testCase.dateCreated}"/></div>
            </li>
            <li>
              <span id="userUpdated-label" class="property-label">User Updated</span>
              <div class="property-value" aria-labelledby="userUpdated-label">${testCase.userUpdated}</div>
            </li>
            <li>
              <span id="lastUpdated-label" class="property-label">Last Updated</span>
              <div class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${testCase.lastUpdated}"/></div>
            </li>
          </ol>
        </div>
      </div>
    </div>
  </body>
</html>
