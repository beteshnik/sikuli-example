package pages.infrastructurePages;

import common.Assertion;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.MainPage;


//страница Просмотр футбольного объекта
public class InfrastructureViewPage extends MainPage {

    @FindBy(xpath = "//div[contains(@class,'header')]//div[contains(@class,'heading')]")
    private WebElement ensurePageLoadedElement;

    @FindBy(xpath = "//div[contains(@class,'header')]//div[contains(@class,'heading')]/h1")
    private WebElement objectPageHeading;

    @FindBy(xpath = "//a/button[contains(@class,'button')][contains(text(),'Редактировать')]")
    private WebElement editLinkButton;

    @FindBy(xpath = "//button[contains(@class,'moreButton')]")
    private WebElement moreButton;

    @FindBy(xpath = "//div[text()='Собственник объекта']/following-sibling::a")
    private WebElement ownerShortNameField;

    @FindBy(xpath = "//div[contains(@class,'header')]//div[contains(@class,'type')]")
    private WebElement typeField;

    @FindBy(xpath = "//div[contains(@class,'labelText')][contains(.,'Адрес')]/div[contains(@class,'text')]")
    private WebElement addressField;

    @FindBy(xpath = "//div[contains(@class,'labelText')][contains(.,'E-mail')]/div[contains(@class,'text')]")
    private WebElement emailField;

    @FindBy(xpath = "//div[contains(@class,'labelText')][contains(.,'Телефон')]/div[contains(@class,'text')]")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//div[contains(@class,'labelText')][contains(.,'Официальный сайт')]/div[contains(@class,'text')]")
    private WebElement urlField;

    @FindBy(xpath = "//button[text()='Редактировать']")
    private WebElement editButton;

    public void ensurePageLoaded() {
        waitWhileElemIsVisible(ensurePageLoadedElement);
    }

    public void checkObjectName(String fullNameExpected) {
        String fullNameUi = getEditableText(objectPageHeading);
        Assertion.assertEqualWithMessage(fullNameExpected, fullNameUi);
    }

    public String getFullName() {
        return getEditableText(objectPageHeading);
    }

    public String getOwnerShortName() {
        return getEditableText(ownerShortNameField);
    }

    public String getType() {
        return getEditableText(typeField);
    }

    public String getAddress() {
        return getEditableText(addressField);
    }

    public String getPhoneNumber() {
        return getEditableText(phoneNumberField);
    }

    public String getUrl() {
        return getEditableText(urlField);
    }

    public String getEmail() {
        return getEditableText(emailField);
    }

    public void clickEditButton() {
        clickAndMakeScreenshot(editLinkButton, "Кнопка Редактировать");
    }

    public void clickMoreButton() {
        clickAndMakeScreenshot(moreButton, "Кнопка ... для вызова выпадающего меню");
    }

}