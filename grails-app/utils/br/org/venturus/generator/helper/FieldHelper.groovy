/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.venturus.generator.helper;

import br.org.venturus.generator.GeneratorHelper;
import br.org.venturus.sorat.Domain;
import br.org.venturus.sorat.DomainField;;

/**
 *
 * @author alisson
 */
public class FieldHelper extends AbstractHelper {

    public String generateRequiredFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateRequired").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Map<String, String> map = instance.createFieldsMap(").append(GeneratorHelper.getPreparedDomainMap(domain.name)).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        map.remove(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillForm(map);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().focusAndBlur(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(FormBot.REQUIRED_FIELD_MESSAGE, getFormBot().getViolationMsgFromRequiredField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(false, getMainBot().checkIfElementIsEnable(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.SAVE.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateRequiredFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateRequired").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Map<String, String> map = instance.createFieldsMap(").append(GeneratorHelper.getPreparedDomainMap(domain.name)).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        map.remove(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillForm(map);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().focusAndBlur(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(FormBot.REQUIRED_FIELD_MESSAGE, getFormBot().getViolationMsgFromRequiredField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(false, getMainBot().checkIfElementIsEnable(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.SAVE.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateBlankSpacesFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateBlankSpaces").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillFieldWithBlankSpaces(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().focusAndBlur(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(FormBot.REQUIRED_FIELD_MESSAGE, getFormBot().getViolationMsgFromRequiredField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(false, getMainBot().checkIfElementIsEnable(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.SAVE.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateBlankSpacesFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateBlankSpaces").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillFieldWithBlankSpaces(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().focusAndBlur(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(FormBot.REQUIRED_FIELD_MESSAGE, getFormBot().getViolationMsgFromRequiredField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(false, getMainBot().checkIfElementIsEnable(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.SAVE.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateLimitMaxCharactersFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateLimitMaxCharacters").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = instance.getRandomValueGreaterMax(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax(), getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()).length());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateLimitMaxCharactersFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateLimitMaxCharacters").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = instance.getRandomValueGreaterMax(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax(), getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()).length());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateLimitMinCharactersFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateLimitMinCharacters").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = instance.getRandomValueLesserMin(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getFormBot().getViolationMsgFromRequiredField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()).contains(FormBot.LIMIT_MIN_VIOLATION));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(false, getMainBot().checkIfElementIsEnable(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.SAVE.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateLimitMinCharactersFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateLimitMinCharacters").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = instance.getRandomValueLesserMin(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getFormBot().getViolationMsgFromRequiredField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()).contains(FormBot.LIMIT_MIN_VIOLATION));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(false, getMainBot().checkIfElementIsEnable(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.SAVE.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateNotAcceptSpecialCharactersFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateNotAcceptSpecialCharacters").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.SpecialCharacter.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(null, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateNotAcceptSpecialCharactersFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateNotAcceptSpecialCharacters").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.SpecialCharacter.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(null, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateAcceptSpecialCharactersFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateAcceptSpecialCharacters").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.SpecialCharacter.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(random, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateAcceptSpecialCharactersFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateAcceptSpecialCharacters").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
         builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.SpecialCharacter.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(random, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateNotAcceptAlphabeticFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateNotAcceptAlphabetic").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.Alphabetic.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(null, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateNotAcceptAlphabeticFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateNotAcceptAlphabetic").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.Alphabetic.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(null, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateAcceptAlphabeticFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateAcceptAlphabetic").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.Alphabetic.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(random, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateAcceptAlphabeticFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateAcceptAlphabetic").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.Alphabetic.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(random, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateNotAcceptAlphaNumericFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateNotAcceptAlphaNumeric").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.AlphaNumeric.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(null, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateNotAcceptAlphaNumericFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateNotAcceptAlphaNumeric").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.AlphaNumeric.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(null, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateAcceptAlphaNumericFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateAcceptAlphaNumeric").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.AlphaNumeric.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(random, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateAcceptAlphaNumericFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateAcceptAlphaNumeric").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.AlphaNumeric.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(random, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateNotAcceptNumericFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateNotAcceptNumeric").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.Numeric.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(null, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateNotAcceptNumericFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateNotAcceptNumeric").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.Numeric.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(null, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateAcceptNumericFromCreateTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateAcceptNumeric").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromCreate() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.Numeric.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(random, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateAcceptNumericFromEditTestCases(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateAcceptNumeric").append(GeneratorHelper.getPreparedName(field.name)).append("FieldFromEdit() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String random = RandomHelper.Numeric.random(new RandomTO(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getLimitMax()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), random);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(random, getFormBot().getFieldValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

}
