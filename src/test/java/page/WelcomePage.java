package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.WebTestUtil;

public class WelcomePage extends Page {

    public WelcomePage(WebDriver driver) {
        super(driver, "welcome");
    }

    public static WelcomePage open(WebDriver driver) {
        driver.get(WebTestUtil.CONTEXT);
        return new WelcomePage(driver);
    }

    public Page clickLinkWithText(String linkText) {
        WebElement element = driver.findElement(By.linkText(linkText));
        element.click();
        return new Page(driver);
    }
}
