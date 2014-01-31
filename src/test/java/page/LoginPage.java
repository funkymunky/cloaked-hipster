package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.WebTestUtil;

public class LoginPage extends Page {

    @FindBy(id = "j_username")
    private WebElement usernameField;

    @FindBy(id = "j_password")
    private WebElement passwordField;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(className = "error")
    private WebElement errorText;

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
        return errorText.getText();
    }

    public WebElement getUserName() {
        return usernameField;
    }

    public WebElement getPassword() {
        return passwordField;
    }
}
