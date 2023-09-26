package org.example.utility;


import org.openqa.selenium.WebDriver;


public class Browser {

    private static WebDriver driver;

    private Browser() {
    }

    public static void initDriver() {
        driver = BrowserFactory.createDriver();
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void close() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
