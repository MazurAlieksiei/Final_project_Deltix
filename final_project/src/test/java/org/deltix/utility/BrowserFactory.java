package org.deltix.utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {

    static {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
    }

    private static Logger log = Logger.getLogger(BrowserFactory.class);
    private static Properties properties;

    public static WebDriver createDriver() {
        log.info("Create driver.");
        WebDriver driver;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/project.properties"));
        } catch (IOException e) {
            log.error("Cant read properties from file.");
            throw new RuntimeException(e);
        }
        switch (properties.getProperty("browser")) {
            case "chrome":

                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                log.error("Browser Not Supported: " + System.getProperty("browser"));
                throw new IllegalStateException("Browser Not Supported: " + System.getProperty("browser"));
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        return driver;
    }
}
