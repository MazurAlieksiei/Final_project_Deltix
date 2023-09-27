package org.deltix.steps;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.deltix.pages.HistogramPage;
import org.deltix.utility.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HistogramPageSteps {

    private static Logger log = Logger.getLogger(HistogramPageSteps.class);
    private static HistogramPage histogramPage = new HistogramPage();
    private static final int WAIT_TIMEOUT = 2;
    private static final String MATCHING_REGEX = "[\\[,\\(]\\d+\\.\\d+, \\d+\\.\\d+[),\\]]";

    @Step("Check if bar containers exists on Histogram page.")
    public static void checkBarContainersExistence() {
        log.info("Check if bar containers exists on Histogram page.");
        Assert.assertTrue(!histogramPage.getBarContainers().isEmpty(), "Bars on Histogram page doesn't exist.");
    }

    @Step("Check if all bar containers attributes exists.")
    public static void checkAllBarContainersAttributesExistence() {
        log.info("Check if all bar containers attributes exists.");
        for (WebElement barContainer : histogramPage.getBarContainers()) {
            new Actions(Browser.getDriver()).moveToElement(barContainer, -30, -40).build().perform();

            Assert.assertTrue(histogramPage.isToolTipDisplayed(), "Bar tool tip is not displayed.");

            Assert.assertTrue(
                    histogramPage.getBorders().matches("Avg fill price: " + MATCHING_REGEX),
                    "Attribute 'borders' doesn't match the expected look.");
            Assert.assertTrue(
                    histogramPage.getCount().matches("Count: \\d+"),
                    "Attribute 'count' doesn't match the expected look.");
        }
    }

        /* После первого прохода и клика, обновляется страница и ранее найденный элемент
        удаляется из структуры DOM (Объектная Модель Документа). Элемент загружается заново после того, как я ранее на
        него ссылался уже, поэтому ссылка указывала на несуществующий объект.
        Необходимо сперва кликнуть на один из столбиков, чтобы загрузилась вся страница.
        Иначе в цикле ниже получим StateElementReferenceException после клика на первый столбик
        и обращения к следующему элементу*/

    @Step("Check if bar details grid opened after click on each bar.")
    public static void checkBarDetailsGridOpenedAfterClickOnEachBar() {
        log.info("Check if bar details grid opened after click on each bar.");
        new Actions(Browser.getDriver()).moveToElement(histogramPage.getBarContainers().get(1)).
                click().build().perform();
        Assert.assertTrue(histogramPage.isBarDetailsGridDisplayed(),
                "Bar detail gris is not displayed after clicking on bar.");

        for (WebElement barContainer : histogramPage.getBarContainers()) {

            new Actions(Browser.getDriver()).moveToElement(barContainer).
                    click().build().perform();
            Assert.assertTrue(histogramPage.isBarDetailsGridDisplayed(),
                    "Bar detail gris is not displayed after clicking on bar.");
        }
    }

    @Step("Wait until Histogram loaded.")
    public static void waitUntilHistogramLoaded() {
        log.info("Wait until Histogram loaded.");
        new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(histogramPage.getLoaderLocator()));
    }
}
