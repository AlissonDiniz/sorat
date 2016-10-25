<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>
      Sorat
    </title>
  <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
  <asset:stylesheet src="login.css" />
</head>
<body>
  <div class="wrapper-login">
    <div class="container">
      <div id="login-container" class="row login-container">
        <section class="login-form">
          <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
              <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                <h1 class="text-center">Welcome to Sorat</h1>
                <br />
                <br />
                <form id="credentialForm" name="credentialForm" action="${postUrl ?: '/login/authenticate'}" method="POST">
                  <div class="form-group">
                    <div class="input-group">
                      <label for="username" class="input-group-addon glyphicon glyphicon-user"></label>
                      <input id="username" name="username" type="text" class="form-control input-lg" required placeholder="Login" maxlength="15" tabindex="1">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group">
                      <label for="password" class="input-group-addon glyphicon glyphicon-lock"></label>
                      <input id="password" name="password" type="password" class="form-control input-lg" required placeholder="Password" maxlength="30" tabindex="2">
                    </div> 
                  </div>
                  <div class="form-group">
                    <div class="checkbox">
                      <label>
                        <input type="checkbox" id="remember_me" name="remember_me" />
                        <g:message code='springSecurity.login.remember.me.label' />
                      </div>
                    </div>
                    <div class="form-actions">
                      <button id="submit-command-button" type="submit" class="btn btn-lg btn-lg btn-primary btn-block">
                        Acessar
                      </button>
                    </div>
                    <g:if test='${flash.message}'>
                    <br />
                    <div id="login-alert-message" class="login-alert-message alert alert-danger">${flash.message}</div>
                    </g:if>
                  </form>
                </div>
              </div>
            </div>
          </section>  
        </div>
      </div>   
    </div>
  </body>
</html>