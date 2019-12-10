package magic_drivers;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IMagicDriver {
    public abstract void setupEnv();
    public abstract WebDriver getDriver() throws IOException;
}
