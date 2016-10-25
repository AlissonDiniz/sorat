<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Role List</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Role List
      </h3>
    </div>
    <div class="row">
      <nav class="navbar navbar-default">
        <ul class="nav navbar-nav">
          <li>
            <g:link action="create">
              <i class="glyphicon glyphicon-plus-sign"></i>
              Create Role
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
              <th class="td-12">Authority</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            <g:each in="${secRoleList}" var="secRoleInstance">
              <tr onclick="window.location.href='<g:createLink action="show" id="${secRoleInstance.id}" />'">
                <th class="td-1">
                  ${secRoleInstance.id}
                </th>
                <td class="td-12">${secRoleInstance.authority}</td>
                <td>${secRoleInstance.description}</td> 
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