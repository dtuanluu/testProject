package ldt.com.waiting;

import ldt.com.exception.ApplicationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WaitPresence implements Waiting {

    @Override
    public WebElement waitingByXpath(String name, String xpath) {
        try {
            log.info("Wait clickable for element : {}, xpath : {} ", name, xpath);
            WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            log.info("Wait clickable for element : {}, xpath : {}. done", name, xpath);
            return webElement;
        } catch (Exception e) {
            throw new ApplicationException("failed waiting clickable", name, xpath);
        }
    }

    @Override
    public WebElement waitingById(String name, String id) {
        try {
            log.info("Wait clickable for element : {}, id : {} ", name, id);
            WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
            log.info("Wait clickable for element : {}, id : {}. done", name, id);
            return webElement;
        } catch (Exception e) {
            log.error("failed to wait for clickable : {}, id : {}", name , id);
            throw new ApplicationException("failed waiting clickable", name, id);
        }
    }

    @Override
    public WebElement waitingByClassName(String name, String className) {
        try {
            log.info("Wait clickable for element : {}, className : {} ", name, className);
            WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
            log.info("Wait clickable for element : {}, className : {}. done", name, className);
            return webElement;
        } catch (Exception e) {
            throw new ApplicationException("failed waiting clickable", name, className);
        }
    }
}
