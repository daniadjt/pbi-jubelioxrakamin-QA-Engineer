package jubelio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class inventoryManagementTest {
    WebDriver driver;

    @BeforeTest
    private void init() {
        System.setProperty("webdriver.chrome.driver", "/Users/daniadjatniko/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("https://app2.jubelio.com/home/inventory/");
        driver.manage().window().maximize();
    }

    @Test (priority = 0)
    public void viewInventoryList() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[3]/div/div/div[2]/div/div")).isDisplayed(), "Inventory list is not displayed.");
    }

    @Test (priority = 1)
    public void clickAdjustInventoryButton() {
        WebElement adjustButton = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div/div/div/div[2]/div/div/div/div/div/div[1]/div[2]/div/button[2]"));
        adjustButton.click();
        WebElement adjustmentForm = driver.findElement(By.className("page-editor"));
        Assert.assertTrue(adjustmentForm.isDisplayed(), "Adjustment form is not displayed.");
    }

    @Test (priority = 2)
    public void searchListTest() {
        WebElement searchBox = driver.findElement(By.className("form-control"));
        searchBox.sendKeys("Rustic Pottery Mug - Pink Edition");
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div/div/div/div[2]/div/div/div/div/div/div[1]/div[1]/div/span[3]/button"));
        searchButton.click();
        WebElement firstItem = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[3]/div/div/div[2]/div/div/div[2]/div[1]/div/div[6]/div/div/span/div"));
        String itemName = firstItem.getText();
        Assert.assertTrue(itemName.contains("Rustic Pottery Mug - Pink Edition"), "The search result does not match the query.");
    }

    @AfterTest
    public void closeBrowser() { driver.quit(); }

}