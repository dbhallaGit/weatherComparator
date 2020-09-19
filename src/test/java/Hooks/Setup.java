package Hooks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;

import Helpers.WebUIHelpers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

enum Browser{
	
	Chrome,
	Firefox;
	
	
}

public class Setup {
	public static Properties prop;
	
	
	@Before
	public void laundDriver() throws Exception{
		loadproperties();
		setUpDriver();
		String platform=prop.getProperty("WebApplication");
		
		if(platform.equalsIgnoreCase("NDTV"))
		launchWebApplication(prop.getProperty("NDTV_Url"));
		
		else 
			System.out.println("UI application URL not configured");
		
	}
	
	@After
	public void quit() {
		WebUIHelpers.driver.quit();
	}
	
	private void launchWebApplication(String URL) {
		WebUIHelpers.driver.get(URL);
		
	}
	public void loadproperties()  {
		prop=new Properties();
		try {
			prop.load(new FileInputStream("C:\\Users\\divesh.bhalla\\eclipse-workspace\\WeatherComparator\\src\\main\\resources\\Config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	public void setUpDriver() throws Exception{
		if(prop.getProperty("Browser").equals(Browser.Chrome.toString())) {
		WebDriverManager.chromedriver().version("85.0.4183.87").setup();	
		WebUIHelpers.driver=new ChromeDriver();
		WebUIHelpers.driver.manage().window().maximize();
		}
		else
			throw new Exception("Specify Browser in Config.property file");
		

}
}
			