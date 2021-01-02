package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/feature",
                glue = "steps",
                monochrome = true)

public class PayStackRunnerTest extends AbstractTestNGCucumberTests {
}
