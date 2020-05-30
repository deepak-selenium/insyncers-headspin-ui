package org.headspin.pom;

import org.headspin.utils.Logger;
import org.headspin.utils.Web;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HotelDetailsPage {

    private WebDriver pageDriver;

    @FindBy(xpath = "//a[@id='detpg_hotel_rooms']")
    private WebElement roomsTab;

    public HotelDetailsPage(WebDriver driver) {
        pageDriver = driver;
        PageFactory.initElements(pageDriver, this);
    }

    public boolean clickRooms() {
        Logger.log("Clicking rooms tab");
        List<String> windowHandles = new ArrayList<>(pageDriver.getWindowHandles());
        if (windowHandles.size() > 1) {
            pageDriver.switchTo().window(windowHandles.get(1));
            Web.waitForCompletion();
        }
        return Web.click(pageDriver, roomsTab);
    }

    public void publishRoomDetails(int roomNumber) {
        List<String> details = Web.getValues(pageDriver, By.xpath(
                String.format("//div[@class='roomWrap'][%d]//li[contains(@class,'appendBottom')]",
                        roomNumber)), WebElement::getText);
        Logger.log("Room details as below");
        details.forEach(Logger::log);
    }

    public boolean selectFirstRoom() {
        Web.waitForCompletion();
        List<WebElement> buttons = pageDriver.findElements(
                By.xpath("//a[contains(@class,'appendBottom') and contains(text(),'SELECT ROOM')]"));
        return buttons != null && Web.click(pageDriver, buttons.get(0));
    }
}
