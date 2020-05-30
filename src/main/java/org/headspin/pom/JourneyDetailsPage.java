package org.headspin.pom;

import org.headspin.utils.Logger;
import org.headspin.utils.Web;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JourneyDetailsPage {

    private WebDriver pageDriver;

    @FindBy(id = "fName")
    private WebElement fName;

    @FindBy(id = "lName")
    private WebElement lName;

    @FindBy(xpath = "//div[contains(@class,'_SpecialRequest')]/p")
    private WebElement commonQuestionDrag;

    @FindBy(xpath = "//label[contains(text(),'Smoking room')]")
    private WebElement smokingRoomSelection;

    @FindBy(xpath = "//label[contains(text(),'Late check-in')]")
    private WebElement lateCheckingSelection;

    @FindBy(xpath = "//input[@id='donation']/..")
    private WebElement uncheckFiveDonation;

    @FindBy(xpath = "//a[contains(@class,'primaryBtn btnPayNow')]")
    private WebElement payNowButton;

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

    public void fillTravellerDetails(String firstName, String lastName) {
        Logger.log(String.format("First name set to [%s] and last name set to [%s]", firstName, lastName));
        fName.sendKeys(firstName + "\n");
        lName.sendKeys(lastName + "\n");
    }

    public void fillPhoneNumber(String number) {
        try {
            WebElement phoneNumber = pageDriver.findElement(By.xpath("//input[@id='mNo']"));
            if (phoneNumber != null && phoneNumber.getAttribute("value").isEmpty()) {
                phoneNumber.sendKeys(number);
            }
        } catch (Exception ignore) {
        }
    }

    public boolean selectCommonRequestedOptions() {
        Web.moveToElement(pageDriver, commonQuestionDrag);
        return Web.click(pageDriver, smokingRoomSelection) && Web.click(pageDriver, lateCheckingSelection);
    }

    public boolean uncheckDonation() {
        return uncheckFiveDonation.isSelected() || Web.click(pageDriver, uncheckFiveDonation);
    }

    public boolean clickPayNowButton() {
        return Web.click(pageDriver, payNowButton);
    }
}
