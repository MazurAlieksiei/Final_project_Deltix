package org.deltix.tests;

import org.deltix.utility.Browser;
import org.deltix.utility.DeltixApi;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected Properties testProperties;
    protected DeltixApi deltixApi;

    @BeforeClass
    public void initData() throws IOException {
        testProperties = new Properties();
        testProperties.load(new FileInputStream("src/test/resources/project.properties"));
        deltixApi = new DeltixApi(testProperties);
    }
    
    @BeforeMethod
    public void init() {
        Browser.getDriver().get(testProperties.getProperty("siteUrl"));
    }

    @AfterMethod
    public void cleanup() {
        Browser.close();
    }
}
