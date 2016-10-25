<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Edit User</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Edit User
        <g:link class="btn btn-primary pull-right" action="create">
          <i class="glyphicon glyphicon-plus-sign"></i>
          Create User
        </g:link>
      </h3>
    </div>
    <div class="row">
      <ol class="breadcrumb">
        <li>
          <g:link action="index">
            User List
          </g:link>
        </li>
        <li>
          <g:link action="show" resource="${this.secUser}">
            Show User
          </g:link>
        </li>
        <li class="active">Edit User</li>
      </ol>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.secUser}">
        <g:eachError bean="${this.secUser}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <g:form resource="${this.secUser}" method="PUT">
        <g:hiddenField name="version" value="${this.secUser?.version}" />
        <div class="row">
          <div class="col-sm-12 col-md-8 col-lg-6">
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="name">Name</label>
                  <g:field type="text" name="name" class="form-control" required="" value="${secUser?.name}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="email">Email</label>
                  <g:field type="text" name="email" class="form-control" required="" value="${secUser?.email}"/>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="form-group required">
                  <label for="username">Username</label>
                  <g:field type="text" name="usernameTemp" class="form-control" required="" value="${secUser?.username}" disabled="disabled"/>
                  <g:field type="text" name="username" class="form-control hide" required="" value="${secUser?.username}" />
                </div>
              </div>
              <div class="col-lg-12">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="enabled" value="${secUser?.enabled}" /> Enabled
                  </label>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="accountExpired" value="${secUser?.accountExpired}" /> Account Expired
                  </label>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="accountLocked" value="${secUser?.accountLocked}" /> Account Locked
                  </label>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="checkbox">
                  <label>
                    <g:checkBox name="passwordExpired" value="${secUser?.passwordExpired}" /> Password Expired
                  </label>
                </div>
              </div>
              <div class="col-lg-12">
                <br />
                <div class="form-group multiselect-wrapper required">
                  <label for="roles">Role List</label>
                  <g:select name="roles" class="form-control multiple" from="${br.org.venturus.sorat.security.SecRole.list()}" required="" value="${secUser?.getAuthorities()*.id}" optionKey="id" multiple="true" />
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <br />
            <g:link class="btn btn-warning" action="show" resource="${this.secUser}">
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
