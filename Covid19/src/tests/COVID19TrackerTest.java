package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;

import dev.failsafe.internal.util.Assert;
import pages.COVID19TrackerPage;

public class COVID19TrackerTest {
    WebDriver driver;
    COVID19TrackerPage covid19TrackerPage;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver (e.g., ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");  // Update path as needed
        driver = new ChromeDriver();
        driver.get("https://inerg-test.web.app/");

        // Initialize COVID19TrackerPage
        covid19TrackerPage = new COVID19TrackerPage(driver);
    }

    /*@Test
    public void testPageLoading() {
        // Verify the page is loaded successfully
        Assert.assertTrue(covid19TrackerPage.isPageLoadedSuccessfully(), "COVID-19 Tracker page did not load successfully.");
    }*/

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
