<%@ defaultCodec="none" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Project List</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Project List
      </h3>
    </div>
    <div class="row">
      <nav class="navbar navbar-default">
        <ul class="nav navbar-nav">
          <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_MANAGER">
          <li>
            <g:link action="create">
              <i class="glyphicon glyphicon-plus-sign"></i>
              Create Project
            </g:link>
          </li>
          </sec:ifAnyGranted>
        </ul>
      </nav>
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <div id="list-project" class="content scaffold-list" role="main">
        <table class="table table-striped">
          <thead>
            <tr>
              <th class="td-1">#</th>
              <th class="td-12">Name</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            <g:each in="${projectList}" var="projectInstance">
              <tr onclick="window.location.href='<g:createLink action="show" id="${projectInstance.id}" />'">
                <th class="td-1">
                  ${projectInstance.id}
                </th>
                <td class="td-12">${projectInstance.name}</td>
                <td>${projectInstance.description}</td> 
              </tr>
            </g:each>
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>