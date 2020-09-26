package PageObjects;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Helpers.Weather;
import Helpers.WebUIHelpers;
import Hooks.Setup;

public class Weatherpage extends WebUIHelpers {

	public Weatherpage() {
		PageFactory.initElements(driver, this);
	}

	By defaultCheckedCities = By
			.xpath("//div[@class='message']/label/input[@class='defaultChecked'][@checked='checked']");

	@FindBy(how = How.XPATH, using = "//*[@class='searchContainer']/input")
	private WebElement SearchBox;

	@FindBy(how = How.XPATH, using = "//*[not(contains(@Style,'display: none;'))]/label/input")
	private WebElement searchedCity;

	@FindBy(how=How.CSS,using = ".outerContainer")
	private WebElement cityDisplayed;
	
	@FindBy(how=How.CSS,using=".temperatureContainer")
	WebElement CityPresent;
	
	@FindBy(how=How.XPATH,using = "//*[@class='temperatureContainer']/*[@class='tempRedText']")
	private WebElement CeliusTemp;
	
	@FindBy(how=How.XPATH,using = "//*[@class='temperatureContainer']/*[@class='tempWhiteText']")
	private WebElement FahrenheitTemp;

	private int tempCelcius;
	
	private int tempFahrenheit;
	
	@FindBy(how = How.CSS,using = ".leaflet-popup")
	private WebElement WeatherPopup;
	
	@FindBy(how=How.XPATH,using = "//div[@class='leaflet-popup-content']/div/*/*[contains(text(),'Condition')]")
	private WebElement UI_Condition;
	
	@FindBy(how=How.XPATH,using = "//div[@class='leaflet-popup-content']/div/*/*[contains(text(),'Humidity')]")
	private WebElement UI_Humidity;
	
	@FindBy(how=How.XPATH,using = "//div[@class='leaflet-popup-content']/div/*/*[contains(text(),'Temp in Degrees')]")
	private WebElement UI_TempInDegrees;
	
	@FindBy(how=How.XPATH,using = "//div[@class='leaflet-popup-content']/div/*/*[contains(text(),'Temp in Fahrenheit')]")
	private WebElement UI_TempInF;
	
	public String cityName;
	
	public String  Condition, Humidity;
	public int TempInDegrees,TempInF;
	
	Weather weatherFromUi;
	
	
	
	public int getTempInDegrees() {
		return TempInDegrees;
	}

	public void setTempInDegrees(int tempInDegrees) {
		TempInDegrees = tempInDegrees;
	}

	public int getTempInF() {
		return TempInF;
	}

	public void setTempInF(int tempInF) {
		TempInF = tempInF;
	}

	public int getTempFahrenheit() {
		return tempFahrenheit;
	}

	public void setTempFahrenheit(int tempFahrenheit) {
		this.tempFahrenheit = tempFahrenheit;
	}

	public int getTempCelcius() {
		return tempCelcius;
	}

	public void setTempCelcius(int tempCelcius) {
		this.tempCelcius = tempCelcius;
	}

	public void clearDefaultCheckedCities() throws Exception {
		Thread.sleep(5000);
		List<WebElement> DefaultCheckedCities = driver.findElements(
				By.xpath("//div[@class='message']/label/input[@class='defaultChecked'][@checked='checked']"));
		
		if(DefaultCheckedCities.size()==0)
			throw new Exception("Not able to clear default selected cities");
		
		for (WebElement webElement : DefaultCheckedCities) {
			clickElementJS(webElement);
			
		}

	}

	public void SearchAndSelectCity(String cityName) throws Exception {
		
		SearchBox.sendKeys(getCityName());
		clickElementJS(searchedCity);
		 setCityName(searchedCity.getAttribute("id"));

	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void verfiyCityDisplayed() {
				Assert.assertEquals(getCityName(), cityDisplayed.getAttribute("title"),"Same city not present");
		
	}

	public void getTemprature() {
		
		setTempCelcius(Integer.valueOf((CeliusTemp.getText().substring(0, 2))));
		
		setTempFahrenheit(Integer.valueOf((FahrenheitTemp.getText().substring(0, 2))));
		
		
		
	}

	public void selectCityOnMap() throws Exception {
		clickElementJS(CityPresent);
		
	}

	public void verfiyWeatherDetailsRevealed() {
		
		Assert.assertTrue(ExpectedConditions.visibilityOf(WeatherPopup)!=null);
				
		
	}
	
	
	public Weather getWeatherDetails() {
		
		
		Condition=UI_Condition.getText().replaceAll("Condition : ", "");
		Humidity=UI_Humidity.getText().split(":")[1].replace("%", "");
		setTempInDegrees(Integer.valueOf(UI_TempInDegrees.getText().split(":")[1].replace(" ", "")));
		setTempInF(Integer.valueOf(UI_TempInF.getText().split(":")[1].replace(" ", "")));
		
		Boolean CelciusToFarenheit=CompareTemp(getTempInDegrees(),getTempInF());
		
		if(CelciusToFarenheit)
			System.out.println("temp in celecius and Farenheit are same");
		else
			System.out.println("temp is not the same");
		
		
		weatherFromUi=new Weather(Condition,Humidity,getTempInDegrees());
		
		System.out.println("Condition is: "+weatherFromUi.getCondition());
		System.out.println("Humidity is: "+weatherFromUi.getHumidity());
		System.out.println("Temp is: "+weatherFromUi.getTempInDegrees());
		
		return weatherFromUi;
	}

	public void verfiryWeatherDisplayedOnUI() {
		Assert.assertEquals(getTempInDegrees(), getTempCelcius(), "C Temp different ");
		Assert.assertEquals(getTempInF(), getTempFahrenheit(), "F Temp different ");
		
	}

	
	
	
	
	

}
