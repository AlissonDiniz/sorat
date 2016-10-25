/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.venturus.helper

import java.io.File;
import br.org.venturus.generator.GeneratorHelper;
import br.org.venturus.sorat.Project;
import br.org.venturus.sorat.ProjectTestDomain;
import br.org.venturus.sorat.Requirement;
import br.org.venturus.sorat.RequirementAction;
import br.org.venturus.sorat.Domain;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author alisson
 */
class DomainHelper {
    
  public void createFile(Project project, Requirement requirement, ZipOutputStream zip){
    ProjectTestDomain domainClass = project.domainClass;
    if(domainClass){
      String preparedContent = domainClass.content; 
      Domain domain = requirement.domain;
      String domainClassName = GeneratorHelper.getPreparedName(domain.name);
      String filePath = "project_test" + File.separator + domainClass.path + File.separator + domainClassName;
      zip.putNextEntry(new ZipEntry(filePath));

      preparedContent = preparedContent.replaceAll('#DOMAIN_NAME', domainClassName); 
      preparedContent = preparedContent.replaceAll('#DEFAULT_VIEWS', this.createDomainView(requirement)); 
      preparedContent = preparedContent.replaceAll('#DOMAIN_MAP', this.createMap(domain)); 
      zip.write(preparedContent.bytes);
    }
  }
       
  private String createDomainView(Requirement requirement){
    StringBuilder builder = new StringBuilder();
    int index = 1;
    requirement.actionList.each{action ->
      if(index < requirement.actionList.size()){
        builder.append("        ").append(action.type).append('("').append(action.title).append('"),');
        builder.append(System.getProperty("line.separator"));
        index++;
      }else{
        builder.append("        ").append(action.type).append('("').append(action.title).append('");');
        builder.append(System.getProperty("line.separator"));
      }
    };
    return builder.toString();
  }
    
  private String createMap(Domain domain){
    StringBuilder builder = new StringBuilder();
    int index = 1;
    domain.fieldList.each{field ->
      if(index < domain.fieldList.size()){
        builder.append("        ").append(GeneratorHelper.getFieldEnum(field.name)).append('("').append(field.elementId).append('", RandomHelper.Types.').append(field.type).append(", ").append(field.fieldLimitMin).append(", ").append(field.fieldLimitMax).append(", ").append(field.fieldDecimal).append("),");
        builder.append(System.getProperty("line.separator"));
        index++;
      }else{
        builder.append("        ").append(GeneratorHelper.getFieldEnum(field.name)).append('("').append(field.elementId).append('", RandomHelper.Types.').append(field.type).append(", ").append(field.fieldLimitMin).append(", ").append(field.fieldLimitMax).append(", ").append(field.fieldDecimal).append(");");
        builder.append(System.getProperty("line.separator"));
      }
    };
    return builder.toString();
  }
   
}