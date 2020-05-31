package org.headspin.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.headspin.pom.*;
import org.headspin.testTransformer.WebListener;
import org.headspin.utils.Logger;
import org.headspin.utils.Reader;
import org.headspin.utils.Web;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Set;

@Listeners(WebListener.class)
public class MmtTest extends BaseTest {

    String firstName = "Headspin";
    String lastName = "Hackathon";
    String phoneNum = "9" + RandomStringUtils.randomNumeric(9);

    @Test(description = "Login To Make my trip")
    public void loginToMmt() {
        WebListener.driver = chromeDriver;
        LandingPage landingPage = new LandingPage(chromeDriver);
        Assert.assertTrue(landingPage.clickLoginLink(), "Failed to click login link");
        if (landingPage.validateLoginPopUp()) {
            Logger.log("Login popup found");
            Assert.assertTrue(landingPage.enterUserName(), "Failed to enter user name");
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
        Assert.assertTrue(bookingPage.clickHotels(), "Hotel click failed");
        Assert.assertTrue(bookingPage.selectCity("Bangalore"), "Failed to set city");
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
        Assert.assertTrue(detailsPage.selectFirstRoom());
        Web.waitForCompletion();

        //pay now
        JourneyDetailsPage journeyDetailsPage = new JourneyDetailsPage(chromeDriver);
        journeyDetailsPage.dismissDialog();
        journeyDetailsPage.fillTravellerDetails(firstName, lastName);
        journeyDetailsPage.fillPhoneNumber(phoneNum);

        Assert.assertTrue(journeyDetailsPage.selectCommonRequestedOptions());
        Assert.assertTrue(journeyDetailsPage.uncheckDonation());
        Assert.assertTrue(journeyDetailsPage.clickPayNowButton());
        Web.waitForCompletion();
        journeyDetailsPage.dismissDialog();

        //Match previous details
        Set<String> allEntries = Web.getClicks();
        BookingSummaryPage summaryPage = new BookingSummaryPage(chromeDriver);
        boolean hotelFound = false;
        for (String s : allEntries) {
            if (s.contains(summaryPage.getHotelName())) {
                hotelFound = true;
                break;
            }
        }
        Assert.assertTrue(hotelFound);
        Assert.assertEquals(summaryPage.getMobileNumberAndEmail().split(",")[1].trim(),
                Reader.getProperty("web.user.name").trim());
        Assert.assertEquals(summaryPage.getMobileNumberAndEmail().split(",")[0].trim(), phoneNum);
        Assert.assertTrue(summaryPage.getName().equalsIgnoreCase(firstName + " " + lastName));
    }
}
