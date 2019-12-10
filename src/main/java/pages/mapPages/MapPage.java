package pages.mapPages;

import common.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import pages.MainPage;

import java.io.IOException;

import org.sikuli.script.Finder;
import org.sikuli.script.Pattern;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Screen;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

//страница авторизации
public class MapPage extends MainPage {

    @FindBy(xpath = "//canvas[contains(@style,'display: block;')]")
    private WebElement map;

    public void ensureMapLoaded() {
        waitWhileElemIsVisible(map);
    }

    public void checkFragmentMapVisibility(String fragmentPath) throws IOException {
        waitSeconds(3);
        float matchIndex = Float.parseFloat(Application.getProperty("imageMatchIndex"));
        Pattern expectedImage = new Pattern(getAbsolutePath(fragmentPath));

        File scrFile = ((TakesScreenshot) Browser.getDriver()).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(scrFile, new File(getAbsolutePath("screenshots/1.png")));

        Finder finder = new Finder(ImageIO.read(scrFile));
        finder.find(expectedImage.similar(matchIndex));
        Assertion.assertFieldIsDisplayedWithMessage(finder.hasNext(), "The image found");
    }

    public void clickMapFragment(String fragmentPath) throws FindFailed, IOException {
        Screen screen = new Screen();
        screen.click(getAbsolutePath(fragmentPath));
        waitSeconds(3);
    }

}