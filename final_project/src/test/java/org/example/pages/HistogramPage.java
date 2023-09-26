package org.example.pages;

import org.example.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HistogramPage {

    private final By histogramLocator = By.xpath("//*[@class='histogram']");
    private By intervalCountDropdownLocator = By.xpath("//*[contains(@name, 'intervalCountAutocomplete')]");
    private final By barContainerLocator = By.xpath("//*[@class = 'bar_container']");
    private final By toolTipLocator = By.xpath("//*[@class='tooltip']");
    private final By toolTipBordersLocator = By.xpath("./div[1]");
    private final By toolTipCountLocator = By.xpath("./div[2]");
    private final By barDetailsGridLocator = By.xpath("//app-selection-grid");
    private final By loaderLocator = By.xpath("//app-histogram-view//*[contains(@class , 'loading')]");

    public boolean isPageOpened() {
        return Browser.getDriver().findElement(histogramLocator).isDisplayed();
    }

    public List<WebElement> getBarContainers() {
        return Browser.getDriver().findElements(barContainerLocator);
    }

    public String getBorders() {
        return Browser.getDriver().findElement(toolTipLocator).findElement(toolTipBordersLocator).getText();
    }

    public String getCount() {
        return Browser.getDriver().findElement(toolTipLocator).findElement(toolTipCountLocator).getText();
    }

    public boolean isToolTipDisplayed() {
        return Browser.getDriver().findElement(toolTipLocator).isDisplayed();
    }

    public boolean isBarDetailsGridDisplayed() {
        return Browser.getDriver().findElement(barDetailsGridLocator).isDisplayed();
    }

    public By getLoaderLocator() {
        return loaderLocator;
    }
}
