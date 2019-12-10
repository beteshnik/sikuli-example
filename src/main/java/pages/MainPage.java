package pages;

import common.Application;
import common.ScreenShot;
import common.Browser;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.StaleElementReferenceException;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static common.Browser.getDriver;
import static java.time.format.DateTimeFormatter.ofPattern;


public class MainPage {
    private static final int periodElementWait = Integer.parseInt(Application.getProperty("periodElementWait"));
    private static final int periodLoadWait = Integer.parseInt(Application.getProperty("periodElementWait"));
    private static final int periodFrameWait = Integer.parseInt(Application.getProperty("periodElementWait"));
    private static final int periodInvisibleWait = Integer.parseInt(Application.getProperty("periodInvisibleWait"));

    public static void waitForLoad() {
        new WebDriverWait(getDriver(), periodLoadWait).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static boolean pageIsLoaded() {
        return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete");
    }

    public static void waitWhileElemIsVisible(WebElement element) {
//        WebDriverWait wait = new WebDriverWait(getDriver(), periodElementWait);
//        try {
//            wait.until(
//                    ExpectedConditions.visibilityOf(element));
//        } catch (StaleElementReferenceException e) {
//            wait.until(
//                    ExpectedConditions.visibilityOf(element));
//        }
    }

    public static void waitWhileElemIsPresent(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), periodElementWait);
//        wait.until(
//                ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitWhileElemIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), periodElementWait);
//        wait.until(
//                ExpectedConditions.elementToBeClickable(element));
    }



    public static void clickAndMakeScreenshot(WebElement element, String message) {
        ScreenShot.makeScreenShot("До нажатия на кнопку: " + message);
        Application.log(String.format("Кликаю на '%s'", message));
        element.click();
    }

    //method for typing in field
    public static void type(WebElement element, String value) {
        element.sendKeys(value);
    }

    //change focus from element
    public static void changeFocus(WebElement element) {
        element.sendKeys(Keys.TAB);
    }

    //moving within list - down
    public static void moveDown(WebElement element) {
        element.sendKeys(Keys.ARROW_DOWN);
    }

    //type and then submit element
    public static void typeSubmit(WebElement element, String value) {
        element.sendKeys(value + Keys.TAB);
    }

    //submit field element
    public static void submitField(WebElement element) {
        element.sendKeys(Keys.ENTER);
    }

    public static void waitSeconds(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isElementNotPresent(WebElement element) {
        try {
            return !element.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    //переключение фрейма
    public static void frameToBeAvailbleAndSwitchToIt(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), periodFrameWait);
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    //возврат к изначальному фрейму
    public static void switchToDefaultFrame() {
        getDriver().switchTo().defaultContent();
    }

    //переходм в новый фрейм
    public static void switchToNewWindow() {
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
    }

    //скрол к элементам которые находятся вне зоны видимости
    public static void mouseMoveToElementActions(WebElement element) {
        try {
            Coordinates coordinate = ((Locatable) element).getCoordinates();
            coordinate.onPage();
            coordinate.inViewPort();
        } catch (NoSuchElementException ignore) {
        } catch (ElementNotVisibleException ignore) {
        }
    }

    public static void scrollToElement(WebElement webelement) {
//        Actions actions = new Actions(getDriver());
//        actions.moveToElement(webelement).perform();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", webelement);
    }

    //принудительное удаление
    public static void clearByDell(WebElement element) {
        //очистка вручную
        element.click();
        element.sendKeys(Keys.HOME);
        element.sendKeys(Keys.chord(Keys.SHIFT, Keys.END));
        element.sendKeys(Keys.DELETE);
    }

    //выделить текст
    public static void selectByKeys(WebElement element) {
        //очистка вручную
        element.sendKeys(Keys.HOME);
        element.sendKeys(Keys.chord(Keys.SHIFT, Keys.END));
    }

    public static String getUneditableText(WebElement field) {
        try {
            if (field.getAttribute("value").equals("")) {
                return null;
            } else
                return field.getAttribute("value");
        } catch (NoSuchElementException | NullPointerException e) {
            return null;
        }
    }

    public static String getAttributeText(WebElement field, String attributeName) {
        try {
            if (field.getAttribute(attributeName).equals("")) {
                return null;
            } else
                return field.getAttribute(attributeName);
        } catch (NoSuchElementException | NullPointerException e) {
            return null;
        }
    }

    public static String getUneditableTextTitle(WebElement field) {
        try {
            if (field.getAttribute("title").equals("")) {
                return null;
            } else
                return field.getAttribute("title");
        } catch (NoSuchElementException | NullPointerException e) {
            return null;
        }
    }

    public static String getEditableText(WebElement field) {
        try {
            if (field.getText().equals("") || field.getText().equals("-") || field.getText().contains("Не определено")) {
                return null;
            } else
                return field.getText();
        } catch (NoSuchElementException | NullPointerException e) {
            return null;
        }
    }

    public static String getInnerText(WebElement field) {
        try {
            if (field.getText().equals("") || field.getText().equals("-") || field.getText().contains("Не определено")) {
                return null;
            } else
                return field.getAttribute("textContent");
        } catch (NoSuchElementException | NullPointerException e) {
            return null;
        }
    }

    //получаем абсолютный путь к файлу
    public static String getAbsolutePath(String path) throws IOException {
        System.out.println(new File("src/test/resources/" + path).getCanonicalPath());
        return new File("src/test/resources/" + path).getCanonicalPath();
    }

    public static void setCheckBox(WebElement element, String message) {
        if (!element.isSelected()) clickAndMakeScreenshot(element, message);
    }

    public static void unSetCheckBox(WebElement element, String message) {
        //if (element.isSelected())
        clickAndMakeScreenshot(element, message);
    }

    //ожидаем исчезновение элемента
    public static void waitUntilElementInvisibile(WebElement webElement) {
        int counter = 0, second = 1; // интервал проверки

        getDriver().manage().timeouts().implicitlyWait(periodInvisibleWait, TimeUnit.SECONDS);
        try {
            while (webElement.isDisplayed()) {
                waitSeconds(second);
                counter += second;
                System.out.println(counter);

                if (counter > periodElementWait)
                    throw new RuntimeException("Элемент не исчез спустя (sec): " + periodElementWait);
            }
        } catch (Exception e) {
        }
    }

    public static void acceptAlert() {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //getDriver().navigate().refresh();
        try {
            Alert alert = getDriver().switchTo().alert();
            alert.accept();

        } catch (NoAlertPresentException ex) {
        }
    }

    //определяем текущую дату
    public static String currentDate() {
        DateTimeFormatter date = (DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        LocalDateTime now = LocalDateTime.now();
        return date.format(now);
    }

    //определяем текущую дату
    public static String currentDateShort() {
        DateTimeFormatter date = (DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDateTime now = LocalDateTime.now();
        return date.format(now);
    }

    //определяем текущую дату + 10 дней
    public static String currentDate10plus() {
        DateTimeFormatter date = (DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        LocalDateTime now = LocalDateTime.now().plusDays(10);
        return date.format(now);
    }


    //конвертируем Unix время в формат dd mm yyyy
    public static String unixToHumanTime(String timeToFormat) {
        Long unixTime = Long.parseLong(timeToFormat);
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date(unixTime));
    }

    //текущее время в Unix формате
    public static Long currentUnixTime() {
        Date currentDate = new Date();
        return currentDate.getTime();
    }

    //текущее время + 10 дней в Unix формате
    public static Long currentUnixTime10() {
        Date currentDate = new Date();
        return currentDate.getTime() + 10 * 24 * 60 * 60 * 1000;
    }

    public static void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0,250)", "");
    }

    public static void scrollUp() {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        Actions actions = new Actions(getDriver());
        System.out.println(executor.executeScript("return window.pageYOffset;"));
        while (((Number) executor.executeScript("return window.pageYOffset;")).longValue() > 0) {
            actions.sendKeys(Keys.PAGE_UP).perform();
            waitForLoad();
        }
    }

    //создаем случайный набор символов, на вход получаем количество символов и краткое имя, той сущности , которую генерируем
    public static String getRandomWord(int lenght) {
        String alphabet[] = {"А", "а", "Б", "б", "В", "в", "Г", "г", "Д", "д", "Е", "е", "Ё", "ё", "Ж", "ж", "З", "з", "И", "и", "Й", "й", "К", "к", "Л", "л", "М", "м", "Н", "н", "О", "о", "П", "п", "Р", "р", "С", "с", "Т", "т", "У", "у", "Ф", "ф", "Х", "х", "Ц", "ц", "Ч", "ч", "Ш", "ш", "Щ", "щ", "Ъ", "ъ", "Ы", "ы", "Ь", "ь", "Э", "э", "Ю", "ю", "Я", "я"};
        StringBuilder sb = new StringBuilder(Math.max(lenght, 16));
        SecureRandom RND = new SecureRandom();
        String randomWord;
        for (int i = 0; i < lenght; i++) {
            int len = alphabet.length;
            int random = RND.nextInt(len);
            String c = alphabet[random];
            sb.append(c);
        }
        randomWord = sb.toString();
        return randomWord.substring(0, 1).toUpperCase() + randomWord.substring(1);
    }

    //устанавливаем значение выпадающего списка
    protected void setDropdownByText(WebElement dropdown, String text) {
        if (text != null && !text.equals("")) {
            dropdown.click();
            MainPage.waitWhileElemIsVisible(getDriver().findElement(By.xpath("//*[contains(text(), '" + text + "')]")));
            WebElement listLine = dropdown.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
            listLine.click();
        }
    }
}



