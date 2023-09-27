package org.deltix.pages;

import org.apache.log4j.Logger;
import org.deltix.elements.FilterForm;
import org.deltix.enums.Order;
import org.deltix.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GridPage {

    private static Logger log = Logger.getLogger(GridPage.class);
    private final By ordersGridRootLocator = By.xpath("//app-orders-grid");

    private final By ordersGridChartRootLocator = By.xpath("//app-orders-grid-chart");

    private final String gridCellPattern = "//*[@role = 'row' and @row-index='%s']/*[@role = 'gridcell' and @col-id = '%s']";

    private final By chartLinesSettingsButton = By.xpath("//app-chart-lines-panel");

    private final String switchLocatorPattern = "//app-chart-lines-panel-content//*[contains(@title, '%s')]/preceding-sibling::*//span";

    private final String headerCellPattern = "//*[@class = 'ag-header-cell-text' and contains(text(), '%s')]";

    private final By chartLoaderLocator = By.xpath("//app-orders-grid-chart//*[contains(@class , 'loading')]");

    private final By gridLoaderLocator = By.xpath("//app-orders-grid//*[contains(@class , 'loading')]");

    private final String interactiveLegendSectionPattern = "//app-chart-legend//*[contains(text(), '%s')]/following-sibling::*";
    private final By tooltipAvgLocator = By.xpath("//*[contains(@class, 'y-axis-labels')]//*[contains(@class, 'AVERAGE_EXECUTION_PRICE')]");

    private final By tooltipMidPriceLocator = By.xpath("//*[contains(@class, 'y-axis-labels')]//*[contains(@class, 'MID_PRICE')]");
    private final By orderInfoStringLocator = By.xpath("//app-chart-view//*[contains(@class, 'chart-view__info')]/*");

    private final By columnsFilterButtonLocator = By.xpath(".//button[@ref = 'eToggleButton']");

    private final By asideFilterPanelLocator = By.xpath(".//*[@class= 'ag-column-select-panel']");

    private final By filterForColumnLocator = By.xpath("./parent::*/preceding-sibling::*[@ref = 'eMenu']");

    private final By filterMenuForColumn = By.xpath("//*[@class = 'ag-menu']");

    private final By agMenuIconColumnsLocator = By.xpath(".//*[contains(@class, 'ag-icon-columns')]");

    private final By rectAvgColorLocator = By.xpath("//app-chart-legend//*[contains(@class, 'container__double-square')]");

    private FilterForm filterForm = new FilterForm(columnsFilterButtonLocator, asideFilterPanelLocator);

    private FilterForm filterFormForColumn = new FilterForm(filterMenuForColumn);

    public FilterForm getFilterFormForColumn() {
        return filterFormForColumn;
    }

    public FilterForm getFilterForm() {
        return filterForm;
    }

    public By getChartLoaderLocator() {
        return chartLoaderLocator;
    }

    public By getGridLoaderLocator() {
        return gridLoaderLocator;
    }

    public boolean isPageOpened() {
        log.info("Check if Grid&Chart page opened.");
        return Browser.getDriver().findElement(ordersGridRootLocator).isDisplayed();
    }

    public void clickOnCellByRowNumberAndCellId(int rowIndex, String cellId) {
        log.info("Click on cell by row number and cell id.");
        String cellPath = String.format(gridCellPattern, rowIndex, cellId);
        Browser.getDriver().findElement(By.xpath(cellPath)).click();
    }

    public boolean isOrdersGridChartRootDisplayed() {
        log.info("Check if orders grid displayed.");
        return Browser.getDriver().findElement(ordersGridChartRootLocator).isDisplayed();
    }

    public void clickOnChartLinesSettingsButton() {
        log.info("Click on chart 'Lines+' button.");
        Browser.getDriver().findElement(chartLinesSettingsButton).click();
    }


    public void turnOnSections(String toggleName) {
        log.info("Click on switch panel to turn on section.");
        String switchPath = String.format(switchLocatorPattern, toggleName);
        if (!isToggleOn(toggleName)) {
            Browser.getDriver().findElement(By.xpath(switchPath)).click();
        }
    }

    public boolean isToggleOn(String toggleName) {
        log.info("Check if toggle turned on.");
        String switchPath = String.format(switchLocatorPattern, toggleName);
        String color = Browser.getDriver().findElement(By.xpath(switchPath)).getCssValue("background-color");
        if (!color.equals("rgba(33, 150, 243, 1)")) {
            return false;
        }
        return true;
    }

    public void sortByHeaderCellNameAndOrder(String headerCellName, Order order) {
        log.info("Sort values by header cell name and order.");
        String cellPath = String.format(headerCellPattern, headerCellName);
        WebElement columnHeader = Browser.getDriver().findElement(By.xpath(cellPath));

        switch (order) {
            case ASC:
                new Actions(Browser.getDriver()).moveToElement(columnHeader).doubleClick().build().perform();
                break;
            case DESC:
                columnHeader.click();
                break;
        }
    }

    public String getCellValueByRowNumberAndCellId(int rowIndex, String cellId) {
        log.info("Get cell value by row number and cell id.");
        String cellPath = String.format(gridCellPattern, rowIndex, cellId);
        return Browser.getDriver().findElement(By.xpath(cellPath)).getText();
    }

    public String getOrderInfoStringText() {
        log.info("Get order info text.");
        return Browser.getDriver().findElement(orderInfoStringLocator).getText();
    }

    public String getToolTipExecText() {
        log.info("Get get tool top Exec text.");
        return Browser.getDriver().findElement(tooltipAvgLocator).getText();
    }

    public String getToolTipMidText() {
        log.info("Get get tool top Mid text.");
        return Browser.getDriver().findElement(tooltipMidPriceLocator).getText();
    }

    public String getInteractiveLegendSectionText(String sectionName) {
        log.info("Get interactive legend section text.");
        String sectionPath = String.format(interactiveLegendSectionPattern, sectionName);
        return Browser.getDriver().findElement(By.xpath(sectionPath)).getText();
    }

    public boolean isColumnDisplayed(String headerCellName) {
        log.info("Check if column " + headerCellName + " displayed." );
        String cellPath = String.format(headerCellPattern, headerCellName);

        try {
            return Browser.getDriver().findElement(By.xpath(cellPath)).isDisplayed();
        } catch (NoSuchElementException ex) {
            log.error(headerCellName + " column not displayed.");
            return false;
        }
    }

    public void moveToGridCellByName(String headerCellName) {
        log.info("Move to grid cell " + headerCellName + ".");
        String cellPath = String.format(headerCellPattern, headerCellName);
        WebElement cell = Browser.getDriver().findElement(By.xpath(cellPath));
        new Actions(Browser.getDriver()).moveToElement(cell).build().perform();
    }

    public void openFilterForColumnByColumnName(String headerCellName) {
        log.info("Open filter for column.");
        String cellPath = String.format(headerCellPattern, headerCellName);
        Browser.getDriver().findElement(By.xpath(cellPath)).findElement(filterForColumnLocator).click();
    }

    public boolean isFilterMenuForColumnDisplayed() {
        log.info("Check if filter menu for column displayed.");
        return Browser.getDriver().findElement(filterMenuForColumn).isDisplayed();
    }

    public void clickOnAgMenuIconColumns() {
        log.info("Click in ag menu icon columns.");
        Browser.getDriver().findElement(filterMenuForColumn).findElement(agMenuIconColumnsLocator).click();
    }

    public void clickOnHeaderColumnByTitle(String headerColumnName) {
        log.info("Click on header column" + headerColumnName);
        String cellPath = String.format(headerCellPattern, headerColumnName);
        Browser.getDriver().findElement(By.xpath(cellPath)).click();
    }

    public String getInteractiveLegendSectionAvgColor() {
        log.info("Get interactive legend section Avg color.");
        return Browser.getDriver().findElement(rectAvgColorLocator).getCssValue("border-color");
    }

    public String getInteractiveLegendSectionMidColor() {
        log.info("Get interactive legend section Mid color.");
        String sectionPath = String.format(interactiveLegendSectionPattern, "Mid price");
        return Browser.getDriver().findElement(By.xpath(sectionPath)).
                findElement(By.xpath("./preceding-sibling::*")).
                getCssValue("background-color");
    }

    public String getToolTipAvgColor() {
        log.info("Get tool tip Avg color.");
        return Browser.getDriver().findElement(By.cssSelector("g.dc-y-axis-label-container.dc-y-axis-label-container-AVERAGE_EXECUTION_PRICE > rect")).getAttribute("fill");
    }

    public String getToolTipMidColor() {
        log.info("Get tool tip Mid color.");
        return Browser.getDriver().findElement(By.cssSelector("g.dc-y-axis-label-container.dc-y-axis-label-container-MID_PRICE > rect")).getAttribute("fill");
    }
}
