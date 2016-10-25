/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.venturus.helper

import br.org.venturus.sorat.security.SecUser;
import br.org.venturus.sorat.Project;

/**
 *
 * @author alisson
 */
class ProjectHelper {
  
  static listMyProjects(def secUser){
    def projectList = [];
    if(secUser.authorities.any{it.authority == "ROLE_ADMIN"}){
      projectList = Project.list();
    }else{
      Project.list().collect{project ->
        if(project.userCreated.equals(secUser.username) || project.team.contains(secUser)){
          projectList << project;
        }
      }
    }
    return projectList;
  }
  
  static validateMember(def project, def secUser){
    def projectValidated;
    if(secUser.authorities.any{it.authority == "ROLE_ADMIN"} || project.team.contains(secUser)){
      projectValidated = project;
    }
    return projectValidated;
  }
}

