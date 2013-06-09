package page;

import net.hello.world.webutils.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.WebTestUtil;

/**
 * Created with IntelliJ IDEA.
 * User: Ayesha
 * Date: 10/04/13
 * Time: 8:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginPage extends Page {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "submit")
    private WebElement submitButton;
    private String errorText;

    public LoginPage(WebDriver driver) {
        super(driver, "login");
    }

    public static LoginPage open(WebDriver driver) {
        driver.get(WebTestUtil.CONTEXT);
        return new LoginPage(driver);
    }

    public LoginPage logInExpectingFailureAs(String username, String password) {
        sendLoginFor(username, password);
        return new LoginPage(getDriver());
    }

    public LoginPage sendLoginFor(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();
        return this;
    }

    public String getErrorText() {
        return errorText;
    }
}
