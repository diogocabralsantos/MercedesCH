package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utils.*;

public class Hooks {

    public static WebDriver driver;
    protected WaitHelper waitHelper;

    private String vehicleDetailsFile = ConfigReader.getProperty("filePath");

    @Parameters("browser")
    @Before
    public void setUp() {
        LogHelper.setLogFileName();
        LogHelper.logInfo(this.getClass(), "Clean past files...");
        IOFileHelper.clearFile(vehicleDetailsFile);

        LogHelper.logInfo(this.getClass(), "Starting test setup...");
        driver = DriverManager.getDriver();

        if (driver == null) {
            throw new NullPointerException("Driver is not initialized!");
        }

        driver.manage().window().maximize();
        waitHelper = new WaitHelper(driver);

        LogHelper.logInfo(this.getClass(), "Test setup complete. Browser launched and maximized.");
    }

    @After
    public void teardown() {
        if (driver != null) {
            DriverManager.closeDriver();
            LogHelper.logInfo(this.getClass(), "Browser closed.");
            IOFileHelper.clearFile("");
        } else {
            LogHelper.logWarn(this.getClass(), "Driver was null, skipping quit.");
        }
    }
}
