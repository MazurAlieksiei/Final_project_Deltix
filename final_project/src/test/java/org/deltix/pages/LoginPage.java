package org.deltix.pages;

import org.apache.log4j.Logger;
import org.deltix.utility.Browser;
import org.openqa.selenium.By;

public class LoginPage {

    private static Logger log = Logger.getLogger(LoginPage.class);
    private final By loginPageLocator = By.xpath("//*[contains(@class, 'login-form')]");
    private final By userNameLocator = By.xpath("//input[@formcontrolname='username']");
    private final By passwordLocator = By.xpath("//input[@formcontrolname='password']");
    private final By singInButtonLocator = By.xpath("//button");

    public void enterName(String login) {
        log.info("Enter login on Login page.");
        Browser.getDriver().findElement(userNameLocator).sendKeys(login);
    }

    public void enterPassword(String password) {
        log.info("Enter password on Login page.");
        Browser.getDriver().findElement(passwordLocator).sendKeys(password);
    }

    public void clickSingInButton() {
        log.info("Click on Sing in button on Login page.");
        Browser.getDriver().findElement(singInButtonLocator).click();
    }

    public boolean isOpened() {
        log.info("Check if Login page opened.");
        return Browser.getDriver().findElement(loginPageLocator).isDisplayed();
    }
}
