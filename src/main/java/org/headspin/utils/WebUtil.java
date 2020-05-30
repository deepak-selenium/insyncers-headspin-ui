package org.headspin.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebUtil {

    public static boolean click(WebDriver driver, WebElement element) {
        boolean success;
        if (element == null) {
            Logger.log("Failed to find the element to click");
            success = false;
        } else {
            Logger.log("Clicking : " + element.getText());
            try {
                element.click();
                success = true;
            } catch (Exception e) {
                Actions actions = new Actions(driver);
                actions.moveToElement(element).click();
                success = true;
            }
        }
        return success;
    }

    public static void moveToElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void waitForCommandToFinish() {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean back(WebDriver driver) {
        return click(driver, driver.findElement(By.xpath("//button[contains(@mattooltip,'Back')]")));
    }
}
