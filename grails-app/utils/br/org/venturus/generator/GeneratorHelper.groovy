/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.venturus.generator

/**
 *
 * @author alisson
 */
class GeneratorHelper {
    
    public static String getPreparedName(String name) {
        return name.replaceAll("\\s","");
    }
    
    public static String getDomainEnum(String domainName) {
        String name = domainName.replaceAll("\\s+","");
        return String.join("_", name.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")).toUpperCase();
    }
    
    public static String getFieldEnum(String fieldName) {
        String name = fieldName.replaceAll("\\s+","");
        return String.join("_", name.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")).toUpperCase();
    }
    
    public static String getPreparedFieldEnum(String domainName, String fieldName) {
        return getPreparedName(domainName).concat(".DomainMap.").concat(getFieldEnum(fieldName));
    }
    
    public static String getPreparedDomainMap(String domainName) {
        return getPreparedName(domainName).concat(".DomainMap.values()");
    }
	
}

