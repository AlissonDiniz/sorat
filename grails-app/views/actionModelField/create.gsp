<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Create Field</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Create Field
      </h3>
    </div>
    <div class="row">
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
        <li>
          <g:link controller="domain" action="show" id="${actionModelInstance.id}">
            Show Model
          </g:link>
        </li>
        <li class="active">Create Field</li>
      </ol>
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
      <g:form action="save">
        <g:hiddenField name="actionModel" value="${actionModelInstance.id}" />
        <div class="row">
          <div class="col-sm-12 col-md-8 col-lg-6">
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="name">Name</label>
                  <g:field type="text" name="name" class="form-control" required="" value="${actionModelField?.name}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="elementId">Element Id</label>
                  <g:field type="text" name="elementId" class="form-control" required="" value="${actionModelField?.elementId}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="type">Type</label>
                  <g:select name="type" class="form-control" required="" from="${br.org.venturus.domain.FieldType.values()}" value="${actionModelField?.type}"/>
                </div>
              </div>
              <div id="field-limit-container" class="col-lg-12 hide">
                <div class="row">
                  <div class="col-lg-6">
                    <div class="form-group required">
                      <label for="fieldLimitMin">Field Limit - Min</label>
                      <g:field type="number" name="fieldLimitMin" class="form-control" required="" maxlength="4" value="${actionModelField?.fieldLimitMin}"/>
                    </div>
                  </div>
                  <div class="col-lg-6">
                    <div class="form-group required">
                      <label for="fieldLimitMax">Field Limit - Max</label>
                      <g:field type="number" name="fieldLimitMax" class="form-control" required="" maxlength="4" value="${actionModelField?.fieldLimitMax}"/>
                    </div>
                  </div>
                </div>
              </div>
              <div id="quantity-decimal-container" class="col-lg-12 hide">
                <div class="form-group">
                  <label for="fieldDecimal">Quantity Decimal</label>
                  <g:field type="number" name="fieldDecimal" class="form-control" maxlength="10" value="${actionModelField?.fieldDecimal}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="required" value="${actionModelField?.required}" /> Field is Required
                  </label>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="fieldUnique" value="${actionModelField?.fieldUnique}" /> Field is Unique
                  </label>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group">
              <label for="description">Description</label>
              <g:textArea name="description" class="form-control summernote" value="${actionModelField?.description}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" controller="actionModel" action="show" id="${actionModelInstance.id}">
              Cancel
            </g:link>
            <button type="submit" class="btn btn-primary">
              <i class="glyphicon glyphicon-floppy-disk"></i>
              Create
            </button>
          </div>
        </div>
      </g:form>
    </div>
    <g:select name="templateType" class="hide" from="${br.org.venturus.domain.FieldType.values()}" optionValue="hasLimit" />
    <asset:javascript src="domain/action-model-field.js"/>
  </body>
</html>
