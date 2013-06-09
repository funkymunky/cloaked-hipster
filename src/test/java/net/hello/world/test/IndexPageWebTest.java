package net.hello.world.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.LoginPage;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: Ayesha
 * Date: 30/03/13
 * Time: 7:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class IndexPageWebTest {

    private static WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://localhost:8080/HelloWorld");
    }

    @After
    public void tearDown() throws Exception {
//        driver.close();
    }

    @Test
    public void testThatIndexpageDisplaysCorrectText() throws Exception {
        String message = "Hello world";
        String homePageText = driver.findElement(By.xpath("//span[@class='something']")).getText();
        assertEquals(message, homePageText);
    }

//    @Test
//    public void testThatLinkNavigatesCorrectly() throws Exception {
//        WebElement accountsLink = driver.findElement(By.linkText("List accounts"));
//        accountsLink.click();
//        String accountsPageText = driver.findElement(By.xpath("//span[@class='mytest']")).getText();
//        assertEquals("Accounts page", accountsPageText);
//    }

    @Test
    public void testThatLoginControlsExist() throws Exception {
        WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        assertNotNull(username);
        assertNotNull(password);
    }

    @Test
    @DoNotCloseDriver
    public void cannotLogInWithWrongPassword() throws InterruptedException {
        LoginPage loginPage = LoginPage.open(driver); //.logInExpectingFailureAs("guest", "badguess");
        loginPage.sendLoginFor("admin", "password");
        assertThat(loginPage.getErrorText(), is("Your login attempt was not successful.\nPlease try again."));
    }
}
