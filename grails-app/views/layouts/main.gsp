<!doctype html>
<html lang="en" class="no-js">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>
      Sorat
    </title>
  <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
  <asset:stylesheet src="application.css" />
  <g:layoutHead/>
</head>
<body>
  <header>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="row">
          <div class="navbar-header">
            <span class="navbar-brand">Sorat</span>
          </div>
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li>
                <g:link controller="project" action="index">Project List</g:link>
              </li>
              <sec:ifAnyGranted roles="ROLE_ADMIN">
              <li>
                <g:link controller="secUser" action="index">User List</g:link>
              </li>
              <li>
                <g:link controller="secRole" action="index">Role List</g:link>
              </li>
              </sec:ifAnyGranted>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"><sec:loggedInUserInfo field="name"/>&nbsp;&nbsp;<span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li role="separator" class="divider"></li>
                  <li>
                    <g:link controller="logout">
                      <span class="glyphicon glyphicon-log-out"></span>
                      Sair
                    </g:link>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </nav>
  </header>
  <div class="container">
    <asset:javascript src="application.js"/>
    <section>
      <g:layoutBody/>
    </section>
    <div class="row">
      <hr>
      <footer>
        <div class="row">
          <div class="col-lg-12">
            <p>Copyright © Alisson Diniz 2016</p>
          </div>
        </div>
      </footer>
    </div>
  </div>
</body>
</html>
