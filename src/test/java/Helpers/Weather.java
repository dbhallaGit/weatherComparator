package Helpers;

public class Weather {

	public Weather(String condition, String humidity, int tempInDegrees2) {
		this.Condition=condition;
		this.Humidity=humidity;
		this.TempInDegrees=tempInDegrees2;
		
	}
	
	String CityName, Condition, Humidity;
	int TempInDegrees;

	

	public String getCondition() {
		return Condition;
	}

	public void setCondition(String condition) {
		Condition = condition;
	}

	public String getHumidity() {
		return Humidity;
	}

	public void setHumidity(String humidity) {
		Humidity = humidity;
	}

	public int getTempInDegrees() {
		return TempInDegrees;
	}

	public void setTempInDegrees(int tempInDegrees) {
		TempInDegrees = tempInDegrees;
	}

	
	
	
	

}
