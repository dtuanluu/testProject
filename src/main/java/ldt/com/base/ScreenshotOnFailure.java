package ldt.com.base;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


public class ScreenshotOnFailure implements MethodRule {
    private WebDriver driver ;
    private final static Logger log = LoggerFactory.getLogger(ScreenshotOnFailure.class);

    public ScreenshotOnFailure(WebDriver driver) {
        this.driver = driver;
    }

    public Statement apply (final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                    log.error("will create a screenshot, the message : {}", t.getMessage());
                    captureScreenshot(frameworkMethod.getName());
                    throw t;
                }
            }

            public void captureScreenshot(String fileName) {
                log.info("capturing screenshot");
                log.info("current url : {}", driver.getCurrentUrl());
                fileName += "." + UUID.randomUUID().toString();
                String pathName = System.getProperty("user.dir") + "/target/";
                Shutterbug.shootPage(driver, Capture.FULL_SCROLL).withName(fileName).save(pathName);
                log.info("capturing screenshot. done. {}", pathName + fileName + ".png");
            }
        };

    }


}

/**
https://assertthat.github.io/selenium-shutterbug/
compare screenshot taken with the expected one with specified deviation rate (for example 0.1 represents that if image differences are less than 10% the images are considered to be equal):
Shutterbug.shootPage(driver).equals(otherImage,0.1);
 */