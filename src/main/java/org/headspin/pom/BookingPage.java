package org.headspin.pom;

import org.headspin.utils.Logger;
import org.headspin.utils.Web;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingPage {

    private WebDriver pageDriver;

    @FindBy(xpath = "//li[contains(@class,'menu_Hotels')]")
    private WebElement hotels;

    @FindBy(xpath = "//label[@for='city']")
    private WebElement cityFieldSelection;

    @FindBy(xpath = "//div[contains(@class,'hsw_autocomplePopup')]//input")
    private WebElement cityFieldInputSelection;

    @FindBy(xpath = "//div[contains(@class,'hsw-seo-header')]")
    private WebElement pageValidator;

    @FindBy(xpath = "//span[contains(text(),'Travelling For')]")
    private WebElement selectTravelFor;

    @FindBy(xpath = "//li[contains(text(),'Leisure')]")
    private WebElement leisure;

    @FindBy(xpath = "//button[@id='hsw_search_button']")
    private WebElement searchButton;

    public BookingPage(WebDriver driver) {
        pageDriver = driver;
        PageFactory.initElements(pageDriver, this);
    }

    public boolean validatePage() {
        return !pageValidator.getText().isEmpty();
    }

    public boolean clickHotels() {
        return Web.click(pageDriver, hotels);
    }

    public boolean selectCity(String cityToSelect) {
        boolean cityInputClicked = Web.click(pageDriver, cityFieldSelection);
        if (cityInputClicked && cityFieldInputSelection != null) {
            Logger.log("Setting city to : " + cityToSelect);
            cityFieldInputSelection.sendKeys(cityToSelect);
            cityFieldInputSelection.sendKeys(Keys.ARROW_DOWN);
            cityFieldInputSelection.sendKeys(Keys.ENTER);
            return true;
        } else {
            Logger.log("Failed to select city");
            return false;
        }
    }

    public boolean selectLeisure() {
        return Web.click(pageDriver, selectTravelFor) && Web.click(pageDriver, leisure);
    }

    public boolean clickSearchButton() {
        return Web.click(pageDriver, searchButton);
    }
}
