package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Parameters;

@CucumberOptions(tags = "", features = {"src/test/resources/features"},
        glue = {"steps"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html"
        })

public class CucumberTestRunner extends AbstractTestNGCucumberTests {
        @Parameters("browser")
        public static void setBrowser(String browser) {
                System.setProperty("browser", browser);
        }
}