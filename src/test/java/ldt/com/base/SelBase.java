package ldt.com.base;

import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * provides Login functionality
 */
public class SelBase {
    private static final Logger log = LoggerFactory.getLogger(SelBase.class);
    public static RemoteWebDriver driver = Driver.getDriver();
    public static boolean isLoggedIn = false;

    public static RemoteWebDriver getDriver() {
        return driver;
    }

    public static void goToStartPage() {
        String startUrl = "https://www.saucedemo.com/";
        log.info("going to start page. {}", startUrl);
        driver.get(startUrl);
    }


    private static int getScreenMaxUsableHeight() {
        Dimension dimVisibleArea = driver.manage().window().getSize();
        int height = dimVisibleArea.getHeight();
        log.info("max usable height : {}", height);
        return height;
    }

    @BeforeClass
    public static void beforeClass() {
        log.info("check login");
        if (!isLoggedIn) {
            log.info("continue with login");
            login();
            log.info("continue with login. done");
        } else {
            log.info("let's continue, we are logged in.");
        }
        driver.manage().window().setSize(new Dimension(1300, 1370));
    }

    public static void login() {
//        Login loginPage = new Login(driver);
//        loginPage.gotoLogin();
//        loginPage.inputUsername.sendKeys("standard_user");
//        loginPage.inputPassword.sendKeys("secret_sauce");
//        log.info("will submit username and password");
//        loginPage.waitButtonLogin().click();

    }

    public static void closeDriver() {
        log.info("will close the driver");
        Driver.closeDriver();
        log.info("will close the driver. done");
    }
}
