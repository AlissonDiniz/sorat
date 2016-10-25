package br.org.venturus.sorat

import grails.transaction.Transactional
import br.org.venturus.sorat.Project;
import br.org.venturus.sorat.Requirement;
import br.org.venturus.sorat.RequirementAction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Transactional
class ReportService {

  def compile(Project project) {
    String documentContent = project.documentTemplate;
    documentContent = documentContent.replaceAll("\\{project.name\\}", project.name);
    documentContent = documentContent.replaceAll("\\{project.description\\}", project.description);
    documentContent = documentContent.replaceAll("\\{project.projectVersion\\}", project.projectVersion);

    documentContent = this.compileSections(documentContent, project);
    documentContent = this.compileRequirements(documentContent, project);
    return documentContent;
  }
  
  private String compileSections(String documentContent, Project project){
    String compiledContent = "";
    Matcher matcherInit = documentContent =~ /(?i)\#sections( |\n|\r\n)/;
    Matcher matcherEnd = documentContent =~ /(?i)\#\/sections( |\n|\r\n)/;
    
    int init = 0;
    while(matcherInit.find() && matcherEnd.find()){
      String template = documentContent.substring(matcherInit.end(), matcherEnd.start());
      String wrapper = "";
      
      int index = 1;
      project.sectionList.sort{it.secOrder}.each{it ->
        String content = "";
        content = template.replaceAll("\\{section.name\\}", it.name);
        content = content.replaceAll("\\{section.index\\}", index.toString());

        String description = it.description ? it.description : "";
        content = content.replaceAll("\\{section.description\\}", description);
        
        index++;
        wrapper += content;
      }
      compiledContent += documentContent.substring(init, matcherInit.start()) + wrapper
      init = matcherEnd.end();
    }
    compiledContent += documentContent.substring(init, documentContent.length());
    return compiledContent;
  } 
  
  private String compileRequirements(String documentContent, Project project){
    String compiledContent = "";
    Matcher matcherInit = documentContent =~ /(?i)\#requirements( |\n|\r\n)/;
    Matcher matcherEnd = documentContent =~ /(?i)\#\/requirements( |\n|\r\n)/;
    
    int init = 0;
    while(matcherInit.find() && matcherEnd.find()){
      String template = documentContent.substring(matcherInit.end(), matcherEnd.start());
      String wrapper = "";

      int index = 1;
      project.requirementList.sort{it.reqOrder}.each{requirement ->
        String content = "";
        content = template.replaceAll("\\{requirement.name\\}", requirement.name);
        content = content.replaceAll("\\{requirement.index\\}", index.toString());

        String description = requirement.description ? requirement.description : "";
        content = content.replaceAll("\\{requirement.description\\}", description);
        
        content = this.compileDomain(content, index, requirement);
        content = this.compileActions(content, index, requirement);
        index++;
        wrapper += content;
      }
      compiledContent += documentContent.substring(init, matcherInit.start()) + wrapper
      init = matcherEnd.end();
    }
    compiledContent += documentContent.substring(init, documentContent.length());
    return compiledContent;
  }
  
  private String compileDomain(String reqContent, int indexR, Requirement requirement){
    String compiledContent = "";
    Matcher matcherInit = reqContent =~ /(?i)\#domain( |\n|\r\n)/;
    Matcher matcherEnd = reqContent =~ /(?i)\#\/domain( |\n|\r\n)/;
    
    int init = 0;
    while(matcherInit.find() && matcherEnd.find()){
      String template = reqContent.substring(matcherInit.end(), matcherEnd.start());
      String wrapper = "";

      if(requirement.domain){
        def domain = requirement.domain;
        wrapper = template.replaceAll("\\{domain.name\\}", domain.name);
        wrapper = this.compileDomainFields(wrapper, indexR, domain);
      }
      compiledContent += reqContent.substring(init, matcherInit.start()) + wrapper
      init = matcherEnd.end();
    }
    compiledContent += reqContent.substring(init, reqContent.length());
    return compiledContent;
  }
  
  private String compileDomainFields(String domainContent, int indexR, Domain domain){
    String compiledContent = "";
    Matcher matcherInit = domainContent =~ /(?i)\#domainfields( |\n|\r\n)/;
    Matcher matcherEnd = domainContent =~ /(?i)\#\/domainfields( |\n|\r\n)/;
    
    int init = 0;
    while(matcherInit.find() && matcherEnd.find()){
      String template = domainContent.substring(matcherInit.end(), matcherEnd.start());
      String wrapper = "";
      
      int index = 1;
      domain.fieldList.sort{it.id}.each{field ->
        String subCompiledContent = "";
        Matcher subMatcherInit = template =~ /(?i)\#attr( |\n|\r\n)/;
        Matcher subMatcherEnd = template =~ /(?i)\#\/attr( |\n|\r\n)/;
        
        int subInit = 0;
        while(subMatcherInit.find() && subMatcherEnd.find()){
          String subTemplate = template.substring(subMatcherInit.end(), subMatcherEnd.start());
          String subWrapper = "";
          
          String attr = subTemplate.substring((subTemplate.indexOf('field.') + 6), subTemplate.indexOf('}'));
          String value = "";

          switch(attr){
            case 'name':
              value = field.name ? field.name : "";
              break;
            case 'type':
              value = field.type ? field.type.toString() : "";
              break;
            case 'required':
              value = field.required.toString();
              break;
            case 'fieldUnique':
              value = field.fieldUnique.toString();
              break;
            case 'visibleInTable':
              value = field.visibleInTable.toString();
              break;
            case 'searchable':
              value = field.searchable.toString();
              break;
            case 'filterable':
              value = field.filterable.toString();
              break;
            case 'sortable':
              value = field.sortable.toString();
              break;
            case 'fieldLimitMin':
              value = field.fieldLimitMin > 0 ? field.fieldLimitMin.toString() : "";
              break;
            case 'fieldLimitMax':
              value = field.fieldLimitMax > 0 ? field.fieldLimitMax.toString() : "";
              break;
            case 'fieldDecimal':
              value = field.fieldDecimal > 0 ? field.fieldDecimal.toString() : "";
              break;
          }
          
          if(value){
            subWrapper = subTemplate.replace("{field." + attr + "}", value);
          }
        
          subCompiledContent += template.substring(subInit, subMatcherInit.start()) + subWrapper
          subInit = subMatcherEnd.end();
        }
        subCompiledContent += template.substring(subInit, template.length());
        
        subCompiledContent = subCompiledContent.replaceAll("\\{requirement.index\\}", indexR.toString());
        subCompiledContent = subCompiledContent.replaceAll("\\{domainfield.index\\}", index.toString());
        wrapper += subCompiledContent;
        index++;
      }
      
      compiledContent += domainContent.substring(init, matcherInit.start()) + wrapper
      init = matcherEnd.end();
    }
    compiledContent += domainContent.substring(init, domainContent.length());
    return compiledContent;
  }
  
  private String compileActions(String reqContent, int indexR, Requirement requirement){
    String compiledContent = "";
    Matcher matcherInit = reqContent =~ /(?i)\#actions( |\n|\r\n)/;
    Matcher matcherEnd = reqContent =~ /(?i)\#\/actions( |\n|\r\n)/;
    
    int init = 0;
    while(matcherInit.find() && matcherEnd.find()){
      String template = reqContent.substring(matcherInit.end(), matcherEnd.start());
      String wrapper = "";
      
      int index = 1;
      requirement.actionList.sort{it.actionOrder}.each{action ->
        String content = template.replaceAll("\\{action.title\\}", action.title);
        content = content.replaceAll("\\{requirement.index\\}", indexR.toString());
        content = content.replaceAll("\\{action.index\\}", index.toString());
        
        String description = action.description ? action.description : "";
        content = content.replaceAll("\\{action.description\\}", description);
        String screens = action.screens ? action.screens : "";
        content = content.replaceAll("\\{action.screens\\}", screens);
        
        content = this.compileActionModel(content, index, action);
        
        wrapper += content;
        index++;
      }
      compiledContent += reqContent.substring(init, matcherInit.start()) + wrapper
      init = matcherEnd.end();
    }
    compiledContent += reqContent.substring(init, reqContent.length());
    return compiledContent;
  }
  
  private String compileActionModel(String actionContent, int indexA, RequirementAction requirementAction){
    String compiledContent = "";
    Matcher matcherInit = actionContent =~ /(?i)\#actionmodel( |\n|\r\n)/;
    Matcher matcherEnd = actionContent =~ /(?i)\#\/actionmodel( |\n|\r\n)/;
    
    int init = 0;
    while(matcherInit.find() && matcherEnd.find()){
      String template = actionContent.substring(matcherInit.end(), matcherEnd.start());
      String wrapper = "";

      if(requirementAction.actionModel){
        def actionModel = requirementAction.actionModel;
        wrapper = template.replaceAll("\\{actionmodel.name\\}", actionModel.name);
        wrapper = this.compileActionModelFields(wrapper, indexA, actionModel);
      }
      compiledContent += actionContent.substring(init, matcherInit.start()) + wrapper
      init = matcherEnd.end();
    }
    compiledContent += actionContent.substring(init, actionContent.length());
    return compiledContent;
  }
  
  private String compileActionModelFields(String actionModelContent, int indexA, ActionModel actionModel){
    String compiledContent = "";
    Matcher matcherInit = actionModelContent =~ /(?i)\#actionmodelfields( |\n|\r\n)/;
    Matcher matcherEnd = actionModelContent =~ /(?i)\#\/actionmodelfields( |\n|\r\n)/;
    
    int init = 0;
    while(matcherInit.find() && matcherEnd.find()){
      String template = actionModelContent.substring(matcherInit.end(), matcherEnd.start());
      String wrapper = "";
      
      int index = 1;
      actionModel.fieldList.sort{it.id}.each{field ->
        String subCompiledContent = "";
        Matcher subMatcherInit = template =~ /(?i)\#attr( |\n|\r\n)/;
        Matcher subMatcherEnd = template =~ /(?i)\#\/attr( |\n|\r\n)/;
        
        int subInit = 0;
        while(subMatcherInit.find() && subMatcherEnd.find()){
          String subTemplate = template.substring(subMatcherInit.end(), subMatcherEnd.start());
          String subWrapper = "";
          
          String attr = subTemplate.substring((subTemplate.indexOf('field.') + 6), subTemplate.indexOf('}'));
          String value = "";

          switch(attr){
            case 'name':
              value = field.name ? field.name : "";
              break;
            case 'type':
              value = field.type ? field.type.toString() : "";
              break;
            case 'required':
              value = field.required.toString();
              break;
            case 'fieldUnique':
              value = field.fieldUnique.toString();
              break;
            case 'fieldLimitMin':
              value = field.fieldLimitMin > 0 ? field.fieldLimitMin.toString() : "";
              break;
            case 'fieldLimitMax':
              value = field.fieldLimitMax > 0 ? field.fieldLimitMax.toString() : "";
              break;
            case 'fieldDecimal':
              value = field.fieldDecimal > 0 ? field.fieldDecimal.toString() : "";
              break;
          }
          
          if(value){
            subWrapper = subTemplate.replace("{field." + attr + "}", value);
          }
        
          subCompiledContent += template.substring(subInit, subMatcherInit.start()) + subWrapper
          subInit = subMatcherEnd.end();
        }
        subCompiledContent += template.substring(subInit, template.length());
        
        subCompiledContent = subCompiledContent.replaceAll("\\{action.index\\}", indexA.toString());
        subCompiledContent = subCompiledContent.replaceAll("\\{domainfield.index\\}", index.toString());
        wrapper += subCompiledContent;
        index++;
      }
      
      compiledContent += actionModelContent.substring(init, matcherInit.start()) + wrapper
      init = matcherEnd.end();
    }
    compiledContent += actionModelContent.substring(init, actionModelContent.length());
    return compiledContent;
  }

}
