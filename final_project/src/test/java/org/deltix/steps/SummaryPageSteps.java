package org.deltix.steps;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.deltix.models.Item;
import org.deltix.models.PostTradeResponse;
import org.deltix.pages.SummaryPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SummaryPageSteps {

    private static Logger log = Logger.getLogger(SummaryPageSteps.class);
    private static SummaryPage summaryPage = new SummaryPage();

    @Step("Check if page components exist on Summary page.")
    public static void checkPageComponentsExistence() {
        log.info("Check if page components exist on Summary page.");
        Assert.assertTrue(summaryPage.isSummaryDashboardDisplayed(), "Page 'Summary' is not displayed.");
        Assert.assertTrue(summaryPage.isSettingsButtonDisplayed(), "Element 'Settings button' is not displayed.");
        Assert.assertTrue(summaryPage.isBenchmarkSelectorDisplayed(), "Element 'Benchmark Selector' is not displayed.");
        Assert.assertTrue(summaryPage.isToolBarElementDisplayed("Summary"), "Element 'Summary' is not displayed.");
        Assert.assertTrue(summaryPage.isToolBarElementDisplayed("Grid & chart"), "Element 'Grid & chart' is not displayed.");
        Assert.assertTrue(summaryPage.isToolBarElementDisplayed("Histogram"), "Element 'Histogram' is not displayed.");
        Assert.assertTrue(summaryPage.isToolBarElementDisplayed("Scatter-plot"), "Element 'Scatter-plot' is not displayed.");
        Assert.assertTrue(summaryPage.isToolBarElementDisplayed("Reports"), "Element 'Reports' is not displayed.");
    }

    @Step("Get algo performance gris details.")
    public static PostTradeResponse getAlgoPerformanceGridDetails() {
        log.info("Get algo performance gris details.");
        List<WebElement> rows = summaryPage.getRows();
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            List<String> test = summaryPage.getCellValuesForRow(rows.get(i));
            Item item = new Item();
            item.setOrderType(test.get(0));
            item.setAll(Integer.valueOf(test.get(1).replaceAll("\\,", "")));
            item.setNegative(Integer.valueOf(test.get(2)));
            item.setPositive(Integer.valueOf(test.get(3)));
            items.add(item);
        }

        PostTradeResponse postTradeResponse = new PostTradeResponse();
        postTradeResponse.setItems(items);
        postTradeResponse.setHasMore(false);

        return postTradeResponse;
    }
}
