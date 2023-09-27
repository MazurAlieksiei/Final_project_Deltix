package org.deltix.tests;

import io.qameta.allure.Description;
import org.deltix.enums.Order;
import org.deltix.models.PostTradeResponse;
import org.deltix.steps.*;
import org.deltix.utility.Browser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


public class DeltixTests extends BaseTest {


    @BeforeMethod
    public void init() {
        Browser.getDriver().get(testProperties.getProperty("siteUrl"));
    }

    @Test(testName = "TC01", description = "Login and check main components")
    @Description("Login and check main components on after login page")
    public void loginAndCheckMainComponentsTest() {
        CommonSteps.loginViaUI(testProperties.getProperty("login"), testProperties.getProperty("password"));
        SummaryPageSteps.checkPageComponentsExistence();
    }

    @Test(testName = "TC04", description = "Hover over a bar")
    @Description("Hover over a bar on Histogram page.")
    public void hoverOverABArSelectionOnHistogramTest() {
        CommonSteps.loginViaUI(testProperties.getProperty("login"), testProperties.getProperty("password"));
        CommonSteps.openHistogramPage();

        HistogramPageSteps.waitUntilHistogramLoaded();
        HistogramPageSteps.checkBarContainersExistence();
        HistogramPageSteps.checkAllBarContainersAttributesExistence();
        HistogramPageSteps.checkBarDetailsGridOpenedAfterClickOnEachBar();
    }


    @Test(
            testName = "TC10",
            description = "Check Attributes and Intervals change",
            dataProvider = "axeValuesProvider",
            dataProviderClass = TestData.class
    )
    @Description("Check Attributes and Intervals change on Scatter-plot page")
    public void checkAttributesAndIntervalsChangeOnScatterPlotPageTest(
            String xAttributeValue,
            String yAttributeValue,
            List<String> xAxeTicksValues,
            List<String> yAxeTicksValues) {
        CommonSteps.loginViaUI(testProperties.getProperty("login"), testProperties.getProperty("password"));
        CommonSteps.openScatterplotPage();

        ScatterplotPageSteps.changeXAttributeValue(xAttributeValue);
        ScatterplotPageSteps.checkScatterPlotLoaderDisplayed();
        ScatterplotPageSteps.waitUntilScatterPlotUpdated();
        ScatterplotPageSteps.checkXAxeDetailsChangedTo(xAttributeValue, xAxeTicksValues);

        ScatterplotPageSteps.changeYAttributeValue(yAttributeValue);
        ScatterplotPageSteps.checkScatterPlotLoaderDisplayed();
        ScatterplotPageSteps.waitUntilScatterPlotUpdated();
        ScatterplotPageSteps.checkYAxeDetailsChangedTo(yAttributeValue, yAxeTicksValues);
    }

    @Test(testName = "TC14",
            description = "Check Avg fill price and Mid price")
    @Description("Check Avg fill price and Mid price on Grid&Chart page")
    public void checkAvgFillPriceAndMidPriceTest() {
        CommonSteps.loginViaUI(testProperties.getProperty("login"), testProperties.getProperty("password"));
        CommonSteps.openGridPage();

        GridPageSteps.selectOrder(0, "Id");
        GridPageSteps.clickOnLinesButton();
        GridPageSteps.turnOnChartLines(Arrays.asList("Avg fill price", "Mid price"));
        GridPageSteps.clickOnLinesButton();
        GridPageSteps.sortOrderGridByCellNameAndOrder("Num of executions", Order.ASC);
        GridPageSteps.selectOrder(0, "Id");
        GridPageSteps.checkIfColorsOfAvgMatch();
        GridPageSteps.checkIfColorsOfMidMatch();

        GridPageSteps.checkOrderExecPriceValue(0);
        GridPageSteps.checkOrderMidPriceValue();
    }

    @Test(testName = "TC46", description = "Check columns visibility")
    @Description("Check columns visibility on Grid&Chart page")
    public void checkColumnsVisibilityTest() {
        CommonSteps.loginViaUI(testProperties.getProperty("login"), testProperties.getProperty("password"));
        CommonSteps.openGridPage();

        GridPageSteps.checkColumnDisplayedOnGrid("Id", true);
        CommonSteps.turnOffFilterByTitleWithFilterConfiguration("Id");
        GridPageSteps.checkColumnDisplayedOnGrid("Id", false);
        CommonSteps.turnOnFilterByTitleWithFilterConfiguration("Id");
        GridPageSteps.checkColumnDisplayedOnGrid("Id", true);

        GridPageSteps.checkColumnDisplayedOnGrid("End time", true);
        GridPageSteps.turnOffFilterByTitleWithToolPanel("End time");
        GridPageSteps.checkColumnDisplayedOnGrid("End time", false);
        GridPageSteps.turnOnFilterByTitleWithToolPanel("End time");
        GridPageSteps.checkColumnDisplayedOnGrid("End time", true);

        GridPageSteps.checkColumnDisplayedOnGrid("Start time", true);
        GridPageSteps.turnOffFilterByTitleWithColumnMenu("Start time", "Start time");
        GridPageSteps.checkColumnDisplayedOnGrid("Start time", false);
        GridPageSteps.turnOnfFilterByTitleWithColumnMenu("Start time", "Start time");
        GridPageSteps.checkColumnDisplayedOnGrid("Start time", true);
    }

    @Test(
            testName = "TC68",
            description = "Check sort",
            dataProvider = "columnsNameProvider",
            dataProviderClass = TestData.class
    )
    @Description("Check sort of columns values on Grid&Chart page")
    public void checkSortTest(String columnName) {
        CommonSteps.loginViaUI(testProperties.getProperty("login"), testProperties.getProperty("password"));
        CommonSteps.openGridPage();

        List<String> startValuesList = GridPageSteps.getNCellValuesForColumnTitle(columnName, 10);
        GridPageSteps.clickOnHeaderColumn(columnName);
        List<String> firstClickValuesList = GridPageSteps.getNCellValuesForColumnTitle(columnName, 10);
        CommonSteps.checkValuesSortedInWay(firstClickValuesList, Order.ASC);
        GridPageSteps.clickOnHeaderColumn(columnName);
        List<String> secondClickValuesList = GridPageSteps.getNCellValuesForColumnTitle(columnName, 10);
        CommonSteps.checkValuesSortedInWay(secondClickValuesList, Order.DESC);
        GridPageSteps.clickOnHeaderColumn(columnName);
        List<String> thridClickValuesList = GridPageSteps.getNCellValuesForColumnTitle(columnName, 10);
        Assert.assertEquals(thridClickValuesList, startValuesList);
    }

    @Test(testName = "TC106", description = "Check Algo performance grid")
    @Description("Check Algo performance grid data from Network matches with Algo performance data from Summary page")
    public void checkAlgoPerformanceGrid() {
        CommonSteps.loginViaUI(testProperties.getProperty("login"), testProperties.getProperty("password"));

        PostTradeResponse response = deltixApi.getPostTradeOrders();
        PostTradeResponse postTradeResponse = SummaryPageSteps.getAlgoPerformanceGridDetails();
        Assert.assertTrue(
                postTradeResponse.equals(response),
                "Algo performance data from Network not match with Algo performance data from Summary page."
        );
    }
}
