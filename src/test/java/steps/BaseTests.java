package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class BaseTests {
    WebDriver driver;
    public final static int TIMEOUT = 5;

    @Before
    public void setUp() {

    }

   @After
    public void teardown() {
        DriverManager.closeDriver();
    }
}
