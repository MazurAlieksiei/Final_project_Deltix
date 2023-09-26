package org.example.steps;

import org.example.pages.ScatterplotPage;
import org.example.utility.Browser;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class ScatterplotPageSteps {

    private static ScatterplotPage scatterplotPage = new ScatterplotPage();
    private static int WAIT_TIMEOUT = 2;

    public static void changeXAttributeValue(String valueName) {
        scatterplotPage.changeXAttribute(valueName);
    }

    public static void checkXAxeDetailsChangedTo(String expectedAxeName, List<String> expectedAxeTicks) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                scatterplotPage.getXAxeLabelText().equals(expectedAxeName),
                "Name of the X-axis not changed to " + expectedAxeName);

        softAssert.assertEquals(scatterplotPage.getXAxeTicksValues(), expectedAxeTicks, "Values of axes (X) are not updated");

        softAssert.assertAll();
    }

    public static void changeYAttributeValue(String valueName) {
        scatterplotPage.changeYAttribute(valueName);
    }

    public static void checkYAxeDetailsChangedTo(String expectedAxeName, List<String> expectedAxeTicks) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                scatterplotPage.getYAxeLabelText().equals(expectedAxeName),
                "Name of the Y-axis not changed to " + expectedAxeName);

        softAssert.assertEquals(scatterplotPage.getYAxeTicksValues(), expectedAxeTicks, "Values of axes (Y) are not updated");

        softAssert.assertAll();
    }

    public static void checkScatterPlotLoaderDisplayed() {
        Assert.assertTrue(scatterplotPage.isLoaderDisplayed(), "Loader not displayed after changing attribute.");
    }

    public static void waitUntilScatterPlotUpdated() {
        new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(scatterplotPage.getLoaderLocator()));
    }
}
