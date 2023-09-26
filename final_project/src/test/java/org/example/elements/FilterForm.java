package org.example.elements;

import org.example.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class FilterForm {

    private By filterButtonLocator;
    private final By filterToolPanelLocator; //By.xpath("//app-fast-filter-panel");
    private final String CHECK_BOX_PATTERN = ".//*[contains(@title, '%s')]/preceding-sibling::*";

    public FilterForm(By filterButtonLocator, By filterPanelLocator) {
        this.filterButtonLocator = filterButtonLocator;
        filterToolPanelLocator = filterPanelLocator;
    }

    public FilterForm(By filterPanelLocator) {
        filterToolPanelLocator = filterPanelLocator;
    }

    public void openFilter() {
        Browser.getDriver().findElement(filterButtonLocator).click();
    }

    public boolean isFilterToolPanelDisplayed() {
        try {
            return Browser.getDriver().findElement(filterToolPanelLocator).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void turnFilterOnByTitle(String filterName) {
        String filterPath = String.format(CHECK_BOX_PATTERN, filterName);
        WebElement baseElement = Browser.getDriver().findElement(filterToolPanelLocator).findElement(By.xpath(filterPath));

        try {
            baseElement.findElement(By.xpath(".//*[@class = 'ag-checkbox-unchecked']"));
            baseElement.click();
        } catch (NoSuchElementException ex) {
            ex.getMessage();
        }
    }

    public void turnFilterOffByTitle(String filterName) {
        String filterPath = String.format(CHECK_BOX_PATTERN, filterName);
        WebElement baseElement = Browser.getDriver().findElement(filterToolPanelLocator).findElement(By.xpath(filterPath));

        try {
            baseElement.findElement(By.xpath(".//*[@class = 'ag-checkbox-checked']"));
            baseElement.click();
        } catch (NoSuchElementException ex) {
            ex.getMessage();
        }
    }
}
