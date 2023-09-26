package org.example.pages;

import org.example.utility.Browser;
import org.openqa.selenium.By;

public class LoginPage {

    private final By loginPageLocator = By.xpath("//*[contains(@class, 'login-form')]");
    private final By userNameLocator = By.xpath("//input[@formcontrolname='username']");
    private final By passwordLocator = By.xpath("//input[@formcontrolname='password']");
    private final By singInButtonLocator = By.xpath("//button");

    public void enterName(String login) {
        Browser.getDriver().findElement(userNameLocator).sendKeys(login);
    }

    public void enterPassword(String password) {
        Browser.getDriver().findElement(passwordLocator).sendKeys(password);
    }

    public void clickSingInButton() {
        Browser.getDriver().findElement(singInButtonLocator).click();
    }

    public boolean isOpened() {
        return Browser.getDriver().findElement(loginPageLocator).isDisplayed();
    }
}
