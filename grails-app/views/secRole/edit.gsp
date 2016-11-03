<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Edit Role</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Edit Role
      </h3>
    </div>
    <div class="row">
      <ol class="breadcrumb">
        <li>
          <g:link action="index">
            Role List
          </g:link>
        </li>
        <li>
          <g:link action="show" resource="${this.secRole}">
            Show Role
          </g:link>
        </li>
        <li class="active">Edit Role</li>
      </ol>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.secRole}">
        <g:eachError bean="${this.secRole}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <g:form resource="${this.secRole}" method="PUT">
        <g:hiddenField name="version" value="${this.secRole?.version}" />
        <div class="row">
          <div class="col-sm-12 col-md-8 col-lg-6">
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="authority">Authority</label>
                  <g:field type="text" name="authority" class="form-control" required="" value="${secRole?.authority}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group">
                  <label for="description">Description</label>
                  <g:field type="text" name="description" class="form-control" required="" value="${secRole?.description}"/>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" action="show" resource="${this.secRole}">
              Cancel
            </g:link>
            <button type="submit" class="btn btn-primary">
              <i class="glyphicon glyphicon-floppy-disk"></i>
              Update
            </button>
          </div>
        </div>
      </g:form>
    </div>
  </body>
</html>
