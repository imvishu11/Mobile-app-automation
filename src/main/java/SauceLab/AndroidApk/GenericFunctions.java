package SauceLab.AndroidApk;

import com.google.common.collect.ImmutableMap;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

/**
 * The class {@link GenericFunctions} holds static methods to which is generic to the application.
 */
public class GenericFunctions {

	public GenericFunctions() {
	}

	/** Checks the presence of element ***/
	public static boolean isElementPresent(AndroidDriver driver, By xpath) {
		System.out.println("Checking presence of element!");
		try {
			driver.findElement(xpath);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Scroll element into view
	 * 
	 * @param androidDriver: instance of android driver
	 * @param selector:      Text value of the element that needs to be scrolled
	 *                       into view as a String
	 **/
	public static void scrollElementIntoView(final AndroidDriver androidDriver, final String selector) {
		String selectorString = "new UiSelector().text(\"" + selector + "\")";
		androidDriver.executeScript("mobile: scroll",
				ImmutableMap.of("strategy", "-android uiautomator", "selector", selectorString));
	}

}