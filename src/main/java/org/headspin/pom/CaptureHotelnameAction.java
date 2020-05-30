package org.headspin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CaptureHotelnameAction {

	private WebDriver pageDriver;

	public void clickAndValidateHotelName() {
		String str = pageDriver
				.findElement(By.xpath("//div[@id='Listing_hotel_4']//a//p[@id='hlistpg_hotel_name']/span[1]"))
				.getText();
		System.out.println("validated hotel name" + str);
		pageDriver
				.findElement(By.xpath(
						"//div[@id='Listing_hotel_4']//div[contains(@class,'makeFlex flexOne padding20 relative')]"))
				.click();

	}

}
