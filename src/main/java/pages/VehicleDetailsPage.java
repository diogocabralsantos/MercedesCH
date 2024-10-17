package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import utils.ConfigReader;
import utils.IOFileHelper;
import utils.LogHelper;
import utils.WaitHelper;

public class VehicleDetailsPage extends BasePage {

    @FindBy(xpath = "//li[contains(@data-test-id,'dcp-vehicle-details-list-item-modelYear')]//span[contains(@class,'value')]")
    WebElement modelYear;

    @FindBy(xpath = "//li[contains(@data-test-id,'cp-vehicle-details-list-item-engineSizeInCcm')]//span[contains(@class,'value')]")
    WebElement engineSize;

    @FindBy(css = "[data-test-id='dcp-cars-buy-box__main-cta-leadform']")
    WebElement speakToExpertBtn;

    // Shadow DOM Selectors
    private static final String HOST1_SELECTOR = "#aemLeadForm";
    private static final String HOSTFIRSTNAME_SELECTOR = "#firstnameinputfield";
    private static final String HOSTLASTNAME_SELECTOR = "#lastnameinputfield_copy";
    private static final String HOSTZIPCODE_SELECTOR = "#zipCodeinputfield_328747616";
    private static final String HOSTMOBILEPHONE_SELECTOR = "#mobilePhoneinputfield_1352242670";
    private static final String HOSTEMAILSELECTOR = "#emailinputfield_2052579168";
    private static final String HOSTSUBMITBTN_SELECTOR = ".form-formstep-cta__button.wb-button.hydrated";

    private String firstnameSelector = "input[name='firstname']";
    private String lastNameSelector = "input[name='lastname']";
    private String zipCodeSelector = "input[name='zipCode']";
    private String mobilePhoneSelector = "input[name='mobilePhone']";
    private String emailSelector = "input[name='email']";
    private String submitBtnSelector = ".button";
    private String emailErrorMsgSelector = "#errorMessage-emailinputfield_2052579168";

    String filePath = ConfigReader.getProperty("filePath");

    private final WaitHelper waitHelper;

    public VehicleDetailsPage(WebDriver driver) {
        super(driver);
        this.waitHelper = new WaitHelper(driver);
    }

    public void getModelYear() {
        LogHelper.logInfo(this.getClass(), "Waiting for Model Year element...");
        LogHelper.logInfo(this.getClass(), "Getting Model Year...");
        String year = modelYear.getText();

        IOFileHelper.writeToFile(filePath,"Model Year: " + year);
        LogHelper.logInfo(this.getClass(), "Model Year: " + year);
    }

    public void getEngineSize() {
        LogHelper.logInfo(this.getClass(), "Waiting for Engine Number element...");
        LogHelper.logInfo(this.getClass(), "Getting Engine Number...");
        String engineS = engineSize.getText();
        IOFileHelper.writeToFile(filePath,"Engine Number: " + engineS);
        LogHelper.logInfo(this.getClass(), "Engine Number: " + engineS);
    }

    public void clickSpeakToExpertBtn() {
        LogHelper.logInfo(this.getClass(), "Waiting for Speak to Expert Button...");
        LogHelper.logInfo(this.getClass(), "Clicking Speak to Expert Button...");
        speakToExpertBtn.click();
        LogHelper.logInfo(this.getClass(), "Speak to Expert Button clicked.");
    }

    public void enterFirstName(String firstName) {
        LogHelper.logInfo(this.getClass(), "Waiting for first name field in Shadow DOM...");
        WebElement firstNameField = findNestedShadowDom(HOST1_SELECTOR, HOSTFIRSTNAME_SELECTOR, firstnameSelector);
        LogHelper.logInfo(this.getClass(), "Entering first name: " + firstName);
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        LogHelper.logInfo(this.getClass(), "Waiting for last name field in Shadow DOM...");
        WebElement lastNameField = findNestedShadowDom(HOST1_SELECTOR, HOSTLASTNAME_SELECTOR, lastNameSelector);
        LogHelper.logInfo(this.getClass(), "Entering last name: " + lastName);
        lastNameField.sendKeys(lastName);
    }

    public void enterZipCode(String zipCode) {
        LogHelper.logInfo(this.getClass(), "Waiting for zip code field in Shadow DOM...");
        WebElement zipCodeField = findNestedShadowDom(HOST1_SELECTOR, HOSTZIPCODE_SELECTOR, zipCodeSelector);
        LogHelper.logInfo(this.getClass(), "Entering zip code: " + zipCode);
        zipCodeField.sendKeys(zipCode);
    }

    public void enterMobilePhone(String mobilePhone) {
        LogHelper.logInfo(this.getClass(), "Waiting for mobile phone field in Shadow DOM...");
        WebElement mobilePhoneField = findNestedShadowDom(HOST1_SELECTOR, HOSTMOBILEPHONE_SELECTOR, mobilePhoneSelector);
        LogHelper.logInfo(this.getClass(), "Entering mobile phone: " + mobilePhone);
        mobilePhoneField.clear();
        mobilePhoneField.sendKeys(mobilePhone);
    }

    public void enterEmail(String email) {
        LogHelper.logInfo(this.getClass(), "Waiting for email field in Shadow DOM...");
        WebElement emailField = findNestedShadowDom(HOST1_SELECTOR, HOSTEMAILSELECTOR, emailSelector);
        LogHelper.logInfo(this.getClass(), "Entering email: " + email);
        emailField.sendKeys(email);
    }

    public void clickSubmitBtn() throws InterruptedException {
        LogHelper.logInfo(this.getClass(), "Waiting for Submit Button...");
        WebElement submitBtn = findNestedShadowDom(HOST1_SELECTOR, HOSTSUBMITBTN_SELECTOR, submitBtnSelector);
        LogHelper.logInfo(this.getClass(), "Clicking the Submit Button...");
        scrollToElement(submitBtn);
        forceAClick(submitBtn);
        LogHelper.logInfo(this.getClass(), "Submit Button clicked.");
    }

    public void getEmailErrorMsg() {
        LogHelper.logInfo(this.getClass(), "Waiting for email error message in Shadow DOM...");
        WebElement emailErrorMessage = findNestedShadowDom(HOST1_SELECTOR, emailErrorMsgSelector);
        waitHelper.waitForElementToBeVisible(emailErrorMessage);
        if (emailErrorMessage.isDisplayed()) {
            LogHelper.logInfo(this.getClass(), "Error Message displayed: " + emailErrorMessage.getText());
        }
    }
}
