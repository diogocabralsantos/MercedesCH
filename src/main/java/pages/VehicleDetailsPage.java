package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;
import pages.popups.PrivacyPolicyPopup;
import utils.DriverManager;

public class VehicleDetailsPage extends BasePage {
    @FindBy(xpath = "//li[contains(@data-test-id,'dcp-vehicle-details-list-item-modelYear')]//span[contains(@class,'value')]")
    WebElement modelYear;

    @FindBy(xpath = "//li[contains(@data-test-id,'dcp-vehicle-details-list-item-engineNumber')]//span[contains(@class,'value')]")
    WebElement engineNumber;

    @FindBy(css = "[data-test-id='dcp-cars-buy-box__main-cta-leadform']")
    WebElement speakToExpertBtn;

    private static final String HOST1_SELECTOR = "#aemLeadForm";
    private static final String HOSTFIRSTNAME_SELECTOR = "#firstnameinputfield";
    private static final String HOSTLASTNAME_SELECTOR = "#lastnameinputfield_copy";
    private static final String HOSTZIPCODE_SELECTOR = "#zipCodeinputfield_328747616";
    private static final String HOSTMOBILEPHONE_SELECTOR = "#mobilePhoneinputfield_1352242670";
    private static final String HOSTEMAILSELECTOR = "#emailinputfield_2052579168";
    private static final String HOSTSUBMITBTN_SELECTOR = ".form-formstep-cta__button.wb-button.hydrated";

    private String firstnameSelector =  "input[name='firstname']";
    private String lastNameSelector =  "input[name='lastname']";
    private String zipCodeSelector =  "input[name='zipCode']";
    private String mobilePhoneSelector =  "input[name='mobilePhone']";
    private String emailSelector = "input[name='email']";
    private String submitBtnSelector =  ".button";
    private String emailErrorMsgSelector =  "#errorMessage-emailinputfield_2052579168";

    public VehicleDetailsPage() {
    }

    public void getModelYear() {
        //scrollToElement(modelYear);
        modelYear.getText();
        System.out.println(modelYear.getText());
    }

    public void getEngineNumber() {
        //scrollToElement(engineNumber);
        engineNumber.getText();
        System.out.println(engineNumber.getText());
    }

    public void clickSpeakToExpertBtn() {
        //scrollToElement(speakToExpertBtn);
        speakToExpertBtn.click();
    }

    public void enterFirstName(String firstName) throws InterruptedException {
        WebElement firstNameField = findNestedShadowDom(HOST1_SELECTOR, HOSTFIRSTNAME_SELECTOR, firstnameSelector);
        firstNameField.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        WebElement lastNameField = findNestedShadowDom(HOST1_SELECTOR, HOSTLASTNAME_SELECTOR, lastNameSelector);
        lastNameField.sendKeys(lastName);
    }
    public void enterZipCode(String zipCode) {
        WebElement zipCodeField = findNestedShadowDom(HOST1_SELECTOR, HOSTZIPCODE_SELECTOR, zipCodeSelector);
        zipCodeField.sendKeys(zipCode);
    }
    public void enterMobilePhone(String mobilePhone) {
        WebElement mobilePhoneField = findNestedShadowDom(HOST1_SELECTOR, HOSTMOBILEPHONE_SELECTOR, mobilePhoneSelector);
        mobilePhoneField.clear();
        mobilePhoneField.sendKeys(mobilePhone);
    }
    public void enterEmail(String email) {
        WebElement emailField = findNestedShadowDom(HOST1_SELECTOR, HOSTEMAILSELECTOR, emailSelector);
        emailField.sendKeys(email);
    }
    public void clickSubmitBtn() throws InterruptedException {
        WebElement submitBtn = findNestedShadowDom(HOST1_SELECTOR, HOSTSUBMITBTN_SELECTOR, submitBtnSelector);
        scrollToElement(submitBtn);
        forceAClick(submitBtn);
    }
    public void getEmailErrorMsg() {
        WebElement emailErrorMessage = findNestedShadowDom(HOST1_SELECTOR, emailErrorMsgSelector);
        if(emailErrorMessage.isDisplayed()) {
            emailErrorMessage.getText();
        }

    }
}
