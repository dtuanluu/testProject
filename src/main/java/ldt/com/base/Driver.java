package ldt.com.base;

import com.google.common.io.Resources;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;


/**
 * @author ldt
 * @date 25.03.21
 */
public class Driver {
    private static final Logger log = LoggerFactory.getLogger(Driver.class);
    private static RemoteWebDriver driver = null;
    public static boolean isLoggedIn = false;

    public static void setDriver() {
        URL chromeDriverUrl = Resources.getResource("driver/chromedriver");
        System.setProperty("webdriver.chrome.driver", chromeDriverUrl.getPath());
        String file = chromeDriverUrl.getFile();
        new File(file).setExecutable(true);
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        if (System.getProperty("doScreen") != null) {
            boolean doScreen = Boolean.parseBoolean(System.getProperty("doScreen"));
            if (doScreen) {
                log.info("will position screen");
                driver.manage().window().setPosition(new Point(1500, -250));
            }
        } else {
            log.info("will position screen");
            driver.manage().window().setPosition(new Point(1500, -250));

        }

        driver.manage().window().setSize(new Dimension(1300, 1370));


        ConfigReader.getInstance();

    }


    public static RemoteWebDriver getDriver() {
        if (driver != null) {
            SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
            if (sessionId == null) {
                setDriver();
            }
        }
        if (driver == null) {
            setDriver();
        }
        return driver;
    }


    public static void closeDriver() {
        driver.quit();
    }


}
