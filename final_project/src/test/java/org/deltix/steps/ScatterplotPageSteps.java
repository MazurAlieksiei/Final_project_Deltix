package org.deltix.steps;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.deltix.pages.ScatterplotPage;
import org.deltix.utility.Browser;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class ScatterplotPageSteps {

    private static Logger log = Logger.getLogger(ScatterplotPageSteps.class);
    private static ScatterplotPage scatterplotPage = new ScatterplotPage();
    private static final int WAIT_TIMEOUT = 2;

    @Step("Change X attribute value.")
    public static void changeXAttributeValue(String valueName) {
        log.info("Change X attribute value to " + valueName +".");
        scatterplotPage.changeXAttribute(valueName);
    }

    @Step("Check if X axe detail changed.")
    public static void checkXAxeDetailsChangedTo(String expectedAxeName, List<String> expectedAxeTicks) {
        log.info("Check if X axe detail changed.");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                scatterplotPage.getXAxeLabelText().equals(expectedAxeName),
                "Name of the X-axis not changed to " + expectedAxeName);

        softAssert.assertEquals(scatterplotPage.getXAxeTicksValues(), expectedAxeTicks, "Values of axes (X) are not updated");

        softAssert.assertAll();
    }

    @Step("Change Y attribute value.")
    public static void changeYAttributeValue(String valueName) {
        log.info("Change Y attribute value to " + valueName +".");
        scatterplotPage.changeYAttribute(valueName);
    }

    @Step("Check if Y axe detail changed.")
    public static void checkYAxeDetailsChangedTo(String expectedAxeName, List<String> expectedAxeTicks) {
        log.info("Check if Y axe detail changed.");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                scatterplotPage.getYAxeLabelText().equals(expectedAxeName),
                "Name of the Y-axis not changed to " + expectedAxeName);

        softAssert.assertEquals(scatterplotPage.getYAxeTicksValues(), expectedAxeTicks, "Values of axes (Y) are not updated");

        softAssert.assertAll();
    }

    @Step("Check if Scatter-plot loader displayed.")
    public static void checkScatterPlotLoaderDisplayed() {
        log.info("Check if Scatter-plot loader displayed.");
        Assert.assertTrue(scatterplotPage.isLoaderDisplayed(), "Loader not displayed after changing attribute.");
    }

    @Step("Wait until Scatter-plot page updated.")
    public static void waitUntilScatterPlotUpdated() {
        log.info("Wait until Scatter-plot page updated.");
        new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(scatterplotPage.getLoaderLocator()));
    }
}
