package ldt.com.tester;

import ldt.com.base.Driver;
import ldt.com.base.ScreenshotOnFailure;
import ldt.com.base.SelBase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeTester extends SelBase {

    @Rule
    public ScreenshotOnFailure failure = new ScreenshotOnFailure(Driver.getDriver());

    private final static Logger log = LoggerFactory.getLogger(FeTester.class);


    @Test
    public void doSomething() {
        Assert.assertTrue(false);
    }

    @AfterClass
    public static void afterClass() {
        log.info("closing the driver");
        closeDriver();
    }

}
