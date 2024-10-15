package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;
import utils.DriverManager;

public class UsedCarSearchResultsPage extends BasePage {
    WebDriver driver;

    public UsedCarSearchResultsPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }


}
