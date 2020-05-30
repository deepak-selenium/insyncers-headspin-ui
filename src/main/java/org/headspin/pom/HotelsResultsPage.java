package org.headspin.pom;

import org.headspin.utils.Logger;
import org.headspin.utils.Web;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelsResultsPage {

    private WebDriver pageDriver;

    @FindBy(className = "minValue")
    private WebElement minAmount;

    @FindBy(className = "maxValue")
    private WebElement maxValue;

    @FindBy(xpath = "//div[contains(@class,'mmBackdrop wholeBlack')]")
    private WebElement dismiss;

    public HotelsResultsPage(WebDriver driver) {
        pageDriver = driver;
        PageFactory.initElements(pageDriver, this);
        Logger.log("Overlay Dissmissed : " + Web.click(pageDriver, dismiss));
        Web.moveToElement(pageDriver, pageDriver.findElement(
                By.xpath("//span[contains(text(),'Locality')]")));
    }

    public boolean setFilter() {
        WebElement slider = pageDriver.findElements(
                By.xpath("//div[contains(@role,'slider')]")).get(1);
        if (slider == null) {
            return false;
        } else {
            Logger.log("Max value before : " + getMaxValue());
            Actions move = new Actions(pageDriver);
            do {
                if (getMaxValue().equals("3000")) {
                    break;
                }
                move.dragAndDropBy(slider, -20, 0).click();
                move.build().perform();
                Web.waitForCompletion();
            } while (Integer.parseInt(getMaxValue()) != 3000);
            /*move.dragAndDropBy(slider, -15, 0).click();
            move.build().perform();
            Web.waitForCompletion();*/
            Logger.log("Max value after : " + getMaxValue());
            return getMaxValue().equals("3000");
        }
    }

    public boolean setUserRating() {
        return Web.click(pageDriver, pageDriver.findElement(
                By.xpath("//label[contains(text(),'4 & above (Very Good)')]")));
    }

    private String getMinValue() {
        return minAmount.getText().replace("INR", "").trim();
    }

    private String getMaxValue() {
        return maxValue.getText().replace("INR", "").trim();
    }

    public boolean goToHotelNumber(int number) {
        List<WebElement> getHotelListings = pageDriver.findElements(By.className("listingRowOuter"));
        if (getHotelListings.size() == 0) {
            Logger.log("No Hotels found");
            return false;
        } else {
            return Web.click(pageDriver, getHotelListings.get(number - 1));
        }
    }
}
