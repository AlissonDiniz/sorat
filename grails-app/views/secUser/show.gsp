<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Show User</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Show User
      </h3>
    </div>
    <div class="row row-actions">
      <ol class="breadcrumb">
        <li>
          <g:link action="index">
            User List
          </g:link>
        </li>
        <li class="active">Show User</li>
      </ol>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="showOptions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          <i class="glyphicon glyphicon-cog"></i>
          Options
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="showOptions">
          <li>
            <g:link action="edit" resource="${this.secUser}">
              <i class="glyphicon glyphicon-pencil"></i>
              Edit
            </g:link>
          </li>
          <li>
            <g:link action="create">
              <i class="glyphicon glyphicon-plus-sign"></i>
              Create New User
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
      <g:hasErrors bean="${this.secUser}">
        <g:eachError bean="${this.secUser}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <div id="show-secUser" data-id="${this.secUser.id}" class="col-sm-12 content scaffold-show" role="main">
        <div class="row">
          <ol class="property-list secUser">
            <li>
              <span id="name-label" class="property-label">Name</span>
              <div class="property-value" aria-labelledby="name-label">${secUser.name}</div>
            </li>
            <li>
              <span id="email-label" class="property-label">Email</span>
              <div class="property-value" aria-labelledby="email-label">${secUser.email}</div>
            </li>
            <li>
              <span id="username-label" class="property-label">Username</span>
              <div class="property-value" aria-labelledby="username-label">${secUser.username}</div>
            </li>
            <li>
              <span id="enabled-label" class="property-label">Enabled</span>
              <div class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${secUser.enabled}" /></div>
            </li>
            <li>
              <span id="accountExpired-label" class="property-label">Account Expired</span>
              <div class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${secUser.accountExpired}" /></div>
            </li>
            <li>
              <span id="accountLocked-label" class="property-label">Account Locked</span>
              <div class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${secUser.accountLocked}" /></div>
            </li>
            <li>
              <span id="passwordExpired-label" class="property-label">Password Expired</span>
              <div class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean boolean="${secUser.passwordExpired}" /></div>
            </li>
            <li>
              <span id="passwordExpired-label" class="property-label">Role List</span>
              <div class="property-value" aria-labelledby="passwordExpired-label">
                <g:each in="${secUser.getAuthorities()}" var="secRole">
                  ${secRole.description}, 
                </g:each>
              </div>
            </li>
            <li>
              <span id="userCreated-label" class="property-label">User Created</span>
              <div class="property-value" aria-labelledby="userCreated-label">${secUser.userCreated}</div>
            </li>
            <li>
              <span id="dateCreated-label" class="property-label">Date Created</span>
              <div class="property-value" aria-labelledby="dateCreated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${secUser.dateCreated}"/></div>
            </li>
            <li>
              <span id="userUpdated-label" class="property-label">User Updated</span>
              <div class="property-value" aria-labelledby="userUpdated-label">${secUser.userUpdated}</div>
            </li>
            <li>
              <span id="lastUpdated-label" class="property-label">Last Updated</span>
              <div class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate format="yyyy-MM-dd HH:mm" date="${secUser.lastUpdated}"/></div>
            </li>
          </ol>
        </div>
      </div>
    </div>
  </body>
</html>
