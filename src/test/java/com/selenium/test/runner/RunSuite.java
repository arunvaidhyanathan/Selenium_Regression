package com.selenium.test.runner;

import com.selenium.test.driver.SingletonDriver;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.selenium.test.steps"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-pretty",
        "json:target/cucumber-reports/CucumberTestReport.json"
    },
    monochrome = true
)
public class RunSuite {
    private static WebDriver driver;
    private static String feature;

    @BeforeClass
    public static void beforeClass() {
        feature = System.getProperty("cucumber.filter.tags", "@all");
        driver = SingletonDriver.getInstance();
    }

    @AfterClass
    public static void afterClass() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            SingletonDriver.quitDriver();
        }
    }
}