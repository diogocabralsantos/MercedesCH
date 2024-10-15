import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;
import utils.DriverManager;

public class VehicleDetailsPage extends BasePage {
    WebDriver driver;

    @FindBy(xpath = "//li[contains(@data-test-id,'dcp-vehicle-details-list-item-modelYear')]//span[contains(@class,'value')]")
    WebElement modelYear;

    @FindBy(xpath = "//li[contains(@data-test-id,'dcp-vehicle-details-list-item-engineNumber')]//span[contains(@class,'value')]")
    WebElement engineNumber;

    public VehicleDetailsPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void getModelYear() {
        modelYear.getText();
        System.out.println(modelYear.getText());
    }
    public void getEngineNumber() {
        engineNumber.getText();
        System.out.println(engineNumber.getText());
    }

}
