package org.deltix.utility;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class Browser {

    private static Logger log = Logger.getLogger(Browser.class);
    private static WebDriver driver;

    private Browser() {
    }

    public static void initDriver() {
        log.info("Init driver.");
        driver = BrowserFactory.createDriver();
    }

    public static WebDriver getDriver() {
        log.info("Get driver.");
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void close() {
        log.info("Close  driver.");
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
