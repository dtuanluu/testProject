package ldt.com.waiting;

import ldt.com.base.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Waiting {
    public static final Logger log = LoggerFactory.getLogger(Waiting.class);
    public WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10) ;

    public WebElement waitingByXpath(String name, String xpath);
    public WebElement waitingById(String name, String id);
    public WebElement waitingByClassName(String name, String className);
}
