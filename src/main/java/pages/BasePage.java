// BasePage.java
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
    WebDriver driver;

    // Constructor to initialize the WebDriver
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Common method to select a value from a dropdown
    public void selectState(WebElement dropdown, String state) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(state);  // Select by visible text in the dropdown
    }
}
