package po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'logo')]")
    private WebElement logo;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    public LoginPage() {
        waitForElement(logo);
    }
    public LoginPage inputLogin(String login) {
        waitForElement(loginInput);
        loginInput.sendKeys(login);
        return this;
    }

    public LoginPage inputPassword(String password) {
        waitForElement(passwordInput);
        passwordInput.sendKeys(password);
        return this;
    }

    public HomePage clickLoginBtn() {
        waitForElementToBeClickable(loginBtn, 5);
        loginBtn.click();
        return new HomePage();
    }
}
