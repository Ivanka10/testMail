package po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailPage extends BasePage {

    @FindBy(className = "readmsg__subject")
    private WebElement msgSubject;

    @FindBy(className = "readmsg__body")
    private WebElement msgBody;

    @FindBy(className = "readmsg__head-contacts")
    private WebElement sender;

    MailPage() {
        waitForElement(sender, 10);
    }

    public String getSubject() {
        waitForElement(msgSubject);
        return msgSubject.getText();
    }

    public String getMailBody() {
        waitForElement(msgBody);
        return msgBody.getText();
    }
}
