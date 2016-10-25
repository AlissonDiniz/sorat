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
public class TableHelper extends AbstractHelper {

    public String generateValidateShowScreenByClickFieldTestCase(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateShowScreenByClick").append(GeneratorHelper.getPreparedName(field.name)).append("() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableColumn(1, ").append(field.orderInTable).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultViews.SHOW.getTitle(), getMainBot().getScreenTitle());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidateEditScreenByClickEditButtonTestCase(Domain domain) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateEditScreenByClickEditButtonTestCase() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnLineEditButton(1);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultViews.EDIT.getTitle(), getMainBot().getScreenTitle());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidateNextButtonOnPaginationTestCase(Domain domain){
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateNextButtonOnPagination() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String comparatorValue = getTableBot().getTableColumnValue(1, 1);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().getPagination().clickOnNextPage();");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(false, getTableBot().getPagination().pageButtonIsActive(1));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getTableBot().getPagination().pageButtonIsActive(2));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertNotEquals(comparatorValue, getTableBot().getTableColumnValue(1, 1));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidatePreviousButtonOnPaginationTestCase(Domain domain) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validatePreviousButtonOnPagination() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().getPagination().clickOnNextPage();");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String comparatorValue = getTableBot().getTableColumnValue(1, 1);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().getPagination().clickOnPreviousPage();");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getTableBot().getPagination().pageButtonIsActive(1));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(false, getTableBot().getPagination().pageButtonIsActive(2));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertNotEquals(comparatorValue, getTableBot().getTableColumnValue(1, 1));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidateIfNextButtonIsNotActiveTestCase(Domain domain) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateIfNextButtonIsNotActive() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().getPagination().goToLastPage();");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(false, getTableBot().getPagination().nextPageButtonIsActive());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidateIfPreviousButtonIsNotActiveTestCase(Domain domain) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateIfPreviousButtonIsNotActive() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(false, getTableBot().getPagination().previousPageButtonIsActive());");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidateNavigationByPageNumber(Domain domain) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateNavigationByPageNumber() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String comparatorValue = getTableBot().getTableColumnValue(1, 1);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().getPagination().clickOnPageButton(2);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(false, getTableBot().getPagination().pageButtonIsActive(1));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getTableBot().getPagination().pageButtonIsActive(2));");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertNotEquals(comparatorValue, getTableBot().getTableColumnValue(1, 1));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidateColumnSortAscTestCase(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validate").append(GeneratorHelper.getPreparedName(field.name)).append("ColumnSortASC() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableHeadColumn(").append(field.orderInTable).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableHeadColumn(").append(field.orderInTable).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertNotEquals(true, getTableBot().isColumnOrderedASC(").append(field.orderInTable).append("));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidateColumnSortDescTestCase(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validate").append(GeneratorHelper.getPreparedName(field.name)).append("ColumnSortDESC() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getTableBot().clickOnTableHeadColumn(").append(field.orderInTable).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getTableBot().isColumnOrderedDESC(").append(field.orderInTable).append("));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    public String generateValidateSearchByFieldValueTestCase(Domain domain, DomainField field) {
        StringBuilder builder = new StringBuilder();
        builder.append("    @Test");
        builder.append(System.getProperty("line.separator"));
        builder.append("    public void validateSearchBy").append(GeneratorHelper.getPreparedName(field.name)).append("FieldValue() {");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMenuBot().goTo(").append(domain.menu).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.CREATE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Map<String, String> map = instance.createFieldsMap(").append(GeneratorHelper.getPreparedDomainMap(domain.name)).append(");");
        builder.append(System.getProperty("line.separator"));
        builder.append("        String comparatorValue = map.get(").append(GeneratorHelper.getPreparedFieldEnum(domain.name, field.name)).append(".getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFormBot().fillForm(map);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.SAVE.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().click(").append(GeneratorHelper.getPreparedName(domain.name)).append(".DefaultButtons.LIST.getId());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getFilterBot().performSearch(comparatorValue);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        getMainBot().performWait(500);");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(true, getTableBot().hasRegisters());");
        builder.append(System.getProperty("line.separator"));
        builder.append("        Assert.assertEquals(comparatorValue, getTableBot().getTableColumnValue(1, ").append(field.orderInTable).append("));");
        builder.append(System.getProperty("line.separator"));
        builder.append("    }");
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
}
