package common;

import magic_drivers.IMagicDriver;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverFactory {
    public static WebDriver getSpecificDriver(String driverName) throws IOException {
        Class cls;
        IMagicDriver clsInstance;
        WebDriver webDriver = null;

        try {
            cls = Class.forName("magic_drivers." + driverName + "MagicDriver");
            clsInstance = (IMagicDriver) cls.newInstance();
            clsInstance.setupEnv();
            webDriver = clsInstance.getDriver();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DriverFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return webDriver;
    }
}

