package config;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/it/resources/features", plugin = {"pretty", "json:target/cucumber-report.json"})
public class CucumberTest {

}
