package tests;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Covid19TrackerPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Covid19TrackerTest {
    WebDriver driver;
    Covid19TrackerPage covid19TrackerPage;

    // Setup ChromeDriver with the appropriate download directory
    @BeforeClass
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        
        // Set Chrome options to specify the download directory
        ChromeOptions options = new ChromeOptions();
        String downloadDir = "C:/path/to/download/directory"; // Change this to your download directory
        options.addArguments("download.default_directory=" + downloadDir);

        driver = new ChromeDriver(options);
        covid19TrackerPage = new Covid19TrackerPage(driver);
        driver.manage().window().maximize();
        covid19TrackerPage.loadCovid19TrackerPage();
        Thread.sleep(2000); // Initial wait for the page to load
    }

    // Test to verify page load
    @Test
    public void verifyCovid19TrackerPageLoadsSuccessfully() throws InterruptedException {
        String expectedTitle = "InerG Test App";
        String actualTitle = covid19TrackerPage.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page did not load successfully.");
    }

    // Test to verify the Zoom In button click
    @Test
    public void verifyZoomInButtonClick() throws InterruptedException {
        covid19TrackerPage.clickZoomInButton();
        Thread.sleep(2000); // Wait to observe zoom effect
    }

    // Test to verify the Zoom Out button click
    @Test(dependsOnMethods = {"verifyZoomInButtonClick"})
    public void verifyZoomOutButtonClick() throws InterruptedException {
        covid19TrackerPage.clickZoomOutButton();
        Thread.sleep(2000); // Wait to observe zoom effect
    }

    // Test to verify default dropdown option (depends on zoom actions)
    @Test(dependsOnMethods = {"verifyZoomOutButtonClick"})
    public void verifyDefaultDropdownOption() throws InterruptedException {
        String expectedOption = "Select a State"; // Expecting the default option
        String actualOption = covid19TrackerPage.getDefaultDropdownOption();
        Assert.assertEquals(actualOption, expectedOption, "Default option in the dropdown is incorrect.");
    }

    // Test to verify state selection (depends on zoom actions)
    @Test(dependsOnMethods = {"verifyZoomOutButtonClick"})
    public void verifyStateSelection() throws InterruptedException {
        String stateToSelect = "Tamil Nadu";
        covid19TrackerPage.selectState(stateToSelect);

        String selectedOption = covid19TrackerPage.getDefaultDropdownOption();
        Assert.assertEquals(selectedOption, stateToSelect, "State was not selected correctly.");
    }

    // Display all data after selecting the state
    @Test(dependsOnMethods = {"verifyStateSelection"})
    public void verifyDisplayAllData() throws InterruptedException {
        covid19TrackerPage.displayAllData(); // Display the data for the selected state
        Thread.sleep(4000);
    }

    // Test to verify hover and click on target element twice
    @Test(dependsOnMethods = {"verifyStateSelection"})
    public void verifyHoverAndClickTargetElementTwice() throws InterruptedException {
        covid19TrackerPage.hoverAndClickTargetElementTwice(); // Hover and click twice
        Thread.sleep(2000); // Wait to observe the effect
    }

    // Test to verify hover and download image functionality
    @Test(dependsOnMethods = {"verifyStateSelection"})
    public void verifyHoverAndDownloadImage() throws InterruptedException {
        covid19TrackerPage.hoverAndDownloadImage(); // Hover and click the download button
        Thread.sleep(5000); // Wait for the image to download

        // Verify the image is downloaded by checking if it exists in the specified directory
        File downloadedFile = new File("C:/path/to/download/directory/plot.png"); // Change to actual download path
        Assert.assertTrue(downloadedFile.exists(), "Image was not downloaded successfully.");
    }

    // Close the browser after the test
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
