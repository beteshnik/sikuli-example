package flow;

import common.Browser;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import pages.MainPage;
import pages.autorisationPages.LoginPage;


public class LoginFlow {

    private static LoginPage loginPage = PageFactory.initElements(
            Browser.getDriver(), LoginPage.class
    );

    //Открываем LoginPage
    public static void openLoginPage() {
        loginPage.openLoginPage();
    }

    //Успешная авторизация
    public static void login(String login, String password) {
        loginPage.ensurePageLoaded();
        loginPage.setLogin("Мэр");
        loginPage.setPassword(password);
        loginPage.submitButtonClick();
    }


    //Убеждаемся что главная страница открыта
    public static void verifyHomePageIsOpened() {

    }
}