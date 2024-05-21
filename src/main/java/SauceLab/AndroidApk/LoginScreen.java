package SauceLab.AndroidApk;

import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;

import io.appium.java_client.android.AndroidDriver;

/**
 * The class {@link LoginScreen} holds locators & methods specific to the Login Screen.
 */
public class LoginScreen {
	
	AndroidDriver driver;
	public LoginScreen(AndroidDriver driver) {
		this.driver = driver;
	}
	
	/*** Locators for Login screen ***/
	public By userNameTextField = AppiumBy.accessibilityId("test-Username");
	public By passwordTextField = AppiumBy.accessibilityId("test-Password");
	public By loginBtn = AppiumBy.accessibilityId("test-LOGIN");
	
	
	/** Methods for Login screen  
	 * 
	 * @param username: username as String
	 * @param password: password as String
	 * 
	 **/
	public void loginToTheApp(String username, String password) {
		System.out.println("Login to the app.");
		driver.findElement(userNameTextField).sendKeys(username);
		driver.findElement(passwordTextField).sendKeys(password);
		driver.findElement(loginBtn).click();	
	}
}