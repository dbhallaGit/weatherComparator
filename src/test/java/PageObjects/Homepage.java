package PageObjects;

import java.io.Console;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Helpers.WebUIHelpers;

public class Homepage extends WebUIHelpers {
	
	
	
	public Homepage() {
	
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(how = How.CLASS_NAME,using = "noti_wrap")
	WebElement SubscribeNewsAlertBox;
	
	@FindBy(how=How.XPATH,using = "//a[text()='No Thanks']")
	WebElement NotNowbtn;
	
	@FindBy(how = How.XPATH,using="//a[text()='WEATHER']")
	private WebElement weatherPage;

	@FindBy(how = How.ID,using = "h_sub_menu")
	private WebElement SubMenu;

	public void clearNewsAlertSubscription() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		if(isElementVisible(SubscribeNewsAlertBox)) {
			
			System.out.println("Alert box present");
			clickElementJS(NotNowbtn);
		}
		else
			System.out.println("Alert box not found");
		
	}

	public void goToWeatherPage() throws InterruptedException {
		try {
			clickElement(SubMenu);
			clickElement(weatherPage);
			
		} catch (Exception e) {
			clearNewsAlertSubscription();
			clickElement(SubMenu);
			clickElement(weatherPage);
		}
		
		
	}

	public void VerfiyPageTitle(String title) {
		//NDTV.com: Latest News, India News, Business, Cricket, Bollywood, Video & Breaking News
		
		System.out.println(getTitle());
		Assert.assertEquals(title, getTitle().split(":")[0]);
			
	}

	


}
