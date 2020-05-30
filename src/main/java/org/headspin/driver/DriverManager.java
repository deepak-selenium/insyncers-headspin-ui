package org.headspin.driver;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    protected WebDriver localDriver;
    protected WebDriver cloudDriver;

    protected abstract void startCloudRun();

    protected abstract void startLocalRun();

    public WebDriver getLocalDriver() {
        startLocalRun();
        return localDriver;
    }

    public WebDriver getCloudDriver() {
        startCloudRun();
        return cloudDriver;
    }
}
