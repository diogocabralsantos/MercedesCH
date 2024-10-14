package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/features"},
        glue = {"steps"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html"
        })

public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}