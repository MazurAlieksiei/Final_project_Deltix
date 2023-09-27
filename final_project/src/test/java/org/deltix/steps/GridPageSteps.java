package org.deltix.steps;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.deltix.enums.Order;
import org.deltix.pages.GridPage;
import org.deltix.utility.Browser;
import org.deltix.utility.MappingUtils;
import org.deltix.utility.StringUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridPageSteps {

    private static Logger log = Logger.getLogger(GridPageSteps.class);
    private static GridPage gridPage = new GridPage();
    private static final int WAIT_TIMEOUT = 5;

    @Step("Select order on Grid&chart page.")
    public static void selectOrder(int rowIndex, String columnName) {
        log.info("Select order on Grid&chart page.");
        gridPage.clickOnCellByRowNumberAndCellId(rowIndex, MappingUtils.mapHeaderTitleToCellId(columnName));
        waitUntilGridChartUpdated();
        Assert.assertTrue(gridPage.isOrdersGridChartRootDisplayed(), "Orders grid chart root on 'Grid & chart' page is not displayed.");
    }

    @Step("Click on'Lines+' button on Grid&chart page.")
    public static void clickOnLinesButton() {
        log.info("Click on'Lines+' button on Grid&chart page.");
        gridPage.clickOnChartLinesSettingsButton();
    }

    @Step("Turn on chart lines on Grid&chart page.")
    public static void turnOnChartLines(List<String> toggleNames) {
        log.info("Turn on chart lines on Grid&chart page.");
        for (String togglesName : toggleNames) {
            gridPage.turnOnSections(togglesName);
            Assert.assertTrue(gridPage.isToggleOn(togglesName), "Toggle " + togglesName + " not turned on after click.");
        }
    }

    @Step("Sort order grid by cell name and required order.")
    public static void sortOrderGridByCellNameAndOrder(String headerCellName, Order order) {
        log.info("Sort order grid by cell name and required order.");
        gridPage.sortByHeaderCellNameAndOrder(headerCellName, order);
        waitUntilGridUpdated();
    }

    @Step("Wait until grid chart updated.")
    public static void waitUntilGridChartUpdated() {
        log.info("Wait until grid chart updated.");
        new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(gridPage.getChartLoaderLocator()));

    }

    @Step("Wait until grid updated.")
    public static void waitUntilGridUpdated() {
        log.info("Wait until grid updated.");
        new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(gridPage.getGridLoaderLocator()));

    }

    @Step("Check order Exec price value.")
    public static void checkOrderExecPriceValue(int rowIndex) {
        log.info("Check order Exec price value.");
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

    @Step("Check order Mid price value.")
    public static void checkOrderMidPriceValue() {
        log.info("Check order Mid price value.");
        String toolTipText = gridPage.getToolTipMidText();
        ArrayList<String> splittedString = new ArrayList<>(Arrays.asList(toolTipText.split("\n")));

        String sectionText = gridPage.getInteractiveLegendSectionText("Mid price");

        Assert.assertTrue(splittedString.get(1).equals(sectionText), "Value of 'Mid price' aro not equals.");
    }

    @Step("Check columns displayed on grid.")
    public static void checkColumnDisplayedOnGrid(String columnName, boolean isDisplayed) {
        log.info("Check columns displayed on grid.");
        Assert.assertEquals(gridPage.isColumnDisplayed(columnName), isDisplayed);
    }

    @Step("Turn off filter by section title with tool panel.")
    public static void turnOffFilterByTitleWithToolPanel(String filterTitle) {
        log.info("Turn off filter by section title with tool panel.");
        if (!gridPage.getFilterForm().isFilterToolPanelDisplayed()) {
            gridPage.getFilterForm().openFilter();
        }
        Assert.assertTrue(gridPage.getFilterForm().isFilterToolPanelDisplayed(), "Filter tool panel is not displayed.");
        gridPage.getFilterForm().turnFilterOffByTitle(filterTitle);
    }

    @Step("Turn on filter by section title with tool panel.")
    public static void turnOnFilterByTitleWithToolPanel(String filterTitle) {
        log.info("Turn on filter by section title with tool panel.");
        if (!gridPage.getFilterForm().isFilterToolPanelDisplayed()) {
            gridPage.getFilterForm().openFilter();
        }
        Assert.assertTrue(gridPage.getFilterForm().isFilterToolPanelDisplayed(), "Filter tool panel is not displayed.");
        gridPage.getFilterForm().turnFilterOnByTitle(filterTitle);
    }

    @Step("Open column filter menu.")
    public static void openColumnFilterMenu(String cellName) {
        log.info("Open column filter menu.");
        gridPage.moveToGridCellByName(cellName);
        gridPage.openFilterForColumnByColumnName(cellName);
        Assert.assertTrue(gridPage.isFilterMenuForColumnDisplayed(), "Filter menu for column is not displayed.");
    }

    @Step("Choose third submenu from column filter menu.")
    public static void chooseThirdSubMenu() {
        log.info("Choose third submenu from column filter menu.");
        gridPage.clickOnAgMenuIconColumns();
    }

    @Step("Turn off filter by section title with column menu.")
    public static void turnOffFilterByTitleWithColumnMenu(String cellName, String filterTitle) {
        log.info("Turn off filter by section title with column menu.");
        if (!gridPage.getFilterFormForColumn().isFilterToolPanelDisplayed()) {
            openColumnFilterMenu(cellName);
            chooseThirdSubMenu();
        }
        gridPage.getFilterFormForColumn().turnFilterOffByTitle(filterTitle);
    }

    @Step("Turn on filter by section title with column menu.")
    public static void turnOnfFilterByTitleWithColumnMenu(String cellName, String filterTitle) {
        log.info("Turn on filter by section title with column menu.");
        if (!gridPage.getFilterFormForColumn().isFilterToolPanelDisplayed()) {
            openColumnFilterMenu(cellName);
            chooseThirdSubMenu();
        }
        gridPage.getFilterFormForColumn().turnFilterOnByTitle(filterTitle);
    }

    @Step("Get N cell values for column by title.")
    public static List<String> getNCellValuesForColumnTitle(String columnTitle, int cellCount) {
        log.info("Get N cell values from column " + columnTitle);
        List<String> cellValues = new ArrayList<>();
        for (int i = 0; i < cellCount; i++) {
            cellValues.add(gridPage.getCellValueByRowNumberAndCellId(i, MappingUtils.mapHeaderTitleToCellId(columnTitle)));
        }
        return cellValues;
    }

    @Step("Click on header column")
    public static void clickOnHeaderColumn(String headerColumnName) {
        log.info("Click on header column" + headerColumnName);
        gridPage.clickOnHeaderColumnByTitle(headerColumnName);
    }

    @Step("Check if colors of Avg in different sections matches.")
    public static void checkIfColorsOfAvgMatch() {
        log.info("Check if colors of Avg in different sections matches.");
        String hexColor = StringUtils.getHexColorValueFromRGB(gridPage.getInteractiveLegendSectionAvgColor());
        Assert.assertEquals(gridPage.getToolTipAvgColor(), hexColor, "Color of element doesn't match.");
    }

    @Step("Check if colors of Mid in different sections matches.")
    public static void checkIfColorsOfMidMatch() {
        log.info("Check if colors of Mid in different sections matches.");
        String hexColor = StringUtils.getHexColorValueFromRGB(gridPage.getInteractiveLegendSectionMidColor());
        Assert.assertEquals(gridPage.getToolTipMidColor(), hexColor, "Color of element doesn't match.");
    }
}

