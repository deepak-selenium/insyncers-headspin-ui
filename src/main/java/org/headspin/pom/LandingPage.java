package org.headspin.pom;

import org.headspin.utils.Logger;
import org.headspin.utils.Reader;
import org.headspin.utils.Web;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    private WebDriver pageDriver;

    @FindBy(xpath = "//ul[@class='userSection pushRight']//p[contains(text(),'Login')]")
    private WebElement loginLink;

    public LandingPage(WebDriver driver) {
        pageDriver = driver;
        PageFactory.initElements(pageDriver, this);
    }

    public boolean clickLoginLink() {
        return Web.click(pageDriver, loginLink);
    }

    public boolean validateLoginPopUp() {
        return pageDriver.findElement(By.xpath("//section[contains(@class,'modalMain')]")) != null;
    }

    public boolean enterUserName() {
        String username = Reader.getProperty("web.user.name");
        WebElement userNameField = pageDriver.findElement(By.xpath("//input[@id='username']"));
        if (userNameField != null && userNameField.isDisplayed()) {
            Logger.log("Setting user name to : " + username);
            userNameField.sendKeys(username + "\n");
        } else {
            Logger.log("Failed to set user name");
        }
        return Web.click(pageDriver, pageDriver.findElement(By.xpath("//span[contains(text(),'Continue')]/..")));
    }

    public boolean enterPassword() {
        String password = Reader.getProperty("web.user.password");
        WebElement passwordField = pageDriver.findElement(By.xpath("//input[@id='password']"));
        if (passwordField != null && passwordField.isDisplayed()) {
            Logger.log("Setting password to : " + password);
            passwordField.sendKeys(password + "\n");
        } else {
            Logger.log("Failed to set password");
        }
        return Web.click(pageDriver, pageDriver.findElement(By.xpath("//span[text()='Login']/..")));
    }
}
