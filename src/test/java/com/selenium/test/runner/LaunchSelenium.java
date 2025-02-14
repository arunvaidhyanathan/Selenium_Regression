package com.selenium.test.runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class LaunchSelenium {
    public static void main(String[] args) {
        // Get the feature tag from system properties or use default
        String feature = System.getProperty("cucumber.filter.tags", "@all");
        System.out.println("Executing tests with feature tag: " + feature);

        // Run the test suite
        Result result = JUnitCore.runClasses(RunSuite.class);

        // Print test execution results
        System.out.println("\nTest Execution Summary:");
        System.out.println("Total tests run: " + result.getRunCount());
        System.out.println("Failed tests: " + result.getFailureCount());
        System.out.println("Ignored tests: " + result.getIgnoreCount());
        System.out.println("Time taken: " + result.getRunTime() + " ms");

        // Print failures if any
        if (!result.wasSuccessful()) {
            System.out.println("\nFailed Tests:");
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
            System.exit(1); // Exit with error code if tests failed
        }

        System.exit(0); // Exit successfully if all tests passed
    }
}