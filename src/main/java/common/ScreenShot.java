package common;


import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ScreenShot {
    public static void makeScreenShot(String title) {
        try {
            Allure.addAttachment(title,
                    new ByteArrayInputStream(FileUtils.readFileToByteArray(((TakesScreenshot)Browser.getDriver()).
                            getScreenshotAs(OutputType.FILE))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

