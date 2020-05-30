package org.headspin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Priceandratingfilter {
    private WebDriver pageDriver;

    public void filterRating() {
        pageDriver.findElement(By.xpath("//label[contains(text(),'4 & above (Very Good)')]")).click();
    }

    public void sliderAction() throws InterruptedException {
        WebElement slider1 = pageDriver.findElement(By.xpath("//div[aria-valuenow='0']"));
        WebElement slider2 = pageDriver.findElement(By.xpath("//div[aria-valuenow='2']"));
        Thread.sleep(3000);

        Actions act = new Actions(pageDriver);
        act.dragAndDropBy(slider2, -60, 0).build().perform();
        Thread.sleep(3000);
        act.dragAndDropBy(slider1, 30, 0).build().perform();


    }

}
