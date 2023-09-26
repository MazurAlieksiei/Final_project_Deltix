package org.example.steps;

import org.example.models.Item;
import org.example.models.PostTradeResponse;
import org.example.pages.SummaryPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SummaryPageSteps {

    private static SummaryPage summaryPage = new SummaryPage();

    public static void checkPageComponentsExistence() {
        Assert.assertTrue(summaryPage.isSummaryDashboardDisplayed(), "Page 'Summary' is not displayed.");
        Assert.assertTrue(summaryPage.isSettingsButtonDisplayed(), "Element 'Settings button' is not displayed.");
        Assert.assertTrue(summaryPage.isBenchmarkSelectorDisplayed(), "Element 'Benchmark Selector' is not displayed.");
        Assert.assertTrue(summaryPage.isToolBarElementDisplayed("Summary"), "Element 'Summary' is not displayed.");
        Assert.assertTrue(summaryPage.isToolBarElementDisplayed("Grid & chart"), "Element 'Grid & chart' is not displayed.");
        Assert.assertTrue(summaryPage.isToolBarElementDisplayed("Histogram"), "Element 'Histogram' is not displayed.");
        Assert.assertTrue(summaryPage.isToolBarElementDisplayed("Scatter-plot"), "Element 'Scatter-plot' is not displayed.");
        Assert.assertTrue(summaryPage.isToolBarElementDisplayed("Reports"), "Element 'Reports' is not displayed.");
    }

    public static PostTradeResponse getAlgoPerformanceGridDetails() {
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
