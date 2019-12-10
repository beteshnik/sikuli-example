package pages.autorisationPages;

import common.Application;
import common.Browser;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.MainPage;

//страница авторизации
public class LoginPage extends MainPage {

    @FindBy(xpath = "//input[@id='login']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[span[text()='Войти']]")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class , 'LoginContainer')]")
    private WebElement loginPageLoadedElement;


    public void ensurePageLoaded() {
        waitWhileElemIsVisible(loginPageLoadedElement);
    }

    public void setLogin(String login) {
        loginField.sendKeys(login);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);

    }

    public void submitButtonClick() {
        clickAndMakeScreenshot(submitButton, "Войти");
    }

    public void openLoginPage() {
        MainPage.acceptAlert();
        String baseUrl = System.getenv("bamboo_BASE_URL");
        if (baseUrl == null) {
            Browser.getDriver().navigate().to(Application.getProperty("devLoginPage"));
        } else
            Browser.getDriver().navigate().to(baseUrl);
        waitForLoad();
    }

}