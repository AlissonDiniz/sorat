<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>User List</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        User List
      </h3>
    </div>
    <div class="row">
      <nav class="navbar navbar-default">
        <ul class="nav navbar-nav">
          <li>
            <g:link action="create">
              <i class="glyphicon glyphicon-plus-sign"></i>
              Create User
            </g:link>
          </li>
        </ul>
      </nav>
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <div id="list-secRole" class="content scaffold-list" role="main">
        <table class="table table-striped">
          <thead>
            <tr>
              <th class="td-1">#</th>
              <th>Name</th>
              <th class="td-4">Email</th>
              <th class="td-4">Username</th>
              <th class="td-2">Enabled</th>
              <th class="td-3">Account <br />Expired</th>
              <th class="td-3">Account <br />Locked</th>
              <th class="td-3">Password <br />Expired</th>
            </tr>
          </thead>
          <tbody>
            <g:each in="${secUserList}" var="secUserInstance">
              <tr onclick="window.location.href='<g:createLink action="show" id="${secUserInstance.id}" />'">
                <th class="td-1">
                  ${secUserInstance.id}
                </th>
                <td>${secUserInstance.name}</td> 
                <td class="td-4">${secUserInstance.email}</td> 
                <td class="td-4">${secUserInstance.username}</td> 
                <td class="td-2"><g:formatBoolean boolean="${secUserInstance.enabled}" /></td>
                <td class="td-3"><g:formatBoolean boolean="${secUserInstance.accountExpired}" /></td>
                <td class="td-3"><g:formatBoolean boolean="${secUserInstance.accountLocked}" /></td>
                <td class="td-3"><g:formatBoolean boolean="${secUserInstance.passwordExpired}" /></td>
              </tr>
            </g:each>
          </tbody>
        </table>
        <div class="pagination">
          <g:paginate total="${secRoleCount ?: 0}" />
        </div>
      </div>
    </div>
  </body>
</html>