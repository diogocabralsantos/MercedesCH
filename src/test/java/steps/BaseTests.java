package steps;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class BaseTests {
    WebDriver driver;
    public final static int TIMEOUT = 5;

    @Before
    public void setUp() {

    }

   /* @After
    public void teardown() {
        DriverManager.closeDriver();
    }*/
}
