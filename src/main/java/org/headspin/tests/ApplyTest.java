package org.headspin.tests;

import org.headspin.pom.ApplyActions;
import org.headspin.pom.LandingPage;
import org.headspin.pom.Priceandratingfilter;
import org.testng.annotations.Test;

public class ApplyTest extends BaseTest {

	@Test(description = "Apply filter")
	public void applyFilter() throws InterruptedException {
		LandingPage landingPage = new LandingPage(chromeDriver);
		Priceandratingfilter fiter = new Priceandratingfilter();
		ApplyActions applyac = new ApplyActions();
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

	}

}
