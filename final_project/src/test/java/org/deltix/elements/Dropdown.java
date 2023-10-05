package org.deltix.elements;

import org.apache.log4j.Logger;
import org.deltix.utility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;

public class Dropdown {

    private static Logger log = Logger.getLogger(Dropdown.class);
    private static final String dropdownValuePattern = "//*[contains(@class, 'dropdown-item') and @title = '%s']";

    public static void selectDropdownValue(By locator, String valueToSelect) {
        log.info("Select dropdown menu value" + valueToSelect + ".");
        Browser.getDriver().findElement(locator).click();

        String valuePath = String.format(dropdownValuePattern, valueToSelect);
        try {
            Browser.getDriver().findElement(By.xpath(valuePath)).click();
        } catch (NotFoundException ex) {
            log.error("No such option to select " + valueToSelect);
            throw new NotFoundException("No such option to select " + valueToSelect + ".");
        }
    }
}
