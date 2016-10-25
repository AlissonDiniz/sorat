<%@ defaultCodec="none" %>
<!DOCTYPE html>
<html>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <head>
    <title>Requirement Document</title>
    <style media="all">
      .wrapper{
      width: 1024px;
      max-width: 1024px;
      margin: 50px auto;
      font-family: "Open Sans", Helvetica, Arial, sans-serif;
      font-size: 14px;
      color: #404040;
      }
      .wrapper p, .wrapper li{
      text-align: justify;
      }
      .wrapper > .project-head > h3 > span{
      font-weight: normal;
      }
      .wrapper > .project-body > .section > .head img{
      width: 1024px;
      max-width: 1024px;
      border: solid #ccc 1px;
      }
      .wrapper > .project-body > .requirement > .head, 
      .wrapper > .project-body > .requirement > .body{ 
      padding-left: 20px;
      }
      .wrapper > .project-body > .requirement > .body > .testcases > .body li{
      padding-bottom: 10px;
      }
      .wrapper > .project-body > .requirement > .body > .testcases > .body li,
      .wrapper > .project-body > .requirement > .body > .testcases > .body li pre{
      width: 1024px;
      max-width: 1024px;
      }
    </style>
  </head>
  <body>
    <div class="wrapper">
      <div class="project-head">
        <h1>${project.name}</h1>
        <p>${project.description}</p>
        <h3>Version: <span>${project.projectVersion}</span></h3>
        <br />
      </div>
      <div class="project-body">
        <h2>Requirements</h2>
        <g:each var="requirement" status="r" in="${project.requirementList.sort{it.reqOrder}}">
          <div class="requirement">
            <div class="head">
              <h3>${r + 1} - ${requirement.name}</h3>
              <p>${requirement.description}</p>
              <br />
            </div>
            <div class="body">
              <div class="testcases">
                <div class="head">
                  <h3>Test Cases</h3>
                </div>
                <div class="body">
                  <ol>
                    <g:each var="testCase" in="${requirement.testCaseList.sort{it.testOrder}}">
                      <li>
                        <p>
                          <strong>Title:</strong> ${testCase.title}
                        </p>
                        <p>
                          <strong>Summary:</strong> 
                          <pre>${testCase.summary}</pre>
                        </p>
                        <p>
                          <strong>Pre - Conditions:</strong> 
                          <pre>${testCase.preConditions}</pre>
                        </p>
                        <p>
                          <strong>Steps:</strong> 
                          <pre>${testCase.steps}</pre>
                        </p>
                        <p>
                          <strong>Expected Results:</strong> 
                          <pre>${testCase.expectedResults}</pre>
                        </p>
                      </li>
                    </g:each>
                  </ol>
                  <br />
                </div>
              </div>
            </div>
          </div>
        </g:each>
      </div>
    </div>
  </body>
</html>
