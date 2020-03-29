package po;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasePage {
    protected WebDriver driver;

    BasePage(){
        try {
            driver = DriverManager.getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageFactory.initElements(driver, this);
    }

    void waitForElement(WebElement element, int timeInSecond) {
        new WebDriverWait(driver, timeInSecond).until(ExpectedConditions.visibilityOf(element));
    }

    void waitForElement(WebElement element) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
    }

    void waitForElementToBeClickable(WebElement element, int timeInSecond) {
        new WebDriverWait(driver, timeInSecond).until(ExpectedConditions.elementToBeClickable(element));
    }

    void waitForElementToBeClickable(WebElement element) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(element));
    }

    boolean isDisplayed(WebElement element) {
        waitForElement(element);
        return element.isDisplayed();
    }
}
