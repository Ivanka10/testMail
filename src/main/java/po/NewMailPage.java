package po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewMailPage extends BasePage {

    @FindBy(name = "toFieldInput")
    private WebElement receiverField;

    @FindBy(name = "subject")
    private WebElement subjectField;

    @FindBy(xpath = "//button[@class='default send']")
    private WebElement sendBtn;

    @FindBy(xpath = "//body[contains(@class, 'content')]")
    private WebElement messageBody;

    private final String FRAME = "mce_0_ifr";

    public NewMailPage inputSubject(String subject) {
        waitForElement(subjectField);
        subjectField.sendKeys(subject);
        return this;
    }

    public NewMailPage inputReceiver(String receiver) {
        waitForElement(receiverField);
        receiverField.sendKeys(receiver);
        return this;
    }

    public NewMailPage inputMessage(String message) {
        driver.switchTo().frame(FRAME);
        waitForElement(messageBody);
        messageBody.sendKeys(message);
        driver.switchTo().defaultContent();
        return this;
    }

    public HomePage clickSendBtn() {
        waitForElementToBeClickable(sendBtn, 5);
        sendBtn.click();
        return new HomePage();
    }
}
