package org.deltix.elements;

import org.apache.log4j.Logger;
import org.deltix.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class FilterForm {

    private static Logger log = Logger.getLogger(FilterForm.class);
    private By filterButtonLocator;
    private final By filterToolPanelLocator; //By.xpath("//app-fast-filter-panel");
    private final String checkBoxPattern = ".//*[contains(@title, '%s')]/preceding-sibling::*";

    public FilterForm(By filterButtonLocator, By filterPanelLocator) {
        this.filterButtonLocator = filterButtonLocator;
        filterToolPanelLocator = filterPanelLocator;
    }

    public FilterForm(By filterPanelLocator) {
        filterToolPanelLocator = filterPanelLocator;
    }

    public void openFilter() {
        log.info("Open filter form.");
        Browser.getDriver().findElement(filterButtonLocator).click();
    }

    public boolean isFilterToolPanelDisplayed() {
        log.info("Check if filter panel displayed.");
        try {
            return Browser.getDriver().findElement(filterToolPanelLocator).isDisplayed();
        } catch (NoSuchElementException ex) {
            log.error("Filter panel not displayed.");
            return false;
        }
    }

    public void turnFilterOnByTitle(String filterName) {
        log.info("Turn on filter section by title.");
        String filterPath = String.format(checkBoxPattern, filterName);
        WebElement baseElement = Browser.getDriver().findElement(filterToolPanelLocator).findElement(By.xpath(filterPath));

        try {
            baseElement.findElement(By.xpath(".//*[@class = 'ag-checkbox-unchecked']"));
            baseElement.click();
        } catch (NoSuchElementException ex) {
            log.error("Unable to locate filter section and click on it.");
            ex.getMessage();
        }
    }

    public void turnFilterOffByTitle(String filterName) {
        log.info("Turn off filter section by title.");
        String filterPath = String.format(checkBoxPattern, filterName);
        WebElement baseElement = Browser.getDriver().findElement(filterToolPanelLocator).findElement(By.xpath(filterPath));

        try {
            baseElement.findElement(By.xpath(".//*[@class = 'ag-checkbox-checked']"));
            baseElement.click();
        } catch (NoSuchElementException ex) {
            log.error("Unable to locate filter section and click on it.");
            ex.getMessage();
        }
    }
}
