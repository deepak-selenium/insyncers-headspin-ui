package org.headspin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ApplyActions {
	private WebDriver pageDriver;

	public void applyPriceFilter() {
		String str = pageDriver
				.findElement(By.xpath("//div[contains(@class,'appliedFiltersContainer')]//li[1]//span[1]")).getText();

		String str1 = "1000-30000";
				
		Assert.assertEquals(str, str1);
		System.out.println("==verified filter option===");
	}

	public void applyaRatingFilter() {

		String str = pageDriver
				.findElement(By.xpath("//div[contains(@class,'appliedFiltersContainer')]//li[1]//span[2")).getText();

		String str1 = "4 & above (Very Good)";
		Assert.assertEquals(str, str1);
		System.out.println("==verified filter option===");

	}

}
