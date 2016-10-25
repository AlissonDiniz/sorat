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
public class ViewHelper extends AbstractHelper {

    public String generateAccessTestCase(Domain domain) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateAccess").append(GeneratorHelper.getPreparedName(domain.name)).append("() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(domain.menu).append(".getPath(), getMainBot().getCurrentPartialUrl());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

}
