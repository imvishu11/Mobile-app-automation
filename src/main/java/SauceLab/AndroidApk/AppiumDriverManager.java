package SauceLab.AndroidApk;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import io.appium.java_client.android.AndroidDriver;

/**
 * The class {@link AppiumDriverManager} holds static methods to appium driver.
 */
public class AppiumDriverManager {
	
	public AppiumDriverManager() {
		
	}
	
	/**
	 * Method to start the android appium driver and return instance of android driver.
	 */
	public static AndroidDriver startAppiumDriver() throws MalformedURLException {
		System.out.println("Initialising the appium driver.");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platform", "android");
		caps.setCapability("device", "Pixel 6");
		caps.setCapability("app", "/Users/vishalsingh/Downloads/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
		caps.setCapability("fullReset", true);
		caps.setCapability("noReset", false);
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("packageName", "com.swaglabsmobileapp");
		caps.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
		caps.setAcceptInsecureCerts(true);
		AndroidDriver driver = new AndroidDriver(AppiumServerManager.getServiceUrl(), caps);
		return driver;
	}	

}