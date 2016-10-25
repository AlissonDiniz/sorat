/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.venturus.generator.helper;

import br.org.venturus.generator.GeneratorHelper;
import br.org.venturus.sorat.Domain;
import br.org.venturus.sorat.DomainField;

/**
 *
 * @author alisson
 */
public class EntityHelper extends AbstractHelper {

    public String generateCreateInstanceTestCase(Domain domain) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateCreate").append(GeneratorHelper.getPreparedName(domain.name)).append("Instance() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Map map = instance.createFieldsMap();");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillForm(map);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.SAVE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultViews.SHOW.getTitle(), getMainBot().getScreenTitle());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        map.forEach((Object key, Object value) -> {");
        builder.append(System.getProperty("line.separator"));
        builder.append("            String fieldValue = getMainBot().getTextValue((String) key);");
        builder.append(System.getProperty("line.separator"));
        builder.append("            Assert.assertEquals(value, fieldValue);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        });");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateEditFieldTestCase(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateEdit").append(GeneratorHelper.getPreparedName(domain.name)).append("Field() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Map map = instance.createFieldsMap();");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillForm(map);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.SAVE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.EDIT.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String comparatorValue = instance.getRandomValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillField(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId(), comparatorValue);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.UPDATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultViews.SHOW.getTitle(), getMainBot().getScreenTitle());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(comparatorValue, getMainBot().getTextValue(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId()));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

}
