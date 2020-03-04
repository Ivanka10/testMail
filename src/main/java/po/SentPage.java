package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class SentPage extends BasePage {

    @FindBy(className = "msglist__row-address-name")
    private WebElement receivers;

    private final String MAIL_BY_SENDER = "//span[@class='msglist__row-address-wrap' and text()='%s']";

    public MailPage openLastSentMessageBySender(String sender) {
        waitForElement(receivers);
        List<WebElement> receivers = driver.findElements(By.xpath(String.format(MAIL_BY_SENDER, sender)));
        receivers.get(0).click();
        return new MailPage();
    }
}
