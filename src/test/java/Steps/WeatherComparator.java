package Steps;

import Helpers.APIHelper;
import Helpers.Weather;
import PageObjects.Homepage;
import PageObjects.Weatherpage;
import PageObjects.weatherAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class WeatherComparator {

	Homepage homepage = new Homepage();
	Weatherpage weatherpage = new Weatherpage();
	Weather weatherFromUi,weatherFromAPI;
	

	public WeatherComparator() {

	}

	public WeatherComparator(Homepage homepage, Weatherpage weatherpage) {
		this.homepage = homepage;
		this.weatherpage = weatherpage;

	}

	@Given("User is on {string} website")
	public void user_is_on_website(String string) throws InterruptedException {
		
		homepage.VerfiyPageTitle(string);

	}

	@Given("Navigate to weather page")
	public void navigate_to_weather_page() throws Exception {
		homepage.goToWeatherPage();

	}

	@Then("use pin location to select a city {string}")
	public void use_pin_location_to_select_a_city(String string) throws Exception {
		weatherpage.clearDefaultCheckedCities();
		weatherpage.setCityName(string);
		weatherpage.SearchAndSelectCity(weatherpage.getCityName());
		

	}

	@Then("verify same City appears on Map with temperature info")
	public void verify_same_city_appears_on_map_with_temperature_info() {
		weatherpage.verfiyCityDisplayed();
		weatherpage.getTemprature();

	}

	@Then("verify weather details reveals on selecting the city")
	public void verify_weather_details_reveals_on_selecting_the_city() throws Exception {
		weatherpage.verfiyCityDisplayed();
		weatherpage.getTemprature();
		weatherpage.selectCityOnMap();
		weatherpage.verfiyWeatherDetailsRevealed();
		weatherFromUi=weatherpage.getWeatherDetails();		
		weatherpage.verfiryWeatherDisplayedOnUI();
		

	}

	@Then("compare the Weather information from website and API response is similar as per given variance")
	public void compare_the_weather_information_from_website_and_api_response_is_similar_as_per_given_variance() {
		
		weatherFromAPI=weatherAPI.getWeatherFromAPI(weatherpage.getCityName());
		

	}

}
