/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.venturus.generator.helper;

import br.org.venturus.generator.GeneratorHelper;
import br.org.venturus.sorat.Domain;

/**
 *
 * @author alisson
 */
public class ButtonHelper extends AbstractHelper {

    public String generateValidateButtonCreateVisibilityTestCase(Domain domain) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonCreate").append(GeneratorHelper.getPreparedName(domain.name)).append("IsVisible() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getMainBot().checkVisibilityOfElement(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidateButtonCreateTestCase(Domain domain) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonCreate").append(GeneratorHelper.getPreparedName(domain.name)).append("() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultViews.CREATE.getTitle(), getMainBot().getScreenTitle());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateValidateButtonCancelVisibilityFromCreateTestCase(Domain domain)  {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonCancelFromCreate").append(GeneratorHelper.getPreparedName(domain.name)).append("IsVisible() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getMainBot().checkVisibilityOfElement(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CANCEL.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateValidateButtonCancelFromCreateTestCase(Domain domain)  {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonCancelFromCreate").append(GeneratorHelper.getPreparedName(domain.name)).append("() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillForm(instance.createFieldsMap(").append(GeneratorHelper.getPreparedDomainMap(domain.name)).append("));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CANCEL.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultViews.LIST.getTitle(), getMainBot().getScreenTitle());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateValidateButtonCancelVisibilityFromEditTestCase(Domain domain)  {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonCancelFromEdit").append(GeneratorHelper.getPreparedName(domain.name)).append("IsVisible() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getMainBot().checkVisibilityOfElement(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CANCEL.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateValidateButtonCancelFromEditTestCase(Domain domain)  {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonCancelFromEdit").append(GeneratorHelper.getPreparedName(domain.name)).append("() {");
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
        builder.append("        getFormBot().fillForm(instance.createFieldsMap(").append(GeneratorHelper.getPreparedDomainMap(domain.name)).append("));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CANCEL.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultViews.SHOW.getTitle(), getMainBot().getScreenTitle());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateValidateButtonListVisibilityFromCreateTestCase(Domain domain)  {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonListFromCreate").append(GeneratorHelper.getPreparedName(domain.name)).append("IsVisible() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getMainBot().checkVisibilityOfElement(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.LIST.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidateButtonListFromCreateTestCase(Domain domain)  {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");;
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonListFromCreate").append(GeneratorHelper.getPreparedName(domain.name)).append("() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.LIST.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultViews.LIST.getTitle(), getMainBot().getScreenTitle());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateValidateButtonEditVisibilityTestCase(Domain domain)  {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonEdit").append(GeneratorHelper.getPreparedName(domain.name)).append("IsVisible() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getMainBot().checkVisibilityOfElement(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateValidateButtonEditTestCase(Domain domain)  {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonEdit").append(GeneratorHelper.getPreparedName(domain.name)).append("() {");
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
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultViews.EDIT.getTitle(), getMainBot().getScreenTitle());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    public String generateValidateButtonListVisibilityFromShowTestCase(Domain domain)  {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonList").append(GeneratorHelper.getPreparedName(domain.name)).append("IsVisible() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getMainBot().checkVisibilityOfElement(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.LIST.getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidateButtonListTestFromShowTestCase(Domain domain)  {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateButtonList").append(GeneratorHelper.getPreparedName(domain.name)).append("() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, 0);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.LIST.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultViews.LIST.getTitle(), getMainBot().getScreenTitle());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
}
