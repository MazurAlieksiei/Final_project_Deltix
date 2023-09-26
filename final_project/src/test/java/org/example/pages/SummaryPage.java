package org.example.pages;

import org.example.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class SummaryPage {

    private final By summaryDashboardLocator = By.xpath("//app-summary-dashboard");
    private final By algoPerformanceTableBaseLocator = By.xpath("//*[contains(@class, 'app-title') and text() = 'Algo Performance']/ancestor::app-widget");
    private final By settingsButtonLocator = By.xpath("//*[@title = 'Settings']");
    private final By gridRowLocator = By.xpath(".//*[@class = 'simple-table__row simple-table__row_grid-template-columns']");
    private final By benchmarkSelectorLocator = By.xpath("//*[contains(@class, 'benchmark-selection')]");
    private final String TOOLBAR_ITEM_PATTERN = "//*[contains(@class, 'app-title') and contains(text(), '%s')]";

    public boolean isSummaryDashboardDisplayed() {
        return Browser.getDriver().findElement(summaryDashboardLocator).isDisplayed();
    }

    public boolean isToolBarElementDisplayed(String elementName) {
        return getToolBarElement(elementName).isDisplayed();
    }

    public boolean isBenchmarkSelectorDisplayed() {
        return Browser.getDriver().findElement(benchmarkSelectorLocator).isDisplayed();
    }

    public boolean isSettingsButtonDisplayed() {
        return Browser.getDriver().findElement(settingsButtonLocator).isDisplayed();
    }

    public void clickOnToolBarElement(String elementName) {
        getToolBarElement(elementName).click();
    }

    private WebElement getToolBarElement(String elementName) {
        String toolBarElementPath = String.format(TOOLBAR_ITEM_PATTERN, elementName);
        return Browser.getDriver().findElement(By.xpath(toolBarElementPath));
    }

    public List<WebElement> getRows() {
        return Browser.getDriver().findElement(algoPerformanceTableBaseLocator).findElements(gridRowLocator);
    }

    public List<String> getCellValuesForRow(WebElement row) {
        List<String> cellValues = new ArrayList<>();
        List<WebElement> cells = row.findElements(By.xpath(".//*[contains(@class, 'simple-table__row-value')]"));

        for (WebElement cell : cells) {
            cellValues.add(cell.getText());
        }

        return cellValues;
    }
}
