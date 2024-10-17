package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.base.BasePage;
import pages.popups.PrivacyPolicyPopup;
import utils.LogHelper;
import utils.WaitHelper;

import java.util.Objects;

public class VehicleSearchPage extends BasePage {

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

    @FindBy(xpath = "//button[span[contains(text(), 'Pre-Owned')]]")
    private WebElement preOwnedBtn;

    private final PrivacyPolicyPopup privacyPolicyPopup;
    private final WaitHelper waitHelper;

    public VehicleSearchPage(WebDriver driver) {
        super(driver);
        privacyPolicyPopup = new PrivacyPolicyPopup(driver);
        waitHelper = new WaitHelper(driver);
        LogHelper.logInfo(this.getClass(), "Initialized VehicleSearchPage.");
    }

    public void clickAcceptPrivacyPolicy() {
        LogHelper.logInfo(this.getClass(), "Waiting for Privacy Policy Popup...");
        LogHelper.logInfo(this.getClass(), "Accepting privacy policy.");
        privacyPolicyPopup.AcceptPrivacyPolicyPopup();
    }

    public void selectState(String state) {
        LogHelper.logInfo(this.getClass(), "Waiting for state dropdown to be visible...");
        waitHelper.waitForElementToBeVisible(locationDropdown);
        LogHelper.logInfo(this.getClass(), "Selecting state: " + state);
        Select stateDropdown = new Select(locationDropdown);
        Assert.assertTrue(locationDropdown.isDisplayed(), "The state dropdown is not visible.");
        stateDropdown.selectByVisibleText(state);
        LogHelper.logInfo(this.getClass(), "State selected: " + state);
    }

    public void enterPostalCode(String postal) {
        LogHelper.logInfo(this.getClass(), "Waiting for postal code field to be visible...");
        LogHelper.logInfo(this.getClass(), "Entering postal code: " + postal);
        sendKeysSlowly(postalCode, postal, 200);  // Test case failing because of strange behavior of application
        LogHelper.logInfo(this.getClass(), "Postal code entered: " + postal);
    }

    public void clickPurposeRadioBtn(String purpose) {
        LogHelper.logInfo(this.getClass(), "Waiting for purpose radio buttons...");
        LogHelper.logInfo(this.getClass(), "Clicking purpose radio button: " + purpose);
        if (Objects.equals(purpose, "Private")) {
            purposePrivateRadioBtn.click();
            LogHelper.logInfo(this.getClass(), "Private purpose selected.");
        } else if (Objects.equals(purpose, "Business")) {
            purposeBusinessRadioBtn.click();
            LogHelper.logInfo(this.getClass(), "Business purpose selected.");
        }
    }

    public void clickContinueBtn() {
        LogHelper.logInfo(this.getClass(), "Waiting for continue button to be clickable...");
        LogHelper.logInfo(this.getClass(), "Clicking continue button.");
        continueBtn.click();
    }

    public void clickFilterBtn() {
        LogHelper.logInfo(this.getClass(), "Waiting for filter button to be clickable...");
        LogHelper.logInfo(this.getClass(), "Clicking filter button.");
        filterBtn.click();
    }

    public void clickPreOwnedBtn() {
        LogHelper.logInfo(this.getClass(), "Waiting for Pre-Owned button to be clickable...");
        waitHelper.waitForElementToBeClickable(preOwnedBtn);
        LogHelper.logInfo(this.getClass(), "Clicking Pre-Owned button.");
        preOwnedBtn.click();
    }
}
