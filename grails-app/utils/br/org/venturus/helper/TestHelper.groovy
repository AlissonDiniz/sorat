/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.venturus.helper

import br.org.venturus.sorat.Project;
import br.org.venturus.sorat.Requirement;
import br.org.venturus.sorat.RequirementAction;
import br.org.venturus.sorat.Domain;
import br.org.venturus.sorat.DomainField;
import br.org.venturus.sorat.ProjectTestDomain;
import br.org.venturus.sorat.ProjectTestTemplate;
import br.org.venturus.generator.GeneratorHelper
import br.org.venturus.domain.TestType;
import br.org.venturus.domain.ActionType;
import br.org.venturus.domain.FieldType;
import br.org.venturus.domain.RuleTest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author alisson
 */
class TestHelper {
       
  public void createFile(Project project, Requirement requirement, ZipOutputStream zip){
    ProjectTestDomain domainTestClass = project.domainTestClass;
    if(domainTestClass){
      String preparedContent = domainTestClass.content; 
      Domain domain = requirement.domain;
      String domainClassName = GeneratorHelper.getPreparedName(domain.name);
      String packageName = (domainClassName.substring(0, 1).toLowerCase() + domainClassName.substring(1));
      String filePath = "project_test" + File.separator + domainTestClass.path.replace('#PACKAGE_NAME', packageName) + File.separator + domainTestClass.name + ".java";
      zip.putNextEntry(new ZipEntry(filePath));

      StringBuilder builder = new StringBuilder();
      project.templateTestList.findAll{it.actionType.equals(TestType.EMPTY)}.each{template ->
        if(template.bellongsField){
          builder.append(generateTestCasesFromRule(template, domain));
        }else{
          builder.append(prepareTemplateContentWithoutField(template.content, domain));
        }
      };
      
      requirement.actionList.each{action ->
        def templateTestList = project.templateTestList.findAll{it.actionType.equals(action.type)};
        templateTestList.each{template ->
          if(template.bellongsField){
            builder.append(generateTestCasesFromRule(template, domain));
          }else{
            builder.append(prepareTemplateContentWithoutField(template.content, domain));
          }
        }
      }
        
      preparedContent = preparedContent.replaceAll('#PACKAGE_NAME', packageName);
      preparedContent = preparedContent.replaceAll('#DOMAIN_NAME', domainClassName);
      preparedContent = preparedContent.replaceAll('#TESTS_GENERATED', builder.toString());
      zip.write(preparedContent.bytes);
    }
  }
    
  private String prepareTemplateContentWithoutField(String content, Domain domain){
    StringBuilder builder = new StringBuilder();
    def preparedContent = content; 
    preparedContent = preparedContent.replaceAll('#DOMAIN_NAME', domain.name); 
    preparedContent = preparedContent.replaceAll('#DOMAIN_MENU', domain.menu); 
    builder.append(preparedContent);
    return builder.toString();
  }
    
  private String prepareTemplateContentWithField(String content, Domain domain, DomainField field){
    StringBuilder builder = new StringBuilder();
    def preparedContent = content; 
    preparedContent = preparedContent.replaceAll('#FIELD_NAME', GeneratorHelper.getPreparedName(field.name)); 
    preparedContent = preparedContent.replaceAll('#DOMAIN_NAME', domain.name); 
    preparedContent = preparedContent.replaceAll('#DOMAIN_MENU', domain.menu); 
    builder.append(preparedContent);
    return builder.toString();
  }
    
  
  private String generateTestCasesFromRule(ProjectTestTemplate template, Domain domain) {
    StringBuilder builder = new StringBuilder();
    switch (template.ruleTest) {
    case RuleTest.FILL:
      domain.fieldList.each{field ->
        builder.append(prepareTemplateContentWithField(template.content, domain, field));
      }
      break;
    case RuleTest.REQUIRED:
      domain.fieldList.findAll{it.required}.each{field ->
        builder.append(prepareTemplateContentWithField(template.content, domain, field));
      }
      break;
    case RuleTest.LIMIT_MIN:
      domain.fieldList.findAll{it.fieldLimitMin > 0}.each{field ->
        builder.append(prepareTemplateContentWithField(template.content, domain, field));
      }
      break;
    case RuleTest.LIMIT_MAX:
      domain.fieldList.findAll{it.fieldLimitMax > 0}.each{field ->
        builder.append(prepareTemplateContentWithField(template.content, domain, field));
      }
      break;
    case RuleTest.SEARCH:
      domain.fieldList.findAll{it.searchable}.each{field ->
        builder.append(prepareTemplateContentWithField(template.content, domain, field));
      }
      break;
    case RuleTest.FILTER:
      domain.fieldList.findAll{it.filterable}.each{field ->
        builder.append(prepareTemplateContentWithField(template.content, domain, field));
      }
      break;
    case RuleTest.SORT:
      domain.fieldList.findAll{it.sortable}.each{field ->
        builder.append(prepareTemplateContentWithField(template.content, domain, field));
      }
      break;
    }
    return builder.toString();
  }
  
  private String generateTestCasesFromFieldType(ProjectTestTemplate template, Domain domain) {
    StringBuilder builder = new StringBuilder();
    switch (template.fieldType) {
    case FieldType.ALPHABETIC:
      domain.fieldList.findAll{it.type.equals(FieldType.ALPHABETIC)}.each{field ->
        builder.append(prepareTemplateContentWithField(template.content, domain, field));
      }
      break;
    case FieldType.ALPHANUMERIC:
      domain.fieldList.findAll{it.type.equals(FieldType.ALPHANUMERIC)}.each{field ->
        builder.append(prepareTemplateContentWithField(template.content, domain, field));
      }
      break;
    case FieldType.NUMERIC:
      domain.fieldList.findAll{it.type.equals(FieldType.NUMERIC)}.each{field ->
        builder.append(prepareTemplateContentWithField(template.content, domain, field));
      }
      break;
    case FieldType.NUMERIC_FLOAT:
      domain.fieldList.findAll{it.type.equals(FieldType.NUMERIC_FLOAT)}.each{field ->
        builder.append(prepareTemplateContentWithField(template.content, domain, field));
      }
      break;
    }
    return builder.toString();
  }
}

