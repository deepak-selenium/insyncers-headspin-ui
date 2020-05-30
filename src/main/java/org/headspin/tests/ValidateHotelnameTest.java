package org.headspin.tests;

import org.headspin.pom.ApplyActions;
import org.headspin.pom.CaptureHotelnameAction;
import org.headspin.pom.LandingPage;
import org.headspin.pom.Priceandratingfilter;
import org.testng.annotations.Test;

public class ValidateHotelnameTest extends BaseTest {
	@Test(description = "validate hotel name and click")
	public void applyFilter() throws InterruptedException {
		LandingPage landingPage = new LandingPage(chromeDriver);
		Priceandratingfilter fiter = new Priceandratingfilter();
		ApplyActions applyac = new ApplyActions();
		CaptureHotelnameAction hotelname = new CaptureHotelnameAction();
		landingPage.clickLoginLink();
		landingPage.validateLoginPopUp();
		landingPage.enterUserName();
		landingPage.enterPassword();
		Thread.sleep(10 * 000);
		fiter.sliderAction();
		Thread.sleep(10 * 000);
		fiter.filterRating();
		Thread.sleep(10 * 1000);
		applyac.applyPriceFilter();
		applyac.applyaRatingFilter();
		hotelname.clickAndValidateHotelName();

	}
}
