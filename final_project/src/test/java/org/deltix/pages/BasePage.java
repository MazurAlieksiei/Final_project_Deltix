package org.deltix.pages;

import org.deltix.elements.FilterForm;
import org.openqa.selenium.By;

public class BasePage {

    private final By filterPanelLocator = By.xpath("//app-fast-filter-panel");
    private final By filterButtonLocator = By.xpath("//button[@title = 'Filters']");
    private final FilterForm filterForm = new FilterForm(filterButtonLocator, filterPanelLocator);

    public FilterForm getFilterForm() {
        return filterForm;
    }
}
