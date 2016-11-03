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
          <g:link controller="domain" action="show" id="${domainInstance.id}">
            Show Domain
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
      <g:hasErrors bean="${this.domainField}">
        <g:eachError bean="${this.domainField}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <g:form action="save">
        <g:hiddenField name="domain" value="${domainInstance.id}" />
        <div class="row">
          <div class="col-sm-12 col-md-8 col-lg-6">
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="name">Name</label>
                  <g:field type="text" name="name" class="form-control" required="" value="${domainField?.name}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="elementId">Element Id</label>
                  <g:field type="text" name="elementId" class="form-control" required="" value="${domainField?.elementId}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="type">Type</label>
                  <g:select name="type" class="form-control" required="" from="${br.org.venturus.domain.FieldType.values()}" value="${domainField?.type}"/>
                </div>
              </div>
              <div id="field-limit-container" class="col-lg-12 hide">
                <div class="row">
                  <div class="col-lg-6">
                    <div class="form-group required">
                      <label for="fieldLimitMin">Field Limit - Min</label>
                      <g:field type="number" name="fieldLimitMin" class="form-control" required="" maxlength="4" value="${domainField?.fieldLimitMin}"/>
                    </div>
                  </div>
                  <div class="col-lg-6">
                    <div class="form-group required">
                      <label for="fieldLimitMax">Field Limit - Max</label>
                      <g:field type="number" name="fieldLimitMax" class="form-control" required="" maxlength="4" value="${domainField?.fieldLimitMax}"/>
                    </div>
                  </div>
                </div>
              </div>
              <div id="quantity-decimal-container" class="col-lg-12 hide">
                <div class="form-group">
                  <label for="fieldDecimal">Quantity Decimal</label>
                  <g:field type="number" name="fieldDecimal" class="form-control" maxlength="10" value="${domainField?.fieldDecimal}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="required" value="${domainField?.required}" /> Field is Required
                  </label>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="fieldUnique" value="${domainField?.fieldUnique}" /> Field is Unique
                  </label>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="visibleInTable" value="${domainField?.visibleInTable}" /> Field is visible in the Table
                  </label>
                </div>
              </div>
              <div class="col-lg-12 visibleInTable hide">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="searchable" value="${domainField?.searchable}" /> Field is Searchable
                  </label>
                </div>
              </div>
              <div class="col-lg-12 visibleInTable hide">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="filterable" value="${domainField?.filterable}" /> Field is Filterable
                  </label>
                </div>
              </div>
              <div class="col-lg-12 visibleInTable hide">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="joinedField" value="${domainField?.joinedField}" /> Joined Field
                  </label>
                </div>
              </div>
              <div class="col-lg-12 visibleInTable hide">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="sortable" value="${domainField?.sortable}" /> Field is Sortable
                  </label>
                </div>
              </div>
              <div class="col-lg-12 visibleInTable hide">
                <div class="form-group">
                  <label for="orderInTable">Order In Table</label>
                  <g:field type="number" name="orderInTable" class="form-control" maxlength="2" value="${domainField?.orderInTable}"/>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group">
              <label for="description">Description</label>
              <g:textArea name="description" class="form-control summernote" value="${domainField?.description}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" controller="domain" action="show" id="${domainInstance.id}">
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
    <asset:javascript src="domain/domain-field.js"/>
  </body>
</html>
