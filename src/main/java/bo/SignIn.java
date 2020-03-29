package bo;

import po.HomePage;
import po.LoginPage;

public class SignIn {
    private LoginPage loginPage;

    public SignIn() {
        loginPage = new LoginPage();
    }

    public void login(String login, String password) {
       loginPage.inputLogin(login).inputPassword(password).clickLoginBtn();
    }

    public String getLoggedUser() {
        HomePage homePage = new HomePage();
        return homePage.getCurrentUser();
    }

}
