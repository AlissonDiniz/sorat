<%@ defaultCodec="none" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Project Test Structure</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Project Test Structure
      </h3>
    </div>
    <div class="row">
      <ol class="breadcrumb">
        <li>
          <g:link action="index">
            Project List
          </g:link>
        </li>
        <li>
          <g:link action="show" resource="${this.project}">
            Show Project
          </g:link>
        </li>
        <li class="active">Project Test Structure</li>
      </ol>
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
              <div class="property-value" aria-labelledby="name-label">${project.name}</div>
            </li>
            <li>
              <span id="domian-label" class="property-label">Domain Class</span>
              <div class="property-value" aria-labelledby="domain-label">
                <g:if test="${project.domainClass != null}">
                  <g:link controller="projectTestDomain" action="show" params="[id: project.domainClass.id, p: project.id]">
                    <i class="glyphicon glyphicon-eye-open"></i>
                    Domain Class Template
                  </g:link>
                </g:if>
                <g:else>
                  <g:link class="btn btn-primary" controller="projectTestDomain" action="create" id="${project.id}">
                    <i class="glyphicon glyphicon-plus-sign"></i>
                    Create Domain Class Template
                  </g:link>
                </g:else>
              </div>
            </li>
            <li>
              <span id="domian-label" class="property-label">Domain Test Class</span>
              <div class="property-value" aria-labelledby="domain-label">
                <g:if test="${project.domainTestClass != null}">
                  <g:link controller="projectTestDomain" action="show" params="[id: project.domainTestClass.id, p: project.id]">
                    <i class="glyphicon glyphicon-eye-open"></i>
                    Domain Test Class Template
                  </g:link>
                </g:if>
                <g:else>
                  <g:link class="btn btn-primary" controller="projectTestDomain" action="create" params="[id: project.id, isTest: true]">
                    <i class="glyphicon glyphicon-plus-sign"></i>
                    Create Domain Test Class Template
                  </g:link>
                </g:else>
              </div>
            </li>
          </ol>
          <div class="col-lg-12">
            <div class="row">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <i class="glyphicon glyphicon-th-list"></i> 
                  <strong>Structure File List</strong>
                  <g:link class="btn-action btn btn-primary" controller="projectTestFile" action="create" id="${project.id}">
                    <i class="glyphicon glyphicon-plus-sign"></i>
                  </g:link>
                </div>
                <div class="panel-body">
                  <div id="projectTestFile-sortable-container" class="list-group">
                    <g:each var="projectTestFile" in="${project.testFileList.sort{it.name}}">
                      <g:link class="ui-state-default list-group-item" data-id="${projectTestFile.id}" action="show" resource="${projectTestFile}">
                        <i class="glyphicon glyphicon-eye-open"></i>
                        &nbsp;&nbsp;${projectTestFile.name} - ${projectTestFile.path}
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
                  <strong>Template Test</strong>
                  <g:link class="btn-action btn btn-primary" controller="projectTestTemplate" action="create" id="${project.id}">
                    <i class="glyphicon glyphicon-plus-sign"></i>
                  </g:link>
                </div>
                <div class="panel-body">
                  <div id="requirement-sortable-container" class="list-group">
                    <g:each var="projectTestTemplate" in="${project.templateTestList.sort{it.name}}">
                      <g:link class="ui-state-default list-group-item" data-id="${projectTestTemplate.id}" action="show" resource="${projectTestTemplate}">
                        <i class="glyphicon glyphicon-eye-open"></i>
                        &nbsp;&nbsp;${projectTestTemplate.name} - ${projectTestTemplate.ruleTest}
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
