package bo;

import org.openqa.selenium.WebDriver;
import po.HomePage;
import po.LoginPage;

public class SignIn {
    private LoginPage loginPage;

    public SignIn(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public void login(String login, String password) {
       loginPage.inputLogin(login).inputPassword(password).clickLoginBtn();
    }

    public String getLoggedUser(WebDriver driver) {
        HomePage homePage = new HomePage(driver);
        return homePage.getCurrentUser();
    }

}
