package SauceDemo2.SauceDemo2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppTest {
	 WebDriver driver;
	    WebDriverWait wait;

	    @BeforeMethod
	    public void setUp() {

	        // Launch Chrome
	        driver = new ChromeDriver();

	        // Maximize browser
	        driver.manage().window().maximize();

	        // Implicit wait
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        // Explicit wait object
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Open SauceDemo
	        driver.get("https://www.saucedemo.com/");
	    }

	    @Test
	    public void completePurchase() {

	        // =========================
	        // 1. LOGIN
	        // =========================

	        driver.findElement(By.id("user-name"))
	                .sendKeys("standard_user");

	        driver.findElement(By.id("password"))
	                .sendKeys("secret_sauce");

	        driver.findElement(By.id("login-button"))
	                .click();


	        // =========================
	        // 2. VERIFY PRODUCTS PAGE
	        // =========================

	        String productPageTitle =
	                driver.findElement(By.className("title"))
	                .getText();

	        Assert.assertEquals(productPageTitle, "Products");


	        // =========================
	        // 3. ADD PRODUCTS TO CART
	        // =========================

	        driver.findElement(
	                By.id("add-to-cart-sauce-labs-backpack"))
	                .click();

	        driver.findElement(
	                By.id("add-to-cart-sauce-labs-bike-light"))
	                .click();


	        // =========================
	        // 4. OPEN CART
	        // =========================

	        driver.findElement(By.className("shopping_cart_link"))
	                .click();


	        // =========================
	        // 5. VERIFY CART
	        // =========================

	        Assert.assertTrue(
	                driver.findElement(
	                        By.xpath("//div[text()='Sauce Labs Backpack']"))
	                .isDisplayed());

	        Assert.assertTrue(
	                driver.findElement(
	                        By.xpath("//div[text()='Sauce Labs Bike Light']"))
	                .isDisplayed());


	        // =========================
	        // 6. CHECKOUT
	        // =========================

	        driver.findElement(By.id("checkout"))
	                .click();


	        // =========================
	        // 7. ENTER CUSTOMER DETAILS
	        // =========================

	        driver.findElement(By.id("first-name"))
	                .sendKeys("Rasmi");

	        driver.findElement(By.id("last-name"))
	                .sendKeys("Test");

	        driver.findElement(By.id("postal-code"))
	                .sendKeys("682001");

	        driver.findElement(By.id("continue"))
	                .click();


	        // =========================
	        // 8. VERIFY CHECKOUT OVERVIEW
	        // =========================

	        String overviewTitle =
	                driver.findElement(By.className("title"))
	                .getText();

	        Assert.assertEquals(
	                overviewTitle,
	                "Checkout: Overview");


	        // =========================
	        // 9. FINISH ORDER
	        // =========================

	        driver.findElement(By.id("finish"))
	                .click();


	        // =========================
	        // 10. VERIFY ORDER SUCCESS
	        // =========================

	        String successMessage =
	                driver.findElement(
	                        By.className("complete-header"))
	                .getText();

	        Assert.assertEquals(
	                successMessage,
	                "Thank you for your order!");
	    }


	    @AfterMethod
	    public void tearDown() {

	        // Close browser
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}
