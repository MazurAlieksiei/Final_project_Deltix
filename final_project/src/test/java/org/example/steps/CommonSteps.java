package org.example.steps;

import org.example.enums.Order;
import org.example.pages.*;
import org.example.utility.CollectionUtils;
import org.testng.Assert;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CommonSteps {

    private static LoginPage loginPage = new LoginPage();
    private static SummaryPage summaryPage = new SummaryPage();
    private static HistogramPage histogramPage = new HistogramPage();
    private static ScatterplotPage scatterplotPage = new ScatterplotPage();
    private static GridPage gridPage = new GridPage();
    private static BasePage basePage = new BasePage();


    public static void loginViaUI(String userName, String password) {
        Assert.assertTrue(loginPage.isOpened(), "Login page is not displayed.");
        loginPage.enterName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSingInButton();
        Assert.assertTrue(summaryPage.isSummaryDashboardDisplayed(), "Page 'Summary' is not displayed.");
    }

    public static void openHistogramPage() {
        summaryPage.clickOnToolBarElement("Histogram");
        Assert.assertTrue(histogramPage.isPageOpened(), "Page 'Histogram' is not displayed.");
    }

    public static void openScatterplotPage() {
        summaryPage.clickOnToolBarElement("Scatter-plot");
        Assert.assertTrue(scatterplotPage.isPageOpened(), "Page 'Scatter-plot' is not displayed.");
    }

    public static void openGridPage() {
        summaryPage.clickOnToolBarElement("Grid & chart");
        Assert.assertTrue(gridPage.isPageOpened(), "Page 'Grid & chart' is not displayed.");
    }

    public static void turnOffFilterByTitleWithFilterConfiguration(String filterTitle) {
        basePage.getFilterForm().openFilter();
        Assert.assertTrue(basePage.getFilterForm().isFilterToolPanelDisplayed(), "Filter tool panel is not displayed.");
        basePage.getFilterForm().turnFilterOffByTitle(filterTitle);
    }

    public static void turnOnFilterByTitleWithFilterConfiguration(String filterTitle) {
        basePage.getFilterForm().openFilter();
        Assert.assertTrue(basePage.getFilterForm().isFilterToolPanelDisplayed(), "Filter tool panel is not displayed.");
        basePage.getFilterForm().turnFilterOnByTitle(filterTitle);
    }

    public static <T extends Comparable> void checkValuesSortedInWay(List<T> list, Order order) {
        try {
            Assert.assertTrue(CollectionUtils.isSortedInRequiredWay(list, order));
        } catch (NoSuchAlgorithmException ex) {
            Assert.assertTrue(false, "No such realization of sorting " + order);
        }
    }
}
