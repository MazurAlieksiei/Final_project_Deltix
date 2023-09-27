package org.deltix.pages;

import org.apache.log4j.Logger;
import org.deltix.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HistogramPage {

    private static Logger log = Logger.getLogger(HistogramPage.class);
    private final By histogramLocator = By.xpath("//*[@class='histogram']");
    private final By barContainerLocator = By.xpath("//*[@class = 'bar_container']");
    private final By toolTipLocator = By.xpath("//*[@class='tooltip']");
    private final By toolTipBordersLocator = By.xpath("./div[1]");
    private final By toolTipCountLocator = By.xpath("./div[2]");
    private final By barDetailsGridLocator = By.xpath("//app-selection-grid");
    private final By loaderLocator = By.xpath("//app-histogram-view//*[contains(@class , 'loading')]");

    public boolean isPageOpened() {
        log.info("Check if page Histogram opened.");
        return Browser.getDriver().findElement(histogramLocator).isDisplayed();
    }

    public List<WebElement> getBarContainers() {
        log.info("Get bars from Grid&Chart page.");
        return Browser.getDriver().findElements(barContainerLocator);
    }

    public String getBorders() {
        log.info("Get borders from bars on Grid&Chart page.");
        return Browser.getDriver().findElement(toolTipLocator).findElement(toolTipBordersLocator).getText();
    }

    public String getCount() {
        log.info("Get count from bars on Grid&Chart page.");
        return Browser.getDriver().findElement(toolTipLocator).findElement(toolTipCountLocator).getText();
    }

    public boolean isToolTipDisplayed() {
        log.info("Check if tool tip displayed on Grid&Chart page.");
        return Browser.getDriver().findElement(toolTipLocator).isDisplayed();
    }

    public boolean isBarDetailsGridDisplayed() {
        log.info("Check if bar details displayed on Grid&Chart page.");
        return Browser.getDriver().findElement(barDetailsGridLocator).isDisplayed();
    }

    public By getLoaderLocator() {
        return loaderLocator;
    }
}
