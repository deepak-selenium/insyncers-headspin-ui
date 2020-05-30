package org.headspin.tests;

import org.headspin.utils.Driver;
import org.headspin.utils.Logger;
import org.headspin.utils.Reader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static WebDriver chromeDriver;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        chromeDriver = Driver.startChromeDriver();
        chromeDriver.get(Reader.getProperty("base.url"));
        Logger.log("Url launched with session : " + chromeDriver.getTitle());
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {
        if (chromeDriver != null) {
            Logger.log("Quitting driver");
            chromeDriver.quit();
        }
    }

    public void waitForCompletion() {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(chromeDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
