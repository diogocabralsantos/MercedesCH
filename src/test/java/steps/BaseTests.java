package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import utils.LogHelper;

import java.util.concurrent.TimeUnit;

public class BaseTests {
    WebDriver driver;
    public final static int TIMEOUT = 5;

    @Before
    public void setUp() {
        LogHelper.logInfo(this.getClass(), "Starting test setup...");
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        LogHelper.logInfo(this.getClass(), "Test setup complete. Browser launched and maximized.");
    }

   @After
    public void teardown() {
        DriverManager.closeDriver();
    }
}
