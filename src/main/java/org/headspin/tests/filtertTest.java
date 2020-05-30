package org.headspin.tests;

import org.headspin.pom.Priceandratingfilter;
import org.headspin.pom.LandingPage;
import org.testng.annotations.Test;

public class filtertTest extends BaseTest {
	@Test(description = "click four rating filter")
	public void clickFilter() throws InterruptedException {
		LandingPage landingPage = new LandingPage(chromeDriver);
		Priceandratingfilter fiter = new Priceandratingfilter();
		landingPage.clickLoginLink();
		landingPage.validateLoginPopUp();
		landingPage.enterUserName();
		landingPage.enterPassword();
		Thread.sleep(10 * 000);
		fiter.sliderAction();
		Thread.sleep(10 * 000);
		fiter.filterRating();

	}

}
