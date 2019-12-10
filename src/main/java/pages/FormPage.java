package pages;

import common.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static common.Browser.getDriver;

import java.util.List;

//форма любой сущности
public class FormPage extends MainPage {

    @FindBy(xpath = "//div[contains(@class,'nameContainer')]")
    private WebElement viewPageElement;

    @FindBy(xpath = "//div[contains(@class,'heading')][contains(.,'Редактирование')]")
    private WebElement editPageElement;

    @FindBy(xpath = "//button[contains(@class,'moreButton')]")
    private WebElement moreButton;

    @FindBy(xpath = "//a/button[contains(@class,'button')][contains(text(),'Редактировать')]")
    private WebElement editLinkButton;

    @FindBy(xpath = "//button[text()='Сохранить' or text()='Обновить профиль'][not(@disabled)]")
    private WebElement saveButton;

    @FindBy(xpath = "//*[contains(@class,'preloader') or contains(@class,'u-Processing-spinner')]")
    private WebElement loaderElement;

    @FindBy(xpath = "//div[contains(@class,'heading')][contains(.,'Редактирование')]")
    private List<WebElement> editPageElements;

    public void clickTab(String tabName) {
        WebElement tabToClick = getDriver().findElement(By.xpath("//button[contains(@class,'tab')][text()='" + tabName + "']"));
        waitWhileElemIsVisible(tabToClick);
        clickAndMakeScreenshot(tabToClick, "Закладка " + tabName);
        waitForLoad();
    }

    public void clickLinkTab(String tabName) {
        scrollUp();
        waitWhileElemIsVisible(getDriver().findElement(By.xpath("//a[contains(.,'" + tabName + "')]")));
        clickAndMakeScreenshot(getDriver().findElement(By.xpath("//a[contains(.,'" + tabName + "')]")), "Закладка " + tabName);
        waitForLoad();
    }

    public void ensureViewPageLoaded() {
        waitWhileElemIsVisible(viewPageElement);
    }

    public void ensureEditPageLoaded() {
        waitWhileElemIsVisible(editPageElement);
    }

    public void ensureNoTextVisible(String text) {
        List<WebElement> notWantedTexts = getDriver().findElements(By.xpath("//*[text()='" + text + "']"));
        Assert.assertEquals(notWantedTexts.size(), 0, "Кол-во строк текста, которого быть не должно");
    }

    public void openEditLink(String entity, String id) {
        String baseUrl = System.getenv("bamboo_BASE_URL");
        String url;
        if (baseUrl == null) {
            url = Application.getProperty("devLoginPage") + "/" + entity + "/edit/" + id;
        } else {
            url = baseUrl + "/" + entity + "/edit/" + id;
        }
        int i = 0;
        while(editPageElements.size()==0 && i<5) {
            getDriver().navigate().to(url);
            waitForLoad();
            waitUntilElementInvisibile(loaderElement);
            i++;
        }
    }

    public void clickMoreButton() {
        clickAndMakeScreenshot(moreButton, "Кнопка ... для вызова выпадающего меню");
    }

    public void clickEditButton() {
        clickAndMakeScreenshot(editLinkButton, "Кнопка Редактировать");
    }

    public void clickSaveButton() {
        clickAndMakeScreenshot(saveButton, "Кнопка Сохранить");
    }

    public void editName(String newName) {
        WebElement nameField = getDriver().findElement(By.xpath("//input[contains(@value,'Автотест')]"));
        clearByDell(nameField);
        type(nameField, newName);
    }
}
