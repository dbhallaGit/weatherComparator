package Helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Hooks.Setup;

public class WebUIHelpers {

	public static WebDriver driver;
	WebDriverWait wait  = new WebDriverWait(driver, Integer.parseInt(Setup.prop.getProperty("WebElementWaitTime")));

	public boolean isElementVisible(WebElement ele) {
		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickElement(WebElement ele) throws Exception {
		try {
			
			wait.until(ExpectedConditions.elementToBeClickable(ele)).click();

		} catch (Exception e) {
			throw new Exception("Element not found" + e.getStackTrace());
		}

	}

	public void clickElementJS(WebElement ele) throws Exception {
		if(wait.until(ExpectedConditions.elementToBeClickable(ele)) != null) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0]. click();", ele);
		}
		else
			throw new Exception("Element not found" );
			
	}

	public String getTitle() {

		return driver.getTitle();

	}
	
	public Boolean CompareTemp(int tempInDegrees, int tempInF) {	
		System.out.println("Temp in degree: " + tempInDegrees+ " into F: "+((tempInDegrees*1.8)+32) );
		System.out.println("temp in F: "+ tempInF);
		if((float)tempInF==Math.round((float)((tempInDegrees*1.8)+32))) 			
				return true;
		else 
			return false;
	}

}
