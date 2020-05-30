package org.headspin.tests;

import org.headspin.pom.BookingPage;
import org.headspin.pom.HotelDetailsPage;
import org.headspin.pom.HotelsResultsPage;
import org.headspin.pom.LandingPage;
import org.headspin.utils.Logger;
import org.headspin.utils.Web;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MmtTest extends BaseTest {

    @Test(description = "Login To Make my trip")
    public void loginToMmt() {
        LandingPage landingPage = new LandingPage(chromeDriver);
        Assert.assertTrue(landingPage.clickLoginLink(), "Failed to click login link");
        if (landingPage.validateLoginPopUp()) {
            Logger.log("Login popup found");
            Assert.assertTrue(landingPage.enterUserName());
            try {
                landingPage.enterPassword();
            } catch (Exception ignore) {
                landingPage.dismissOtp();
            }
        } else {
            Logger.log("No login popup found");
        }
    }

    @Test(description = "Hotel selection", dependsOnMethods = {"loginToMmt"})
    public void selectHotel() {
        BookingPage bookingPage = new BookingPage(chromeDriver);
        Assert.assertTrue(bookingPage.clickHotels());
        Assert.assertTrue(bookingPage.selectCity("Bangalore"));
        Assert.assertTrue(bookingPage.selectLeisure());
        Assert.assertTrue(bookingPage.clickSearchButton());
        Web.waitForCompletion();
    }

    @Test(description = "price filter selection", dependsOnMethods = {"selectHotel"})
    public void setSearchFilters() {
        HotelsResultsPage resultsPage = new HotelsResultsPage(chromeDriver);
        Assert.assertTrue(resultsPage.setFilter());
        Assert.assertTrue(resultsPage.setUserRating());
        Assert.assertTrue(resultsPage.goToHotelNumber(5));
        Web.waitForCompletion();
    }

    @Test(description = "Hotel room operations", dependsOnMethods = {"setSearchFilters"})
    public void roomTest() {
        HotelDetailsPage detailsPage = new HotelDetailsPage(chromeDriver);
        Assert.assertTrue(detailsPage.clickRooms());
        detailsPage.publishRoomDetails(1);
        Assert.assertTrue(detailsPage.selectRoom(1));
    }
}
