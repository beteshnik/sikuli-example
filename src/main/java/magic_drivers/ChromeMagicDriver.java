package magic_drivers;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

public class ChromeMagicDriver implements IMagicDriver {

    String baseUrl = System.getenv("bamboo_BASE_URL");

    @Override
    public void setupEnv() {
        //если baseUrl не задан, то запускаем локальный chrome.driver
        if (baseUrl == null) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        }
    }

    @Override
    public WebDriver getDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capa = DesiredCapabilities.chrome();
        Map<String, Object> preferences = new Hashtable<String, Object>();

        preferences.put("plugins.plugins_disabled", new String[]{
                "Adobe Flash Player",
                "Chrome PDF Viewer"
        });

        preferences.put("chrome.switches", Arrays.asList("--incognito", "--disable-web-security", "--user-data-dir"));
        preferences.put("profile.default_content_settings.popups", 0);
        capa.setJavascriptEnabled(true);
        capa.setPlatform(Platform.WINDOWS);
        capa.setCapability("takesScreenshot", true);
        options.setExperimentalOption("prefs", preferences);
        capa.setCapability(ChromeOptions.CAPABILITY, options);

        if (baseUrl == null) {
            return new ChromeDriver(options);
            //если baseUrl не задан, то выполняем удаленный запуск
        } else {
            RemoteWebDriver driver = new RemoteWebDriver(new URL("http://172.31.8.49:4444/wd/hub"), capa);
            driver.setFileDetector(new LocalFileDetector());
            return driver;
        }
    }
}