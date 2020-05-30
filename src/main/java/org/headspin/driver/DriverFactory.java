package org.headspin.driver;

import org.headspin.utils.Logger;

public class DriverFactory {

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager = null;

        switch (type) {
            case CHROME:
                driverManager = new ChromeManager();
                break;
            default:
                Logger.log("Defaulting driver not set");
        }
        return driverManager;
    }
}
