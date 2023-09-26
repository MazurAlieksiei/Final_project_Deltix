package org.example.steps;

import org.example.pages.HistogramPage;
import org.example.utility.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HistogramPageSteps {

    private static HistogramPage histogramPage = new HistogramPage();
    private static int WAIT_TIMEOUT = 2;

    public static void checkBarContainersExistence() {
        Assert.assertTrue(!histogramPage.getBarContainers().isEmpty(), "Bars on Histogram page doesn't exist.");
    }

    public static void checkAllBarContainersAttributesExistence() {
        for (WebElement barContainer : histogramPage.getBarContainers()) {
            new Actions(Browser.getDriver()).moveToElement(barContainer, -30, -40).build().perform();

            Assert.assertTrue(histogramPage.isToolTipDisplayed(), "Bar tool tip is not displayed.");

            Assert.assertTrue(
                    histogramPage.getBorders().matches("Avg fill price: [\\[,\\(]\\d+\\.\\d+, \\d+\\.\\d+[),\\]]"),
                    "Attribute 'borders' doesn't match the expected look.");
            Assert.assertTrue(
                    histogramPage.getCount().matches("Count: \\d+"),
                    "Attribute 'count' doesn't match the expected look.");
        }
    }

    /* После первого прохода и клика, обновляется страница и ранее найденный элемент
    удаляется из структуры DOM (Объектная Модель Документа). Элемент загружается заново после того, как я ранее на
    него ссылался уже, поэтому ссылка указывала на несуществующий объект.*/

    public static void checkBarDetailsGridOpenedAfterClickOnEachBar() {
        /*Необходимо сперва кликнуть на один из столбиков, чтобы загрузилась вся страница.
        Иначе в цикле ниже получим StateElementReferenceException после клика на первый столбик
        и обращения к следующему элементу*/
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

    public static void waitUntilHistogramLoaded() {
        new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(histogramPage.getLoaderLocator()));
    }
}
