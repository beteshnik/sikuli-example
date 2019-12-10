package common;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Assertion {
    public static String error = "";
    public static String message = "";

    public static String assertEqualWithMessage(String actualResult, String expectedResult) {
        error = "";
        message = "";
        ScreenShot.makeScreenShot("Перед проверкой условия" + message);
        message = String.format("Ожидаемый результат: \"%s\"; Фактический результат: \"%s\".",
                expectedResult, actualResult);

        try {
            assertEquals(actualResult , expectedResult);
        } catch (AssertionError ex) {
            error = ex.toString().split("\\n")[0];
            Assert.fail(error);
        } catch (NoSuchElementException ex) {
            error = ex.toString().split("\\n")[0];
            Assert.fail(error);
        }

        return message;
    }

    public static String assertFieldIsDisplayedWithMessage(Boolean b, String m) {
        message = m;

        try {
            ScreenShot.makeScreenShot("Перед проверкой условия" + message);
            assertTrue(b);
        } catch (AssertionError ex ) {
            error = ex.toString().split("\\n")[0];
            Assert.fail(error);
            message = "";
        } catch (NullPointerException ex) {
            error = ex.toString().split("\\n")[0];
            Assert.fail(error);
            message = "";
        } catch (NoSuchElementException ex) {
            error = ex.toString().split("\\n")[0];
            Assert.fail(error);
            message = "";
        }

        return message;
    }
}
