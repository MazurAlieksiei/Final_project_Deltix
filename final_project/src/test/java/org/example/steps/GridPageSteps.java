package org.example.steps;

import org.example.enums.Order;
import org.example.pages.GridPage;
import org.example.utility.Browser;
import org.example.utility.CollectionUtils;
import org.example.utility.MappingUtils;
import org.example.utility.StringUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridPageSteps {

    private static GridPage gridPage = new GridPage();

    private static int WAIT_TIMEOUT = 5;

    public static void selectOrder(int rowIndex, String columnName) {
        gridPage.clickOnCellByRowNumberAndCellId(rowIndex, MappingUtils.mapHeaderTitleToCellId(columnName));
        waitUntilGridChartUpdated();
        Assert.assertTrue(gridPage.isOrdersGridChartRootDisplayed(), "Orders grid chart root on 'Grid & chart' page is not displayed.");
    }

    public static void clickOnLinesButton() {
        gridPage.clickOnChartLinesSettingsButton();
    }

    public static void turnOnChartLines(List<String> toggleNames) {
        for (String togglesName : toggleNames) {
            gridPage.turnOnSections(togglesName);
            Assert.assertTrue(gridPage.isToggleOn(togglesName), "Toggle " + togglesName + " not turned on after click.");
        }
    }

    public static void sortOrderGridByCellNameAndOrder(String headerCellName, Order order) {
        gridPage.sortByHeaderCellNameAndOrder(headerCellName, order);
        waitUntilGridUpdated();
    }

    public static void waitUntilGridChartUpdated() {
        new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(gridPage.getChartLoaderLocator()));

    }

    public static void waitUntilGridUpdated() {
        new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(gridPage.getGridLoaderLocator()));

    }

    public static void checkOrderExecPriceValue(int rowIndex) {
        String toolTipText = gridPage.getToolTipExecText();
        String orderInfoStringText = gridPage.getOrderInfoStringText();
        String cellText = gridPage.getCellValueByRowNumberAndCellId(rowIndex, "averageFillPrice");
        String sectionText = gridPage.getInteractiveLegendSectionText("Avg fill price");

        ArrayList<String> splittedString = new ArrayList<>(Arrays.asList(orderInfoStringText.split(",")));

        String orderInfoExecPriceValue = "";
        for (String string : splittedString) {
            if (string.contains("Exec price")) {
                orderInfoExecPriceValue = string.substring(string.indexOf(":") + 1);
                orderInfoExecPriceValue = orderInfoExecPriceValue.trim();
            }
        }

        Assert.assertTrue(
                toolTipText.equals(orderInfoExecPriceValue)
                        && toolTipText.equals(cellText)
                        && toolTipText.equals(sectionText)
                        && orderInfoExecPriceValue.equals(cellText)
                        && orderInfoExecPriceValue.equals(sectionText)
                        && cellText.equals(sectionText), "Value of 'Exec price' in different places are not equals.");
    }

    public static void checkOrderMidPriceValue() {
        String toolTipText = gridPage.getToolTipMidText();
        ArrayList<String> splittedString = new ArrayList<>(Arrays.asList(toolTipText.split("\n")));

        String sectionText = gridPage.getInteractiveLegendSectionText("Mid price");

        Assert.assertTrue(splittedString.get(1).equals(sectionText), "Value of 'Mid price' aro not equals.");
    }

    public static void checkColumnDisplayedOnGrid(String columnName, boolean isDisplayed) {
        Assert.assertEquals(gridPage.isColumnDisplayed(columnName), isDisplayed);
    }

    public static void turnOffFilterByTitleWithToolPanel(String filterTitle) {
        if (!gridPage.getFilterForm().isFilterToolPanelDisplayed()) {
            gridPage.getFilterForm().openFilter();
        }
        Assert.assertTrue(gridPage.getFilterForm().isFilterToolPanelDisplayed(), "Filter tool panel is not displayed.");
        gridPage.getFilterForm().turnFilterOffByTitle(filterTitle);
    }

    public static void turnOnFilterByTitleWithToolPanel(String filterTitle) {
        if (!gridPage.getFilterForm().isFilterToolPanelDisplayed()) {
            gridPage.getFilterForm().openFilter();
        }
        Assert.assertTrue(gridPage.getFilterForm().isFilterToolPanelDisplayed(), "Filter tool panel is not displayed.");
        gridPage.getFilterForm().turnFilterOnByTitle(filterTitle);
    }

    public static void openColumnFilterMenu(String cellName) {
        gridPage.moveToGridCellByName(cellName);
        gridPage.openFilterForColumnByColumnName(cellName);
        Assert.assertTrue(gridPage.isFilterMenuForColumnDisplayed(), "Filter menu for column is not displayed.");
    }

    public static void chooseThirdSubMenu() {
        gridPage.clickOnAgMenuIconColumns();
    }

    public static void turnOffFilterByTitleWithColumnMenu(String cellName, String filterTitle) {
        if (!gridPage.getFilterFormForColumn().isFilterToolPanelDisplayed()) {
            openColumnFilterMenu(cellName);
            chooseThirdSubMenu();
        }
        gridPage.getFilterFormForColumn().turnFilterOffByTitle(filterTitle);
    }

    public static void turnOnfFilterByTitleWithColumnMenu(String cellName, String filterTitle) {
        if (!gridPage.getFilterFormForColumn().isFilterToolPanelDisplayed()) {
            openColumnFilterMenu(cellName);
            chooseThirdSubMenu();
        }
        gridPage.getFilterFormForColumn().turnFilterOnByTitle(filterTitle);
    }

    public static List<String> getNCellValuesForColumnTitle(String columnTitle, int cellCount) {
        List<String> cellValues = new ArrayList<>();
        for (int i = 0; i < cellCount; i++) {
            cellValues.add(gridPage.getCellValueByRowNumberAndCellId(i, MappingUtils.mapHeaderTitleToCellId(columnTitle)));
        }
        return cellValues;
    }

    public static void clickOnHeaderColumn(String headerColumnName) {
        gridPage.clickOnHeaderColumnByTitle(headerColumnName);
    }

    public static void checkIfColorsOfAvgMatch() {
        String hexColor = StringUtils.getHexColorValueFromRGB(gridPage.getInteractiveLegendSectionAvgColor());
        Assert.assertEquals(gridPage.getToolTipAvgColor(), hexColor, "Color of element doesn't match.");
    }

    public static void checkIfColorsOfMidMatch() {
        String hexColor = StringUtils.getHexColorValueFromRGB(gridPage.getInteractiveLegendSectionMidColor());
        Assert.assertEquals(gridPage.getToolTipMidColor(), hexColor, "Color of element doesn't match.");
    }
}

