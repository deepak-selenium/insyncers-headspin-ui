package org.headspin.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Web {
    private static Set<String> clickedTexts = new TreeSet<>();

    public static boolean click(WebDriver driver, WebElement element) {
        boolean success;
        if (element == null) {
            Logger.log("Failed to find the element to click");
            success = false;
        } else {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            String text = element.getText();
            clickedTexts.add(text);
            Logger.log("Clicking : " + text);
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

    public static boolean back(WebDriver driver) {
        return click(driver, driver.findElement(By.xpath("//button[contains(@mattooltip,'Back')]")));
    }

    public static List<String> getValues(WebDriver driver, By loc, Function<WebElement, String> pred) {
        List<WebElement> elements = driver.findElements(loc);
        return elements.stream().map(pred).collect(Collectors.toList());
    }

    public static void waitForCompletion() {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Set<String> getClicks() {
        return clickedTexts;
    }
}
