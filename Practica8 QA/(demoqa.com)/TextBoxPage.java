import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextBoxPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By userNameLocator = By.id("userName");
    private By userEmailLocator = By.id("userEmail");
    private By currentAddressLocator = By.id("currentAddress");
    private By permanentAddressLocator = By.id("permanentAddress");
    private By submitButtonLocator = By.id("submit");
    private By outputNameLocator = By.id("name");
    private By outputEmailLocator = By.id("email");

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillFullName(String name) {
        wait.until(ExpectedConditions.presenceOfElementLocated(userNameLocator));
        driver.findElement(userNameLocator).sendKeys(name);
    }

    public void fillEmail(String email) {
        driver.findElement(userEmailLocator).sendKeys(email);
    }

    public void fillCurrentAddress(String address) {
        driver.findElement(currentAddressLocator).sendKeys(address);
    }

    public void fillPermanentAddress(String address) {
        driver.findElement(permanentAddressLocator).sendKeys(address);
    }

    public void clickSubmit() {
        WebElement submitButton = driver.findElement(submitButtonLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        wait.until(ExpectedConditions.elementToBeClickable(submitButtonLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
    }

    public String getOutputName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(outputNameLocator));
        return driver.findElement(outputNameLocator).getText();
    }

    public String getOutputEmail() {
        return driver.findElement(outputEmailLocator).getText();
    }
}
