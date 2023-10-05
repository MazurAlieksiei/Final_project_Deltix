package org.deltix.pages;

import org.apache.log4j.Logger;
import org.deltix.elements.Dropdown;
import org.deltix.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ScatterplotPage {

    private static Logger log = Logger.getLogger(ScatterplotPage.class);
    private final By scatterPlotLocator = By.xpath("//app-scatter-plot-view");
    private final By xAttributeDropdownLocator = By.xpath("//*[contains(text(), 'X Attribute')]//following-sibling::*");
    private final By yAttributeDropdownLocator = By.xpath("//*[contains(text(), 'Y Attribute')]//following-sibling::*");
    private final By scatterPlotXLabelLocator = By.xpath("//*[@class = 'scatter-plot-x-label']");
    private final By scatterPlotYLabelLocator = By.xpath("//*[@class = 'scatter-plot-y-label']");
    private final By xAxeTicksLocator = By.xpath("//*[@class = 'x axis']//*[@class = 'tick']");
    private final By yAxeTicksLocator = By.xpath("//*[@class = 'y axis']//*[@class = 'tick']");
    private final By loaderLocator = By.xpath("//app-scatter-plot-view//*[contains(@class , 'loading')]");

    public By getLoaderLocator() {
        return loaderLocator;
    }

    public boolean isPageOpened() {
        log.info("Check if Scatter-plot page opened.");
        return Browser.getDriver().findElement(scatterPlotLocator).isDisplayed();
    }

    public void changeXAttribute(String valueName) {
        log.info("Change X attribute.");
        Dropdown.selectDropdownValue(xAttributeDropdownLocator, valueName);
    }

    public void changeYAttribute(String valueName) {
        log.info("Change Y attribute.");
        Dropdown.selectDropdownValue(yAttributeDropdownLocator, valueName);
    }

    public String getXAxeLabelText() {
        log.info("Get X axe label text.");
        return Browser.getDriver().findElement(scatterPlotXLabelLocator).getText();
    }

    public String getYAxeLabelText() {
        log.info("Get Y axe label text.");
        return Browser.getDriver().findElement(scatterPlotYLabelLocator).getText();
    }

    public List<String> getXAxeTicksValues() {
        log.info("Get X axe ticks values.");
        List<String> values = new ArrayList<>();
        List<WebElement> axeTicks = Browser.getDriver().findElements(xAxeTicksLocator);
        for (WebElement axeTick : axeTicks) {
            values.add(axeTick.getText());
        }
        return values;
    }

    public List<String> getYAxeTicksValues() {
        log.info("Get Y axe ticks values.");
        List<String> values = new ArrayList<>();
        List<WebElement> axeTicks = Browser.getDriver().findElements(yAxeTicksLocator);
        for (WebElement axeTick : axeTicks) {
            values.add(axeTick.getText());
        }
        return values;
    }

    public boolean isLoaderDisplayed() {
        log.info("Check if loader displayed.");
        return Browser.getDriver().findElement(loaderLocator).isDisplayed();
    }
}
