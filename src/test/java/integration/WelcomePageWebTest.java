package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.LoginPage;
import page.Page;
import page.WelcomePage;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class WelcomePageWebTest {

    private static WebDriver driver;
    private WelcomePage welcomePage;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://localhost:8080/HelloWorld");
        this.welcomePage = WelcomePage.open(driver);
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }

    @Test
    public void testThatIndexpageDisplaysCorrectText() throws Exception {
        String message = "Hello, world!";
        String homePageText = driver.findElement(By.xpath("//div[@class='hero-unit']")).getText();
        assertThat(homePageText.contains(message), is(true));
    }

    @Test
    public void testNavigationToLoginPage() throws Exception {
        Page loginPage = welcomePage.clickLinkWithText("Login");
        System.out.println("loginPage = " + loginPage.getTitle());
        assertThat("Login Page".equals(loginPage.getTitle()), is(true));
    }
}
