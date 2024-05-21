package SauceLab.AndroidApk;

import java.net.MalformedURLException;
import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import io.appium.java_client.android.AndroidDriver;

import org.testng.Assert;

/**
 * The class {@link SauceLabsTest} is test runner class which holds tests.
 */
public class SauceLabsTest {
	
	AndroidDriver driver;
	LoginScreen loginScreen;
	ProductsGalaryScreen productsGalaryScreen;
	
	@BeforeMethod
	public void InitialiseDriver() throws MalformedURLException {
		AppiumServerManager.startServerUsingAnyFreePort();
		driver = AppiumDriverManager.startAppiumDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		loginScreen = new LoginScreen(driver);
		productsGalaryScreen = new ProductsGalaryScreen(driver);
	}

	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void TC_SauceLabs_01_Login_Scroll_AddInCart() {
		loginScreen.loginToTheApp("standard_user", "secret_sauce");
		productsGalaryScreen.addFirstItemInTheCart();
		productsGalaryScreen.scrollOnProductGalaryScreen();
		Assert.assertTrue(productsGalaryScreen.isItemAddedInTheCart(), "The item is not added in the cart.");
	}
	
	@AfterMethod
	public void quitDriver() {
		if (driver != null) {
            driver.quit();
        }
		AppiumServerManager.stopServer();
	}

}