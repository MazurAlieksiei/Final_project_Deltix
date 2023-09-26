package org.example.utility;

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
        System.setProperty("webdriver.gecko.driver", "c:/geckodriver.exe");
    }

    private static Properties properties;

    public static WebDriver createDriver() {
        WebDriver driver;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/project.properties"));
        } catch (IOException e) {
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
                throw new IllegalStateException("Browser Not Supported: " + System.getProperty("browser"));
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        return driver;
    }
}
