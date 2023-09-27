package org.deltix.steps;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.deltix.enums.Order;
import org.deltix.pages.*;
import org.deltix.utility.CollectionUtils;
import org.testng.Assert;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CommonSteps {

    private static Logger log = Logger.getLogger(CommonSteps.class);
    private static LoginPage loginPage = new LoginPage();
    private static SummaryPage summaryPage = new SummaryPage();
    private static HistogramPage histogramPage = new HistogramPage();
    private static ScatterplotPage scatterplotPage = new ScatterplotPage();
    private static GridPage gridPage = new GridPage();
    private static BasePage basePage = new BasePage();


    @Step("Login")
    public static void loginViaUI(String userName, String password) {
        log.info("Login via UI.");
        Assert.assertTrue(loginPage.isOpened(), "Login page is not displayed.");
        loginPage.enterName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSingInButton();
        Assert.assertTrue(summaryPage.isSummaryDashboardDisplayed(), "Page 'Summary' is not displayed.");
    }

    @Step("Open Histogram page.")
    public static void openHistogramPage() {
        log.info("Open Histogram page.");
        summaryPage.clickOnToolBarElement("Histogram");
        Assert.assertTrue(histogramPage.isPageOpened(), "Page 'Histogram' is not displayed.");
    }

    @Step("Open Scatter-plot page.")
    public static void openScatterplotPage() {
        log.info("Open Scatter-plot page.");
        summaryPage.clickOnToolBarElement("Scatter-plot");
        Assert.assertTrue(scatterplotPage.isPageOpened(), "Page 'Scatter-plot' is not displayed.");
    }

    @Step("Open Grid&Chart page.")
    public static void openGridPage() {
        log.info("Open Grid&Chart page.");
        summaryPage.clickOnToolBarElement("Grid & chart");
        Assert.assertTrue(gridPage.isPageOpened(), "Page 'Grid & chart' is not displayed.");
    }

    @Step("Turn on filter by section title with filter configuration.")
    public static void turnOffFilterByTitleWithFilterConfiguration(String filterTitle) {
        log.info("Turn on filter by section title with filter configuration.");
        basePage.getFilterForm().openFilter();
        Assert.assertTrue(basePage.getFilterForm().isFilterToolPanelDisplayed(), "Filter tool panel is not displayed.");
        basePage.getFilterForm().turnFilterOffByTitle(filterTitle);
    }

    @Step("Turn off filter by section title with filter configuration.")
    public static void turnOnFilterByTitleWithFilterConfiguration(String filterTitle) {
        log.info("Turn off filter by section title with filter configuration.");
        basePage.getFilterForm().openFilter();
        Assert.assertTrue(basePage.getFilterForm().isFilterToolPanelDisplayed(), "Filter tool panel is not displayed.");
        basePage.getFilterForm().turnFilterOnByTitle(filterTitle);
    }

    @Step("Check if values sorted in required way.")
    public static <T extends Comparable> void checkValuesSortedInWay(List<T> list, Order order) {
        log.info("Check if values sorted in required way.");
        try {
            Assert.assertTrue(CollectionUtils.  isSortedInRequiredWay(list, order));
        } catch (NoSuchAlgorithmException ex) {
            log.error("No such realization of sorting " + order);
            Assert.assertTrue(false, "No such realization of sorting " + order);
        }
    }
}
