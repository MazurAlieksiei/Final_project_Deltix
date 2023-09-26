package org.example.pages;

import org.example.elements.Dropdown;
import org.example.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ScatterplotPage {

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
        return Browser.getDriver().findElement(scatterPlotLocator).isDisplayed();
    }

    public void changeXAttribute(String valueName) {
        Dropdown.selectDropdownValue(xAttributeDropdownLocator, valueName);
    }

    public void changeYAttribute(String valueName) {
        Dropdown.selectDropdownValue(yAttributeDropdownLocator, valueName);
    }

    public String getXAxeLabelText() {
        return Browser.getDriver().findElement(scatterPlotXLabelLocator).getText();
    }

    public String getYAxeLabelText() {
        return Browser.getDriver().findElement(scatterPlotYLabelLocator).getText();
    }

    public List<String> getXAxeTicksValues() {
        List<String> values = new ArrayList<>();
        List<WebElement> axeTicks = Browser.getDriver().findElements(xAxeTicksLocator);
        for (WebElement axeTick : axeTicks) {
            values.add(axeTick.getText());
        }
        return values;
    }

    public List<String> getYAxeTicksValues() {
        List<String> values = new ArrayList<>();
        List<WebElement> axeTicks = Browser.getDriver().findElements(yAxeTicksLocator);
        for (WebElement axeTick : axeTicks) {
            values.add(axeTick.getText());
        }
        return values;
    }

    public boolean isLoaderDisplayed() {
        return Browser.getDriver().findElement(loaderLocator).isDisplayed();
    }
}
