package com.selenium.test.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class SingletonDriver {
    private static final String SELENIUM_HOST = System.getProperty("selenium.host");
    private static final String SELENIUM_PORT = System.getProperty("selenium.port", "4444");
    private static RemoteWebDriver REAL_DRIVER = null;
    private static final String SELENIUM_REMOTE_URL = String.format("http://%s:%s/wd/hub", SELENIUM_HOST, SELENIUM_PORT);
    private static WebDriver driver;

    private SingletonDriver() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            startChromeDriver();
        }
        return driver;
    }

    private static void startChromeDriver() {
        try {
            if (SELENIUM_HOST == null || SELENIUM_HOST.isEmpty()) {
                // Local WebDriver setup
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeOptions());
            } else {
                // Remote WebDriver setup
                driver = getRemoteWebDriver();
            }
            driver.manage().window().maximize();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        return options;
    }

    private static RemoteWebDriver getRemoteWebDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), getChromeOptions());
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}