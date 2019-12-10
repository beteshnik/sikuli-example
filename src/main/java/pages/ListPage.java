package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.TablePage;
import static common.Browser.getDriver;
import java.util.List;

//список
public class ListPage extends MainPage {

    @FindBy(xpath = "//a//div[contains(@class, 'info')]")
    private WebElement ensurePageLoadedElement;

    @FindBy(xpath = "//a[contains(@class,'listItem')]")
    private WebElement ensureListItemLoadedElement;

    @FindBy(xpath = "//div[@aria-hidden='false']//div[contains(@class,'t-Alerts')]")
    private WebElement ensureAlertsLoadedElement;

    @FindBy(xpath = "//div[contains(@class, 'searchbar')]//input")
    private WebElement searchField;

    @FindBy(xpath = "//div[contains(@class,'labelText')][contains(.,'Результатов')]/div[contains(@class,'text')]")
    private WebElement resultsCounter;

    @FindBy(xpath = "//*[contains(@class,'preloader') or contains(@class,'u-Processing-spinner')]")
    private WebElement loaderElement;


    @FindBy(xpath = "//h2[contains(text(),'Новые')]")
    private WebElement newRecordsHeader;

    @FindBy(xpath = "//span[@class='nodatafound'][contains(.,'Нет записей')]")
    private WebElement emptySearchResults;

    public void ensurePageLoaded() {
        waitUntilElementInvisibile(loaderElement);
        waitWhileElemIsVisible(ensurePageLoadedElement);
    }

    public void ensureListItemLoaded() {
        waitWhileElemIsVisible(ensureListItemLoadedElement);
    }

    public void ensureAlertListLoaded() {
        waitUntilElementInvisibile(loaderElement);
        waitWhileElemIsVisible(ensureAlertsLoadedElement);
    }

    //выбираем значение в таблице по содержимому
    public void selectTableContainerByContent(String dataString) {
        //получаем контейнер по содержимому
        waitWhileElemIsVisible(getDriver().findElement(By.xpath("//a[contains(.,'" + dataString + "')]")));
        clickAndMakeScreenshot(getDriver().findElement(By.xpath("//a[contains(.,'" + dataString + "')]")),"В списке строку " + dataString);
        waitForLoad();
    }

    //выбираем значение в таблице c заголовком по содержимому
    public void selectTableContainerByContentUnderTitle(String dataString, String tableName) {
        //получаем контейнер по содержимому
        waitSeconds(5);
        WebElement container = getDriver().findElement(By.xpath("//div[@class='t-Region-header'][contains(.,'"+tableName+"')]/following-sibling::div//a[contains(.,'" + dataString + "')]"));
        waitWhileElemIsVisible(container);
        clickAndMakeScreenshot(container,"В списке строку " + dataString);
        waitForLoad();
    }

    //выбираем последнее значение в таблице c заголовком по содержимому
    public void selectTableContainerByContentUnderTitleLast(String dataString, String tableName) {
        //получаем контейнер по содержимому
        waitWhileElemIsVisible(getDriver().findElement(By.xpath("//div[@class='t-Region-header'][contains(.,'"+tableName+"')]/following-sibling::div//a")));
        List<WebElement> containers = getDriver().findElements(By.xpath("//div[@class='t-Region-header'][contains(.,'"+tableName+"')]/following-sibling::div//a[contains(.,'" + dataString + "')]"));
        waitWhileElemIsVisible(containers.get(containers.size()-1));
        clickAndMakeScreenshot(containers.get(containers.size()-1),"В списке последнюю строку " + dataString);
    }

    //выбираем первое значение в таблице c заголовком по содержимому
    public void selectTableContainerByContentUnderTitleFirst(String dataString, String tableName) {
        //получаем контейнер по содержимому
        waitWhileElemIsVisible(getDriver().findElement(By.xpath("//*[contains(.,'"+tableName+"')]/following-sibling::div//a")));
        List<WebElement> containers = getDriver().findElements(By.xpath("//*[contains(.,'"+tableName+"')]/following-sibling::div//a[contains(.,'" + dataString + "')]"));
        waitWhileElemIsVisible(containers.get(0));
        clickAndMakeScreenshot(containers.get(0),"В списке первую строку " + dataString);
    }

    //выбираем значение в таблице по номеру
    public void selectTableContainerByOrder(int orderNumber) {
        //получаем контейнер по содержимому
        WebElement container = getDriver().findElements(By.xpath("//div[@role='alert']/a")).get(orderNumber);
        clickAndMakeScreenshot(container,"В списке строку номер " + orderNumber+1);
    }

    //выбираем строку в таблице по номеру в истории изменений
    public void selectChangeHistoryRow(int rowNumber) {
        WebElement row = getDriver().findElement(By.xpath("//tbody/tr["+rowNumber+"]//button"));
        waitWhileElemIsVisible(row);
        clickAndMakeScreenshot(row,"В списке строку номер " + row);
    }

    //проверяем, что в таблице есть нужная запись с ссылкой нужное кол-во раз
    public void ensureLinkPresentInList(String searchName, int quantity) {
        //получаем контейнер по содержимому
        List<WebElement> dataLines = getDriver().findElements(By.xpath("//a[contains(@class, 'listItem')]//span[contains(.,'"+searchName+"')]"));
        Assert.assertEquals(dataLines.size(), quantity,"В списке отображаются все нужные записи");
    }

    //проверяем, что в таблице c заголовком есть нужная запись c ссылкой нужное кол-во раз
    public void ensureLinkPresentInListUnderHeading(String tableName, String searchName, int quantity) {
        //получаем контейнер по содержимому
        List<WebElement> dataLines = getDriver().findElements(By.xpath("//div[@class='t-Region-header'][contains(.,'"+tableName+"')]/following-sibling::div//a//span[contains(.,'"+searchName+"')]"));
        Assert.assertEquals(dataLines.size(), quantity,"В списке отображаются все нужные записи");
    }

    //проверяем, что в таблице есть нужная запись нужное кол-во раз
    public void ensureNamePresentInList(String searchName, int quantity) {
        //получаем контейнер по содержимому
        List<WebElement> dataLines = getDriver().findElements(By.xpath("//div[@aria-hidden='false']//div[@class='t-Alert-content'][contains(.,'"+searchName+"')]"));
        Assert.assertEquals(dataLines.size(), quantity,"В списке отображаются все нужные записи");
    }

    //проверяем, что в счетчике нужное кол-во результатов
    public void ensureCounterValueList(int quantity) {
        //получаем контейнер по содержимому
        int quantityUI = Integer.parseInt(getEditableText(resultsCounter));
        Assert.assertEquals(quantityUI, quantity, "В счетчике Результаты корректное кол-во");
    }

    //проверяем, что в таблице нужное кол-во результатов
    public void ensureResultsQuantityList(int quantity) {
        waitWhileElemIsPresent(By.xpath("//tbody"));
        //получаем контейнер по содержимому
        int quantityUI = getDriver().findElements(By.xpath("//tbody/tr")).size();
        Assert.assertEquals(quantityUI, quantity, "В таблице отображаются данные " + quantity);
    }

    //проверяем, что в таблице есть нужная запись с нужным статусом
    public void ensureNameWithStatusPresentInList(String searchName, String status) {
        waitSeconds(2);
        WebElement container = getDriver().findElement(By.xpath("//a[contains(.,'" + searchName + "')]//div[contains(@class,'statusIcon')]/span[text()='"+status+"']"));
        waitWhileElemIsVisible(container);
    }

    //проверяем, что в таблице есть нужная запись с нужным статусом
    public void ensureNameWithStatusPresentInListApex(String searchName, String status) {
        waitSeconds(2);
        WebElement container = getDriver().findElement(By.xpath("//div[@class='t-Alert-content'][contains(.,'" + searchName + "')]/following-sibling::div[@class='t-Alert-buttons']//span[contains(text(),'"+status+"')]"));
        waitWhileElemIsVisible(container);
    }

    //проверяем, что в таблице есть новые записи
    public void ensureNewRecordsVisible() {
        waitWhileElemIsVisible(newRecordsHeader);
    }

    //проверяем, что в результатах поиска пусто
    public void ensureEmptySearchResults() {
        waitWhileElemIsVisible(emptySearchResults);
    }
}
