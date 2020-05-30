package org.headspin.pom;

import org.headspin.utils.Logger;
import org.headspin.utils.Web;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class JourneyDetailsPage {

    private WebDriver pageDriver;

    public JourneyDetailsPage(WebDriver driver) {
        pageDriver = driver;
        PageFactory.initElements(pageDriver, this);
    }

    public void dismissDialog() {
        try {
            Logger.log("Dialog dissmissed : " + Web.click(
                    pageDriver, pageDriver.findElement(By.xpath("//span[@class='close']"))));
        } catch (Exception ignore) {
            Logger.log("No dialog found");
        }
    }
}
