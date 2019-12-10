package flow;

import common.Browser;
import org.openqa.selenium.support.PageFactory;
import pages.mapPages.MapPage;
import org.sikuli.script.FindFailed;

import java.io.IOException;


public class MapFlow {

    private static MapPage mapPage = PageFactory.initElements(
            Browser.getDriver(), MapPage.class
    );

    public static void checkFullMapVisibility() {
        mapPage.ensureMapLoaded();
    }

    public static void checkFragmentMapVisibility(String fragmentPath) throws IOException {
        mapPage.checkFragmentMapVisibility(fragmentPath);
    }

    public static void clickMapFragment(String fragmentPath) throws FindFailed, IOException {
        mapPage.clickMapFragment(fragmentPath);

    }
}