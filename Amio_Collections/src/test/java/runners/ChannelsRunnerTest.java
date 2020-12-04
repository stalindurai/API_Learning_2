package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/features",
                glue = "steps",
                monochrome = true)

public class ChannelsRunnerTest extends AbstractTestNGCucumberTests {
}
