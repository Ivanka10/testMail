package bo;

import po.HomePage;
import po.LoginPage;

public class SignIn {
    private LoginPage loginPage;
    private HomePage homePage;

    public SignIn() {
        loginPage = new LoginPage();
    }

    public void login(String login, String password) {
       homePage = loginPage.inputLogin(login).inputPassword(password).clickLoginBtn();
    }

    public String getLoggedUser() {
       return homePage.getCurrentUser();
    }

}
