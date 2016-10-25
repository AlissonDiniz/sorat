<%@ defaultCodec="none" %>
<%@ page import="br.org.venturus.domain.FieldType" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Show Field</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Show Field
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
        <li>
          <g:link controller="domain" action="show" id="${domainInstance.id}">
            Show Domain
          </g:link>
        </li>
        <li class="active">Show Field</li>
      </ol>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="showOptions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          <i class="glyphicon glyphicon-cog"></i>
          Options
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="showOptions">
          <li>
            <g:link action="edit" resource="${this.domainField}">
              <i class="glyphicon glyphicon-pencil"></i>
              Edit
            </g:link>
          </li>
          <li>
            <g:link action="copy" id="${domainField.id}">
              <i class="glyphicon glyphicon-duplicate"></i>
              Copy Field
            </g:link>
          </li>
          <li>
            <g:link action="create" id="${domainInstance.id}">
              <i class="glyphicon glyphicon-plus-sign"></i>
              Create New Field
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
      <g:hasErrors bean="${this.domainField}">
        <g:eachError bean="${this.domainField}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <div id="show-project" class="col-sm-12 col-md-8 content scaffold-show" role="main">
        <div class="row">
          <ol class="property-list project">
            <li>
              <span id="name-label" class="property-label">Name</span>
              <div class="property-value" aria-labelledby="name-label">${domainField.name}</div>
            </li>
            <li>
              <span id="elementId-label" class="property-label">Element Id</span>
              <div class="property-value" aria-labelledby="elementId-label">${domainField.elementId}</div>
            </li>
            <li>
              <span id="type-label" class="property-label">Type</span>
              <div class="property-value" aria-labelledby="type-label">${domainField.type}</div>
            </li>
            <g:if test="${domainField.type.hasLimit}">
              <li>
                <span id="fieldLimitMin-label" class="property-label">Field Limit - Min</span>
                <div class="property-value" aria-labelledby="fieldLimitMin-label">${domainField.fieldLimitMin}</div>
              </li>
              <li>
                <span id="fieldLimitMax-label" class="property-label">Field Limit - Max</span>
                <div class="property-value" aria-labelledby="fieldLimitMax-label">${domainField.fieldLimitMax}</div>
              </li>
            </g:if>
            <g:if test="${domainField.type.equals(FieldType.NUMERIC_FLOAT)}">
              <li>
                <span id="fieldDecimal-label" class="property-label">Quantity Decimal</span>
                <div class="property-value" aria-labelledby="fieldDecimal-label">${domainField.fieldDecimal}</div>
              </li>
            </g:if>
            <li>
              <span id="required-label" class="property-label">Field is Required</span>
              <div class="property-value" aria-labelledby="required-label">
                <g:formatBoolean boolean="${domainField.required}" />
              </div>
            </li>
            <li>
              <span id="fieldUnique-label" class="property-label">Field is Unique</span>
              <div class="property-value" aria-labelledby="fieldUnique-label">
                <g:formatBoolean boolean="${domainField.fieldUnique}" />
              </div>
            </li>
            <li>
              <span id="visibleInTable-label" class="property-label">Field is visible in the Table</span>
              <div class="property-value" aria-labelledby="visibleInTable-label">
                <g:formatBoolean boolean="${domainField.visibleInTable}" />
              </div>
            </li>
            <li>
              <span id="searchable-label" class="property-label">Field is Searchable</span>
              <div class="property-value" aria-labelledby="searchable-label">
                <g:formatBoolean boolean="${domainField.searchable}" />
              </div>
            </li>
            <li>
              <span id="joinedField-label" class="property-label">Joined Field</span>
              <div class="property-value" aria-labelledby="joinedField-label">
                <g:formatBoolean boolean="${domainField.joinedField}" />
              </div>
            </li>
            <li>
              <span id="sortable-label" class="property-label">Field is Sortable</span>
              <div class="property-value" aria-labelledby="sortable-label">
                <g:formatBoolean boolean="${domainField.sortable}" />
              </div>
            </li>
            <g:if test="${domainField.visibleInTable}">
              <li>
                <span id="orderInTable-label" class="property-label">Order In Table</span>
                <div class="property-value" aria-labelledby="orderInTable-label">${domainField.orderInTable}</div>
              </li>
            </g:if>
            <li>
              <span id="description-label" class="property-label">Description</span>
              <div class="property-value" aria-labelledby="description-label">${domainField.description}</div>
            </li>
            <li>
              <span id="userCreated-label" class="property-label">User Created</span>
              <div class="property-value" aria-labelledby="userCreated-label">${domainField.userCreated}</div>
            </li>
            <li>
              <span id="dateCreated-label" class="property-label">Date Created</span>
              <div class="property-value" aria-labelledby="dateCreated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${domainField.dateCreated}"/></div>
            </li>
            <li>
              <span id="userUpdated-label" class="property-label">User Updated</span>
              <div class="property-value" aria-labelledby="userUpdated-label">${domainField.userUpdated}</div>
            </li>
            <li>
              <span id="lastUpdated-label" class="property-label">Last Updated</span>
              <div class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${domainField.lastUpdated}"/></div>
            </li>
          </ol>
        </div>
      </div>
    </div>
  </body>
</html>
