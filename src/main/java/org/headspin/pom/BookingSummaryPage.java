package org.headspin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BookingSummaryPage {

    private WebDriver pageDriver;

    public BookingSummaryPage(WebDriver driver) {
        pageDriver = driver;
        PageFactory.initElements(pageDriver, this);
    }

    public String getHotelName() {
        return pageDriver.findElements(By.xpath("//div[@class='pull-left hotel_leftpart']//p")).get(0).getText();
    }

    public String getCheckinDate() {
        return pageDriver.findElements(By.xpath("//div[@class='checkin pull-left']//span")).get(0).getText();
    }

    public String getCheckoutDate() {
        return pageDriver.findElements(By.xpath("//div[@class='checkin pull-right']//span")).get(0).getText();
    }

    public String getName() {
        return pageDriver.findElements(By.xpath("//div[@class='traveler_details clearfix']//p")).get(0).getText();
    }

    public String getMobileNumberAndEmail() {
        return pageDriver.findElements(By.xpath("//div[@class='traveler_details clearfix']//p")).get(1).getText();
    }
}
