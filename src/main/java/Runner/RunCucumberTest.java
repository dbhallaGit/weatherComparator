package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "html:target/cucumber","json:target/cucumber.json"}, 
glue = "//WeatherComparator//src//test//java",
publish = true,
tags = "@Smoke",
features = "//WeatherComparator//src//test//java//Features")
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}

