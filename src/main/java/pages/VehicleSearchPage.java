package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.base.BasePage;
import utils.DriverManager;

import java.util.Objects;

public class VehicleSearchPage extends BasePage {
    WebDriver driver;

    @FindBy(xpath = "//label[contains(text(), '* Your state')]/following::select[1]")
    private WebElement locationDropdown;

    @FindBy(xpath = "//*[@aria-labelledby='postal-code-hint']")
    private WebElement postalCode;

    @FindBy(xpath = "//input[@name='registration-type' and @value='P']/following-sibling::div")
    private WebElement purposePrivateRadioBtn;

    @FindBy(xpath = "//input[@name='registration-type' and @value='B']/following-sibling::div")
    private WebElement purposeBusinessRadioBtn;

    @FindBy(css = "[data-test-id='state-selected-modal__close']")
    private WebElement continueBtn;

    @FindBy(css = ".filter-toggle")
    private WebElement filterBtn;


    public VehicleSearchPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    // Method for selectState
    public void selectState(String state) {
        Select stateDropdown = new Select(locationDropdown);
        Assert.assertTrue(locationDropdown.isDisplayed(), "O dropdown de estado não está visível.");
        stateDropdown.selectByVisibleText(state);
    }

    public void enterPostalCode(String postal) {
        sendKeysSlowly(postalCode, postal, 200); // Test case failing because of strange behavior of application

    }

    public void clickPurposeRadioBtn(String purpose) {
        if(Objects.equals(purpose, "Private")){
            purposePrivateRadioBtn.click();
        }else if(Objects.equals(purpose, "Business")){
            purposeBusinessRadioBtn.click();
        }
    }

    public void clickContinueBtn() {
        continueBtn.click();
    }
    public void clickFilterBtn() {
        filterBtn.click();
    }

}
