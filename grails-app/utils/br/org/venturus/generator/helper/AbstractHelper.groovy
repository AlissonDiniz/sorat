/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.venturus.generator.helper;

import java.lang.reflect.Field;

/**
 *
 * @author alisson
 */
public abstract class AbstractHelper {

    protected String getRandomGenerator(String pageName, Field field) {
//        org.venturus.qa.generator.annotation.Field annotation = field.getAnnotation(org.venturus.qa.generator.annotation.Field.class);
//        String fieldEnum = annotation.name();
//        return "RandomHelper.generate" + annotation.generator() + "Value(" + pageName + ".Fields." + fieldEnum + ".getLimit())";
        return "";
    }

}
