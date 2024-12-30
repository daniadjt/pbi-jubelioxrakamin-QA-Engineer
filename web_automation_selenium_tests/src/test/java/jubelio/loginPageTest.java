package jubelio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class loginPageTest {
    WebDriver driver;

    @BeforeTest
    private void init() {
        System.setProperty("webdriver.chrome.driver", "/Users/daniadjatniko/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("https://app2.jubelio.com/login");
        driver.manage().window().maximize();
    }

    @Test (priority = 0)
    public void loginWithCorrectValues() {
        driver.findElement(By.name("email")).sendKeys("daniadjatniko1@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Validpassword123!");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/div/form/button")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://app2.jubelio.com/home/getting-started");
    }

    @Test (priority = 1)
    public void loginWithIncorrectValues() {
        driver.findElement(By.name("email")).sendKeys("daniadjatniko1@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Invalidpassword123");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/div/form/button")).click();
        Assert.assertEquals(driver.findElement(By.className("app-alert")).getText(), "Password atau email anda salah.");
    }

    @Test (priority = 2)
    public void loginWithNullValues() {
        driver.findElement(By.className("ladda-button")).click();
        String actualMessage = driver.findElement(By.className("app-alert")).getText();
        String expectedMessage = "marker \"Email harus diisi\"\nmarker \"Password harus diisi\"";
        Assert.assertEquals(actualMessage, expectedMessage, "Pesan error tidak sesuai");
    }

    @Test (priority = 3)
    public void loginWithUnregisteredEmail() {
        driver.findElement(By.name("email")).sendKeys("daniadjatniko@gmail.com");
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/div/form/button")).click();
        Assert.assertEquals(driver.findElement(By.className("app-alert")).getText(), "Format Email tidak valid.");
    }

    @Test (priority = 4)
    public void loginWithInvalidEmailFormat() {
        driver.findElement(By.name("email")).sendKeys("daniadjatniko1@.com");
        driver.findElement(By.name("password")).sendKeys("validpassword123");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/div/form/button")).click();
        Assert.assertEquals(driver.findElement(By.className("app-alert")).getText(), "Format Email tidak valid.");
    }

    @AfterTest
    public void closeBrowser() { driver.quit(); }
}