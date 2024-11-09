package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class COVID19TrackerPage {
    WebDriver driver;

    // Constructor
    public COVID19TrackerPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By pageTitle = By.xpath("//h1[text()='InerG Test App']");  // Assuming the title of the page is in an h1 tag

    // Actions
    public boolean isPageLoadedSuccessfully() {
        return driver.findElement(pageTitle).isDisplayed();
    }
}
