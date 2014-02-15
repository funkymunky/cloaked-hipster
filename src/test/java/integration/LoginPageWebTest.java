package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.LoginPage;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class LoginPageWebTest {

    private static WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://localhost:8080/HelloWorld/login");
        this.loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }

    @Test
    public void testThatLoginControlsExist() throws Exception {
        WebElement userName = loginPage.getUserName();
        WebElement password = loginPage.getPassword();
        assertNotNull(userName);
        assertNotNull(password);
    }

    @Test
    public void cannotLogInWithWrongPassword() throws InterruptedException {
        loginPage.sendLoginFor("admin", "password");
        String errorText = loginPage.getErrorText();
        assertThat(errorText, is("Login failed"));
    }

    @Test
    public void testSuccessfulLogin() throws Exception {
        loginPage.sendLoginFor("marco", "123");
        String message = loginPage.getWelcomeText();
        assertThat(message.contains("Welcome back marco"), is(true));
    }
}
