package org.headspin.utils;

import com.google.common.io.Resources;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private static WebDriver driver;
    private static final String TOKEN = "cdb27f30dc594c05b393e1b5c456a038";

    public static WebDriver startChromeDriver() {
        String cloudMode = Reader.getProperty("test.mode.cloud");
        if (cloudMode.equals("true")) {
            Logger.log("Running chrome on cloud mode");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("browserName", "chrome");
            capabilities.setCapability("browserVersion", "79.0.3945.88");
            Map<String, String> displayProperty = new HashMap<>();
            displayProperty.put("width", "1920");
            displayProperty.put("height", "1080");
            capabilities.setCapability("headspin:initialScreenSize", displayProperty);
            try {
                driver = new RemoteWebDriver(new URL(
                        String.format(Reader.getProperty("test.device.url"), TOKEN)), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            Logger.log("Starting chrome in local mode");
            System.setProperty("webdriver.chrome.driver",
                    Resources.getResource("chromedriver").getPath());
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);
            options.addArguments("disable-infobars");
            options.addArguments("start-maximized");
            options.addArguments("enable-automation");
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
