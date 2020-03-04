package bo;

import po.HomePage;
import po.MailPage;
import po.NewMailPage;
import po.SentPage;

public class MailActions {

    private HomePage homePage;
    private MailPage mailPage;
    private NewMailPage newMailPage;
    private SentPage sentPage;

    public MailActions() {
        homePage = new HomePage();
    }

    public void sendMail(String receiver, String subject, String bodyMail) {
        newMailPage = homePage.clickComposeBtn();
        newMailPage.inputSubject(subject)
                .inputReceiver(receiver)
                .inputMessage(bodyMail)
                .clickSendBtn();
    }

    public boolean isSent() {
       return homePage.isSuccessMsgDisplayed();
    }

    public void openSentMessageBySender(String sender) {
        mailPage = homePage.goToSentMails().openLastSentMessageBySender(sender);
    }

    public String getSubject() {
        return mailPage.getSubject();
    }

    public String getMailBody() {
        return mailPage.getMailBody();
    }

}
