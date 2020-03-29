import bo.MailActions;
import bo.SignIn;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utilits.JsonParser;
import utilits.model.UserModel;
import utilits.properties.FilePath;

import java.io.File;

public class MailTest {
    private WebDriver driver;

    @DataProvider(name = "getUsers", parallel = true)
    public Object[] getData() {
        JsonParser parser = new JsonParser();
        FilePath filePath = new FilePath();
        return parser.getData(new File(filePath.propertyFile("filePathJSON")));
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(String browser) throws Exception {
       driver =  DriverManager.getDriver(browser);
    }

    @Test(dataProvider = "getUsers")
    public void testLoginAndSendMail(UserModel user) {
        SignIn signIn = new SignIn(driver);
        signIn.login(user.getLogin(), user.getPassword());

        Assert.assertEquals(user.getLogin(), signIn.getLoggedUser(driver), "Incorrect mail of logged user on home page");

        MailActions mailActions = new MailActions(driver);
        mailActions.sendMail(user.getMessageModel().getReceiver(),
                user.getMessageModel().getSubject(),
                user.getMessageModel().getMessage());

        Assert.assertTrue(mailActions.isSent(), "Message was not sent");

        mailActions.openSentMessageBySender(user.getMessageModel().getReceiver());

        Assert.assertEquals(user.getMessageModel().getMessage(), mailActions.getMailBody(), "Mail body is incorrect.");
        Assert.assertEquals(user.getMessageModel().getSubject(), mailActions.getSubject(), "Subject is incorrect");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.tearDown();
    }
}
