package br.org.venturus.sorat

import grails.transaction.Transactional
import br.org.venturus.sorat.RequirementAction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.org.venturus.domain.BreakLine;


@Transactional
class ExportService {

  def compile(def templateModel, RequirementAction requirementAction) {
    def content = "";
    if(templateModel){
      content = this.compileTestCases(templateModel, requirementAction);
    }
    return content;
  }
  
  private String compileTestCases(def templateModel, RequirementAction requirementAction){
    String compiledContent = "";
    Matcher matcherInit = templateModel.exportTemplate =~ /(?i)\#testcases( |\n|\r\n)/;
    Matcher matcherEnd = templateModel.exportTemplate =~ /(?i)\#\/testcases( |\n|\r\n)/;
    
    boolean replace = !templateModel.breakLine.equals(BreakLine.NOT);
    String breakLine = templateModel.breakLine.equals(BreakLine.EMPTY) ? '' : '<![CDATA[<br>]]>';
    int init = 0;
    while(matcherInit.find() && matcherEnd.find()){
      String template = templateModel.exportTemplate.substring(matcherInit.end(), matcherEnd.start());
      String wrapper = "";
      
      requirementAction.testCaseList.sort{it.testOrder}.each{it ->
        String content = "";
        
        content = template.replaceAll("\\{testCase.title\\}", this.prepareValue(it.title, replace, breakLine, templateModel.escapeXml));
        content = content.replaceAll("\\{testCase.summary\\}", this.prepareValue(it.summary, replace, breakLine, templateModel.escapeXml));
        content = content.replaceAll("\\{testCase.preConditions\\}", this.prepareValue(it.preConditions, replace, breakLine, templateModel.escapeXml));
        content = content.replaceAll("\\{testCase.steps\\}", this.prepareValue(it.steps, replace, breakLine, templateModel.escapeXml));
        content = content.replaceAll("\\{testCase.expectedResults\\}", this.prepareValue(it.expectedResults, replace, breakLine, templateModel.escapeXml));
        
        content = content.replaceAll("\\{testCase.keyWord\\}", it.keyWord.toString());
        wrapper += content;
      }
      compiledContent += templateModel.exportTemplate.substring(init, matcherInit.start()) + wrapper;
      init = matcherEnd.end();
    }
    compiledContent += templateModel.exportTemplate.substring(init, templateModel.exportTemplate.length());
    return compiledContent;
  }
  
  private String prepareValue(String value, boolean replace, String breakLine, boolean escapeXml){
    String valuePrepared = value;
    if(escapeXml){
      valuePrepared = valuePrepared.replaceAll(/\"|\'|\>|\<|\\|\//, "");
    }
    if(replace){
      valuePrepared = valuePrepared.replaceAll("\\r\n", breakLine);
    }
    return valuePrepared;
  }

}
