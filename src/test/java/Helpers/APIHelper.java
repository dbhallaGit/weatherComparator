package Helpers;

import java.util.Properties;

import Hooks.Setup;
import PageObjects.Weatherpage;
import io.restassured.*;
import io.restassured.http.Headers;
import io.restassured.response.ValidatableResponse;

public class APIHelper {

	public static ValidatableResponse getResponseBody(String URI, String cityName, String APIKey) {
		 RestAssured.given().queryParam("q", cityName).queryParam("appid", APIKey)
			.get(URI).then().assertThat().statusCode(200);
		
		return RestAssured.given().queryParam("q", cityName).queryParam("appid", APIKey)
				.get(URI).then().log().body();

	}

	public static ValidatableResponse getResponseBody(String API_URL) {
		System.out.println("Response body: " + RestAssured.given().when().get(API_URL).then().log().body());
		return RestAssured.given().when().get(API_URL).then().log().body();

	}

	public static int getStatusCode(String API_URL) {

		return RestAssured.given().when().get(API_URL).getStatusCode();

	}
	
	public static int getStatusCode(String URI, String cityName, String APIKey) {
		return RestAssured.given().queryParam("q", cityName).queryParam("appid", APIKey)
				.get(URI).then().extract().statusCode();

	}
	
	public static Headers getHeaders(String URI, String cityName, String APIKey) {
		return RestAssured.given().queryParam("q", cityName).queryParam("appid", APIKey)
				.get(URI).then().extract().headers();

	}
	

	public static ValidatableResponse getResponseData(String API_URL) {

		return RestAssured.given().when().get(API_URL).then().log().all();

	}
}
