package SauceLab.AndroidApk;

import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * The class {@link ProductsGalaryScreen} holds locators & methods specific to the product galary Screen.
 */
public class ProductsGalaryScreen {

	AndroidDriver driver;
	public ProductsGalaryScreen(AndroidDriver driver) {
		this.driver = driver;
	}
	
	/*** Locators for Product galary screen ***/
	
	public By firstItem = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]/android.widget.TextView");
	public By cartAdditionTxt = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.TextView");
	public By termsTxt = AppiumBy.xpath("//android.widget.ScrollView[@content-desc='test-PRODUCTS']//android.widget.TextView[@text='Terms of Service | Privacy Policy']");
	
	public final String scrollableContainerClass = "android.widget.ScrollView";
	public final String termsText = "Terms of Service | Privacy Policy";
	
	
	
	/*** Methods for Product galary screen ***/
	
	public void addFirstItemInTheCart() {
		System.out.println("Adding item to the cart!");
		driver.findElement(firstItem).click();
	}
	
	public boolean isItemAddedInTheCart() {
		return GenericFunctions.isElementPresent(driver, cartAdditionTxt);
	}
	
	public void scrollOnProductGalaryScreen() {
		GenericFunctions.scrollElementIntoView(driver, termsText);
	}	
}