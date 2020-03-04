package po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(className = "login-button__user")
    private WebElement currentUser;

    @FindBy(className = "sidebar")
    private WebElement toolBar;

    @FindBy(xpath = "//button[contains(@class,'compose')]")
    private WebElement composeBtn;

    @FindBy(css = ".sendmsg__ads-ready")
    private WebElement successPopup;

    @FindBy(xpath = "//a[contains(@class, 'sent')]")
    private WebElement sentLink;


    public HomePage() {
        waitForElement(toolBar, 10);
    }

    public String getCurrentUser() {
        waitForElement(currentUser);
        return currentUser.getText();
    }

    public NewMailPage clickComposeBtn() {
        waitForElementToBeClickable(composeBtn);
        composeBtn.click();
        return new NewMailPage();
    }

    public Boolean isSuccessMsgDisplayed() {
       return isDisplayed(successPopup);
    }

    public SentPage goToSentMails() {
        waitForElementToBeClickable(sentLink);
        sentLink.click();
        return new SentPage();
    }
}
