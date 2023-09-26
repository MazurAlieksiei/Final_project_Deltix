package org.example.pages;

import org.example.elements.FilterForm;
import org.example.enums.Order;
import org.example.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GridPage {

    private final By ordersGridRootLocator = By.xpath("//app-orders-grid");

    private final By ordersGridChartRootLocator = By.xpath("//app-orders-grid-chart");

    private final String GRID_CELL_PATTERN = "//*[@role = 'row' and @row-index='%s']/*[@role = 'gridcell' and @col-id = '%s']";

    private final By chartLinesSettingsButton = By.xpath("//app-chart-lines-panel");

    private final String SWITCH_LOCATOR_PATTERN = "//app-chart-lines-panel-content//*[contains(@title, '%s')]/preceding-sibling::*//span";

    private final String HEADER_CELL_PATTERN = "//*[@class = 'ag-header-cell-text' and contains(text(), '%s')]";

    private final By chartLoaderLocator = By.xpath("//app-orders-grid-chart//*[contains(@class , 'loading')]");

    private final By gridLoaderLocator = By.xpath("//app-orders-grid//*[contains(@class , 'loading')]");

    private final String INTERACTIVE_LEGEND_SECTION_PATTERN = "//app-chart-legend//*[contains(text(), '%s')]/following-sibling::*";
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
        return Browser.getDriver().findElement(ordersGridRootLocator).isDisplayed();
    }

    public void clickOnCellByRowNumberAndCellId(int rowIndex, String cellId) {
        String cellPath = String.format(GRID_CELL_PATTERN, rowIndex, cellId);
        Browser.getDriver().findElement(By.xpath(cellPath)).click();
    }

    public boolean isOrdersGridChartRootDisplayed() {
        return Browser.getDriver().findElement(ordersGridChartRootLocator).isDisplayed();
    }

    //rgba(221, 221, 221, 1) off
    //rgba(33, 150, 243, 1) on
    public void clickOnChartLinesSettingsButton() {
        Browser.getDriver().findElement(chartLinesSettingsButton).click();
    }


    public void turnOnSections(String toggleName) {
        String switchPath = String.format(SWITCH_LOCATOR_PATTERN, toggleName);
        if (!isToggleOn(toggleName)) {
            Browser.getDriver().findElement(By.xpath(switchPath)).click();
        }
    }

    public boolean isToggleOn(String toggleName) {
        String switchPath = String.format(SWITCH_LOCATOR_PATTERN, toggleName);
        String color = Browser.getDriver().findElement(By.xpath(switchPath)).getCssValue("background-color");
        if (!color.equals("rgba(33, 150, 243, 1)")) {
            return false;
        }
        return true;
    }

    public void sortByHeaderCellNameAndOrder(String headerCellName, Order order) {
        String cellPath = String.format(HEADER_CELL_PATTERN, headerCellName);
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
        String cellPath = String.format(GRID_CELL_PATTERN, rowIndex, cellId);
        return Browser.getDriver().findElement(By.xpath(cellPath)).getText();
    }

    public String getOrderInfoStringText() {
        return Browser.getDriver().findElement(orderInfoStringLocator).getText();
    }

    public String getToolTipExecText() {
        return Browser.getDriver().findElement(tooltipAvgLocator).getText();
    }

    public String getToolTipMidText() {
        return Browser.getDriver().findElement(tooltipMidPriceLocator).getText();
    }

    public String getInteractiveLegendSectionText(String sectionName) {
        String sectionPath = String.format(INTERACTIVE_LEGEND_SECTION_PATTERN, sectionName);
        return Browser.getDriver().findElement(By.xpath(sectionPath)).getText();
    }

    public boolean isColumnDisplayed(String headerCellName) {
        String cellPath = String.format(HEADER_CELL_PATTERN, headerCellName);

        try {
            return Browser.getDriver().findElement(By.xpath(cellPath)).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void moveToGridCellByName(String headerCellName) {
        String cellPath = String.format(HEADER_CELL_PATTERN, headerCellName);
        WebElement cell = Browser.getDriver().findElement(By.xpath(cellPath));
        new Actions(Browser.getDriver()).moveToElement(cell).build().perform();
    }

    public void openFilterForColumnByColumnName(String headerCellName) {
        String cellPath = String.format(HEADER_CELL_PATTERN, headerCellName);
        Browser.getDriver().findElement(By.xpath(cellPath)).findElement(filterForColumnLocator).click();
    }

    public boolean isFilterMenuForColumnDisplayed() {
        return Browser.getDriver().findElement(filterMenuForColumn).isDisplayed();
    }

    public void clickOnAgMenuIconColumns() {
        Browser.getDriver().findElement(filterMenuForColumn).findElement(agMenuIconColumnsLocator).click();
    }

    public void clickOnHeaderColumnByTitle(String headerColumnName) {
        String cellPath = String.format(HEADER_CELL_PATTERN, headerColumnName);
        Browser.getDriver().findElement(By.xpath(cellPath)).click();
    }

    //border-color: rgb(128, 128, 128); серый avg
    //background-color: rgb(52, 73, 94); синий mid
    public String getInteractiveLegendSectionAvgColor() {
        return Browser.getDriver().findElement(rectAvgColorLocator).getCssValue("border-color");
    }

    public String getInteractiveLegendSectionMidColor() {
        String sectionPath = String.format(INTERACTIVE_LEGEND_SECTION_PATTERN, "Mid price");
        return Browser.getDriver().findElement(By.xpath(sectionPath)).
                findElement(By.xpath("./preceding-sibling::*")).
                getCssValue("background-color");
    }

    public String getToolTipAvgColor() {
        return Browser.getDriver().findElement(By.cssSelector("g.dc-y-axis-label-container.dc-y-axis-label-container-AVERAGE_EXECUTION_PRICE > rect")).getAttribute("fill");
    }

    public String getToolTipMidColor() {
        return Browser.getDriver().findElement(By.cssSelector("g.dc-y-axis-label-container.dc-y-axis-label-container-MID_PRICE > rect")).getAttribute("fill");
    }
}
