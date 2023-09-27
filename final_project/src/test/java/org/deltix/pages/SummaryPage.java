package org.deltix.pages;

import org.apache.log4j.Logger;
import org.deltix.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class SummaryPage {

    private static Logger log = Logger.getLogger(SummaryPage.class);
    private final By summaryDashboardLocator = By.xpath("//app-summary-dashboard");
    private final By algoPerformanceTableBaseLocator = By.xpath("//*[contains(@class, 'app-title') and text() = 'Algo Performance']/ancestor::app-widget");
    private final By settingsButtonLocator = By.xpath("//*[@title = 'Settings']");
    private final By gridRowLocator = By.xpath(".//*[@class = 'simple-table__row simple-table__row_grid-template-columns']");
    private final By benchmarkSelectorLocator = By.xpath("//*[contains(@class, 'benchmark-selection')]");
    private final String toolbarItemPattern = "//*[contains(@class, 'app-title') and contains(text(), '%s')]";

    public boolean isSummaryDashboardDisplayed() {
        log.info("Check if Summary dashboard displayed.");
        return Browser.getDriver().findElement(summaryDashboardLocator).isDisplayed();
    }

    public boolean isToolBarElementDisplayed(String elementName) {
        log.info("Check if tool bar element displayed.");
        return getToolBarElement(elementName).isDisplayed();
    }

    public boolean isBenchmarkSelectorDisplayed() {
        log.info("Check if benchmark selector displayed.");
        return Browser.getDriver().findElement(benchmarkSelectorLocator).isDisplayed();
    }

    public boolean isSettingsButtonDisplayed() {
        log.info("Check if settings button displayed.");
        return Browser.getDriver().findElement(settingsButtonLocator).isDisplayed();
    }

    public void clickOnToolBarElement(String elementName) {
        log.info("Click on tool bar element on Summary page.");
        getToolBarElement(elementName).click();
    }

    private WebElement getToolBarElement(String elementName) {
        log.info("Get tool bar element.");
        String toolBarElementPath = String.format(toolbarItemPattern, elementName);
        return Browser.getDriver().findElement(By.xpath(toolBarElementPath));
    }

    public List<WebElement> getRows() {
        log.info("Get rows on algo performance table.");
        return Browser.getDriver().findElement(algoPerformanceTableBaseLocator).findElements(gridRowLocator);
    }

    public List<String> getCellValuesForRow(WebElement row) {
        log.info("Get get cell values for row.");
        List<String> cellValues = new ArrayList<>();
        List<WebElement> cells = row.findElements(By.xpath(".//*[contains(@class, 'simple-table__row-value')]"));

        for (WebElement cell : cells) {
            cellValues.add(cell.getText());
        }

        return cellValues;
    }
}
