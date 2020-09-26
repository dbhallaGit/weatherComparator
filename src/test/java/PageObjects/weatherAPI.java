package PageObjects;

import Helpers.APIHelper;
import Helpers.Weather;
import Hooks.Setup;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class weatherAPI extends APIHelper {

	static String APIKey=Setup.prop.getProperty("API_Key");
	static String API_URL="http://"+Setup.prop.getProperty("URI_CityName_").replace("{your api key}",APIKey);
	static String URI="http://"+Setup.prop.getProperty("URI");
	
	static ValidatableResponse ResponseData;
	private static int statusCode;
	private static ValidatableResponse ResponseBody;

	public static Weather getWeatherFromAPI( String CityName) {
		statusCode=getStatusCode(URI, CityName, APIKey);		
		System.out.println("Status code: "+ statusCode);
		ResponseBody=getResponseBody(URI, CityName, APIKey );
		System.out.println(ResponseBody);
		//ResponseBody.body("name");
		return null;
	}



	

	
		
		
		
	}
	

