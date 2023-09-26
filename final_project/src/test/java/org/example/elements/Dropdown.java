package org.example.elements;

import org.example.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;

public class Dropdown {

    private static final String DROPDOWN_VALUE_PATTERN = "//*[contains(@class, 'dropdown-item') and @title = '%s']";

    public static void selectDropdownValue(By locator, String valueToSelect) {
        Browser.getDriver().findElement(locator).click();

        String valuePath = String.format(DROPDOWN_VALUE_PATTERN, valueToSelect);
        try {
            Browser.getDriver().findElement(By.xpath(valuePath)).click();
        } catch (NotFoundException ex) {
            throw new NotFoundException("No such option to select " + valueToSelect);
        }
    }
}
