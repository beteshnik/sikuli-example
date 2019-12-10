package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import static common.Browser.getDriver;

public class TablePage extends MainPage {


    //получаем ячейку таблицы
    public WebElement getCell(int col, int row, String table) {
        String xpath = String.format(table, row, col);
        return getDriver().findElement(By.xpath(xpath));
    }

    //получаем ячейки таблицы из последнего строки
    public WebElement getLastRowCell(int col, String table) {
        String xpath = String.format(table, col);
        return getDriver().findElement(By.xpath(xpath));
    }

    //получаем ячейку таблицы
    public List<WebElement> getCelsRow(int row, String table) {
        String xpath = String.format(table, row);
        return getDriver().findElements(By.xpath(xpath));
    }

    //получаем ячейку таблицы
    public WebElement getCellByRow(int row, String table) {
        String xpath = String.format(table, row);
        return getDriver().findElement(By.xpath(xpath));
    }

    //получаем ячейку таблицы Группы
    public WebElement getGroupListCell(int col, int row, String groupData) {
        String table = "//tbody/tr[%d]/td[%d]//span[text() = '" + groupData + "']";
        String xpath = String.format(table, row, col);
        return getDriver().findElement(By.xpath(xpath));
    }

    //получаем ячейку таблицы по названию колонки и её содержимому
    public static WebElement getListCellByContent(String column, String search) {
        WebElement th = getDriver().findElement(By.xpath("//th[contains(.,'" + column + "')]"));
        int col = getTableHeaders().indexOf(th);
        String table = "//tbody/tr[contains(.,'" + search + "')]/td[%d]";
        String xpath = String.format(table, col + 1);
        return getDriver().findElement(By.xpath(xpath));
    }

    //получаем ячейку таблицы по названию колонки и номеру строки
    public static WebElement getListCellByTitleAndLine(String column, int row) {
        WebElement th = getDriver().findElement(By.xpath("//th[contains(.,'" + column + "')]"));
        int col = getTableHeaders().indexOf(th);
        String table = "//tbody/tr[%d]/td[%d]";
        String xpath = String.format(table, row, col + 1);
        return getDriver().findElement(By.xpath(xpath));
    }

    //получаем ячейки таблицы по названию колонки
    public static List<WebElement> getListCellByTitle(String column) {
        WebElement th = getDriver().findElement(By.xpath("//th[contains(.,'" + column + "')]"));
        int col = getTableHeaders().indexOf(th);
        String table = "//tbody/tr/td[%d]";
        String xpath = String.format(table, col + 1);
        return getDriver().findElements(By.xpath(xpath));
    }

    //получаем заголовки таблицы
    public static List<WebElement> getTableHeaders() {
        return getDriver().findElements(By.xpath("//th"));
    }


}
