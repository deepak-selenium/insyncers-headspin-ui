package org.headspin.driver;

import com.google.common.io.Resources;
import org.headspin.utils.Logger;
import org.headspin.utils.Reader;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ChromeManager extends DriverManager {

    private static String BROWSER_NAME = "chrome";
    private static String CLOUD_BROWSER_VERSION = "79.0.3945.88";
    private static String TOKEN = "cdb27f30dc594c05b393e1b5c456a038";

    @Override
    protected void startCloudRun() {
        Logger.log("Running chrome on cloud mode");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("browserName", BROWSER_NAME);
        capabilities.setCapability("browserVersion", CLOUD_BROWSER_VERSION);
        Map<String, String> displayProperty = new HashMap<>();
        displayProperty.put("width", "1920");
        displayProperty.put("height", "1080");
        capabilities.setCapability("headspin:initialScreenSize", displayProperty);
        try {
            cloudDriver = new RemoteWebDriver(new URL(
                    String.format(Reader.getProperty("test.device.url"), TOKEN)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void startLocalRun() {
        Logger.log("Starting chrome in local mode");
        Logger.log(System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + 
        		"\\src\\main\\java\\org\\headspin\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("disable-infobars");
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        localDriver = new ChromeDriver(options);
    }
}