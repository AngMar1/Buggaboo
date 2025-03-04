package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/features/login.feature",
                    "src/main/resources/features/cart.feature",
                    "src/main/resources/features/search.feature"
        },
        glue = {"stepdefinitions"}, // Ensure this matches the package with step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class RunCucumberTest {
}
