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
          <g:link controller="requirement" action="show" id="${requirementActionInstance.id}">
            Show Action
          </g:link>
        </li>
        <li>
          <g:link controller="actionModel" action="show" id="${actionModelInstance.id}">
            Show Model
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
            <g:link action="edit" resource="${this.actionModelField}">
              <i class="glyphicon glyphicon-pencil"></i>
              Edit
            </g:link>
          </li>
          <li>
            <g:link action="copy" id="${actionModelField.id}">
              <i class="glyphicon glyphicon-duplicate"></i>
              Copy Field
            </g:link>
          </li>
          <li>
            <g:link action="create" id="${actionModelInstance.id}">
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
      <g:hasErrors bean="${this.actionModelField}">
        <g:eachError bean="${this.actionModelField}" var="error">
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
              <div class="property-value" aria-labelledby="name-label">${actionModelField.name}</div>
            </li>
            <li>
              <span id="elementId-label" class="property-label">Element Id</span>
              <div class="property-value" aria-labelledby="elementId-label">${actionModelField.elementId}</div>
            </li>
            <li>
              <span id="type-label" class="property-label">Type</span>
              <div class="property-value" aria-labelledby="type-label">${actionModelField.type}</div>
            </li>
            <g:if test="${actionModelField.type.hasLimit}">
              <li>
                <span id="fieldLimitMin-label" class="property-label">Field Limit - Min</span>
                <div class="property-value" aria-labelledby="fieldLimitMin-label">${actionModelField.fieldLimitMin}</div>
              </li>
              <li>
                <span id="fieldLimitMax-label" class="property-label">Field Limit - Max</span>
                <div class="property-value" aria-labelledby="fieldLimitMax-label">${actionModelField.fieldLimitMax}</div>
              </li>
            </g:if>
            <g:if test="${actionModelField.type.equals(FieldType.NUMERIC_FLOAT)}">
              <li>
                <span id="fieldDecimal-label" class="property-label">Quantity Decimal</span>
                <div class="property-value" aria-labelledby="fieldDecimal-label">${actionModelField.fieldDecimal}</div>
              </li>
            </g:if>
            <li>
              <span id="required-label" class="property-label">Field is Required</span>
              <div class="property-value" aria-labelledby="required-label">
                <g:formatBoolean boolean="${actionModelField.required}" />
              </div>
            </li>
            <li>
              <span id="fieldUnique-label" class="property-label">Field is Unique</span>
              <div class="property-value" aria-labelledby="fieldUnique-label">
                <g:formatBoolean boolean="${actionModelField.fieldUnique}" />
              </div>
            </li>
            <li>
              <span id="description-label" class="property-label">Description</span>
              <div class="property-value" aria-labelledby="description-label">${actionModelField.description}</div>
            </li>
            <li>
              <span id="userCreated-label" class="property-label">User Created</span>
              <div class="property-value" aria-labelledby="userCreated-label">${actionModelField.userCreated}</div>
            </li>
            <li>
              <span id="dateCreated-label" class="property-label">Date Created</span>
              <div class="property-value" aria-labelledby="dateCreated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${actionModelField.dateCreated}"/></div>
            </li>
            <li>
              <span id="userUpdated-label" class="property-label">User Updated</span>
              <div class="property-value" aria-labelledby="userUpdated-label">${actionModelField.userUpdated}</div>
            </li>
            <li>
              <span id="lastUpdated-label" class="property-label">Last Updated</span>
              <div class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${actionModelField.lastUpdated}"/></div>
            </li>
          </ol>
        </div>
      </div>
    </div>
  </body>
</html>
