package org.headspin.tests;

import org.headspin.pom.LandingPage;
import org.headspin.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MmtTest extends BaseTest {

    @Test(description = "Login To Make my trip")
    public void loginToMmt() {
        LandingPage landingPage = new LandingPage(chromeDriver);
        Assert.assertTrue(landingPage.clickLoginLink(), "Failed to click login link");
        if (landingPage.validateLoginPopUp()) {
            Logger.log("Login popup found");
            try {
                Assert.assertTrue(landingPage.enterUserName());
                Assert.assertTrue(landingPage.enterPassword());
            } catch (Exception ignore) {

            }
        } else {
            Logger.log("No login popup found");
        }
    }
}
