/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.venturus.helper

import br.org.venturus.sorat.DomainField;
import br.org.venturus.sorat.ActionModelField;
import br.org.venturus.sorat.RequirementAction;
import br.org.venturus.sorat.Requirement;
import br.org.venturus.sorat.TestCase;

/**
 *
 * @author alisson
 */
class ClonerHelper {
  
  static cloneDomainField(DomainField sourceInstance){
    def domainField = new DomainField();
    domainField.name = sourceInstance.name + ' COPY';
    domainField.type = sourceInstance.type;
    domainField.elementId = sourceInstance.elementId;
    domainField.fieldLimitMin = sourceInstance.fieldLimitMin;
    domainField.fieldLimitMax = sourceInstance.fieldLimitMax;
    domainField.fieldDecimal = sourceInstance.fieldDecimal;
    domainField.required = sourceInstance.required;
    domainField.fieldUnique = sourceInstance.fieldUnique;
    domainField.searchable = sourceInstance.searchable;
    domainField.filterable = sourceInstance.filterable;
    domainField.joinedField = sourceInstance.joinedField;
    domainField.sortable = sourceInstance.sortable;
    domainField.visibleInTable = sourceInstance.visibleInTable;
    domainField.orderInTable = sourceInstance.orderInTable;
    domainField.description = sourceInstance.description;
    domainField.domain = sourceInstance.domain;
    return domainField;
  }
  
  static cloneActionModelField(ActionModelField sourceInstance){
    def actionModelField = new ActionModelField();
    actionModelField.name = sourceInstance.name + ' COPY';
    actionModelField.type = sourceInstance.type;
    actionModelField.elementId = sourceInstance.elementId;
    actionModelField.fieldLimitMin = sourceInstance.fieldLimitMin;
    actionModelField.fieldLimitMax = sourceInstance.fieldLimitMax;
    actionModelField.fieldDecimal = sourceInstance.fieldDecimal;
    actionModelField.required = sourceInstance.required;
    actionModelField.fieldUnique = sourceInstance.fieldUnique;
    actionModelField.description = sourceInstance.description;
    actionModelField.actionModel = sourceInstance.actionModel;
    return actionModelField;
  }
  
  static cloneRequirementAction(RequirementAction sourceInstance){
    def requirementAction = new RequirementAction();
    requirementAction.title = sourceInstance.title + ' COPY';
    requirementAction.type = sourceInstance.type;
    requirementAction.description = sourceInstance.description;
    requirementAction.requirement = sourceInstance.requirement;
    return requirementAction;
  }
  
  static cloneRequirement(Requirement sourceInstance){
    def requirement = new Requirement();
    requirement.name = sourceInstance.name + ' COPY';
    requirement.description = sourceInstance.description;
    requirement.project = sourceInstance.project;
    return requirement;
  }
  
  static cloneTestCase(TestCase sourceInstance){
    def testCase = new TestCase();
    testCase.title = sourceInstance.title + ' COPY';
    testCase.summary = sourceInstance.summary;
    testCase.preConditions = sourceInstance.preConditions;
    testCase.steps = sourceInstance.steps;
    testCase.expectedResults = sourceInstance.expectedResults;
    testCase.keyWord = sourceInstance.keyWord;
    testCase.requirementAction = sourceInstance.requirementAction;
    return testCase;
  }
	
}

