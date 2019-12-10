package common;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;
import static java.util.Optional.ofNullable;

public class AllureEnvironmentUtils {

    public static void createEnvProperties() {

        FileOutputStream fos = null;

        try {
            Properties props = new Properties();

            // Uncomment for local runs
            fos = new FileOutputStream("allure-results/environment.properties");
//            fos = new FileOutputStream("build/allure-results/environment.properties");

            ofNullable(getProperty("browser")).ifPresent(s -> props.setProperty("Browser", s));

            props.store(fos, "Write environment");

            fos.close();
        } catch (IOException e) {
            Application.log("IO problem when writing allure properties file");
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }
}
