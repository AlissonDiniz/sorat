<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Project Template to Export TestCases</title>
  </head>
  <body>
    <div class="row">
      <h3 class="page-header">
        Project Template to Export TestCases
      </h3>
    </div>
    <div class="row">
      <ol class="breadcrumb">
        <li>
          <g:link action="index">
            Project List
          </g:link>
        </li>
        <li>
          <g:link action="show" resource="${this.project}">
            ${this.project.name}
          </g:link>
        </li>
        <li class="active">Project Template to Export TestCases</li>
      </ol>
    </div>
    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
          ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${this.project}">
        <g:eachError bean="${this.project}" var="error">
          <div class="alert alert-danger" role="alert">
            <g:message error="${error}"/>
          </div>
        </g:eachError>
      </g:hasErrors>
      <g:form action="saveExportTemplate" resource="${this.project}" method="POST">
        <div class="row">
          <div class="col-lg-12">
            <div class="form-group required">
              <label for="name">Name</label>
              <br />
              <span>${this.project.name}</span>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="well">
              <h4>Available Tags:</h4>
              <p>
                <span>Test Cases Block: <strong>#testcases ... #/testcases</strong></span>
                <br />
                <span>Replace to Test Case Title: <strong>{testCase.title}</strong></span>
                <br />
                <span>Replace to Test Case Summary: <strong>{testCase.summary}</strong></span>
                <br />
                <span>Replace to Test Case Pre-Conditions: <strong>{testCase.preConditions}</strong></span>
                <br />
                <span>Replace to Test Case Steps: <strong>{testCase.steps}</strong></span>
                <br />
                <span>Replace to Test Case Expected Results: <strong>{testCase.expectedResults}</strong></span>
                <br />
                <span>Replace to Test Case Key Word: <strong>{testCase.keyWord}</strong></span>
              </p>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="row">
              <div class="col-lg-4">
                <div class="form-group required">
                  <label for="extension">Extension</label>
                  <g:field type="text" name="extension" class="form-control" required="" value="${templateModel?.extension}" placeholder="Ex: .xml"/>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="row">
              <div class="col-lg-4">
                <div class="form-group required">
                  <label for="breakLine">Replace Break Line</label>
                  <g:select name="breakLine" class="form-control" required="" from="${br.org.venturus.domain.BreakLine.values()}" value="${templateModel?.breakLine}"/>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="checkbox">
              <label>
                <g:checkBox name="escapeXml" value="${templateModel?.escapeXml}" /> Escape Xml
              </label>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="form-group required">
              <label for="exportTemplate">Template</label>
              <g:textArea name="exportTemplate" class="form-control textarea-medium" required="" value="${templateModel?.exportTemplate}" rows="5" cols="40"/>
            </div>
          </div>
          <div class="col-lg-12">
            <g:link class="btn btn-warning" action="exportTemplate" resource="${this.project}">
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
