package com.selenium.test.steps;

import com.selenium.test.driver.SingletonDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LaunchSelenium {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LaunchSelenium() {
        this.driver = SingletonDriver.getInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I navigate to Google search page")
    public void navigateToGoogle() {
        driver.get("https://www.google.com");
    }

    @When("I search for {string}")
    public void searchFor(String searchTerm) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
            By.name("q")));
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
    }

    @Then("I should see search results")
    public void verifySearchResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.id("search")));
        Assert.assertTrue("Search results are not displayed",
            driver.findElements(By.cssSelector("div.g")).size() > 0);
    }
}