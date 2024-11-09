package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Covid19TrackerPage extends BasePage {

    // Constructor to initialize WebDriver and PageFactory, and call the parent constructor
    public Covid19TrackerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);  // Initialize page elements
    }

    // Method to load the COVID-19 Tracker page
    public void loadCovid19TrackerPage() {
        driver.get("https://inerg-test.web.app/");
    }

    // Method to get the page title
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Locate the 'select' element (dropdown) for states
    @FindBy(xpath = "//select[@class='data-filter-input']")
    WebElement stateDropdown;

    // Locate the "Zoom In" button
    @FindBy(xpath = "//span[normalize-space()='+']")
    WebElement zoomInButton;

    // Locate the "Zoom Out" button
    @FindBy(xpath = "//span[contains(text(),'−')]")
    WebElement zoomOutButton;

    // Method to click the "Zoom In" button
    public void clickZoomInButton() {
        zoomInButton.click();
    }

    // Method to click the "Zoom Out" button
    public void clickZoomOutButton() {
        zoomOutButton.click();
    }

    // Method to get the default selected option from the dropdown
    public String getDefaultDropdownOption() {
        Select select = new Select(stateDropdown);
        return select.getFirstSelectedOption().getText();
    }

    // Method to select a state in the dropdown
    public void selectState(String state) {
        Select select = new Select(stateDropdown);
        select.selectByVisibleText(state);
    }

    

   
    // Method to display all the data available in the result
    public void displayAllData() {
        // Create a WebDriverWait object with Duration (for newer versions of Selenium)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the data elements (the <p> tags) are visible
        List<WebElement> dataParagraphs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='resultCard']/div[@class='display-data']/p")));

        // Check if the list is empty
        if (dataParagraphs.isEmpty()) {
            System.out.println("No data elements found!");
        } else {
            // Loop through the <p> tags and print the label and its data
            for (WebElement paragraph : dataParagraphs) {
                String paragraphText = paragraph.getText();

                // Split the text based on the first colon to separate the label and the data
                String[] parts = paragraphText.split(":");

                if (parts.length > 1) {
                    String label = parts[0].trim();  // The label (e.g., "Active Cases")
                    String dataValue = parts[1].trim();  // The data value (e.g., "5,000")
                    System.out.println(label + ": " + dataValue);  // Print the label and its corresponding data value
                }
            }
        }
    }
 // Locate the element using the provided XPath
    @FindBy(xpath = "//body/div[@id='root']/div[@class='container']/div[2]/div[1]/div[1]/div[1]")
    WebElement targetElement;

    public void hoverAndClickTargetElementTwice() {
        Actions actions = new Actions(driver);
        actions.moveToElement(targetElement).perform();  // Hover over the element
        actions.click().perform();  // Click the element
        try {
            Thread.sleep(1000);  // Adding some wait time before the second click
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.click().perform();  // Click the element again
    }
 // Locate the download button using the provided XPath
    @FindBy(xpath = "(//div[@class='modebar-group']/a[@data-title='Download plot as a png'])[1]")
    WebElement downloadButton;

    public void hoverAndDownloadImage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(downloadButton).perform();  // Hover over the element
        actions.click().perform();  // Click the element to trigger the download
    }

}
