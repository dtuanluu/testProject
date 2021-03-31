package ldt.com.page;

import ldt.com.base.ConfigReader;
import ldt.com.waiting.WaitClickable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ldt
 * @date 23.03.21
 */
public class Login {

    public static final Logger log = LoggerFactory.getLogger(Login.class);

    private static final String idUserName = "user-name";
    private static final String idPassword = "password";
    private static final String idLoginButton = "login-button";

    private WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = idUserName)
    public WebElement inputUsername;

    @FindBy(id = idPassword)
    public WebElement inputPassword;

    @FindBy(id = idLoginButton)
    public WebElement buttonLogin;

    public WebElement waitButtonLogin() {
        log.info("waiting until login button becomes clickable");
//        new WaitClickable().waitingById("idLoginButton", "adasdfasdfasdf");
        return new WaitClickable().waitingById("idLoginButton", idLoginButton);
    }


    public Login(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,30);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }


    public void gotoLogin() {
        driver.get(ConfigReader.getInstance().getValueByKey("applicationUrl"));
    }


    public void close() {
        driver.close();
    }


}
