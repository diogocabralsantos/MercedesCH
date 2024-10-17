package pages.popups;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;
import utils.LogHelper;

public class PrivacyPolicyPopup extends BasePage {
    private static final String HOST1_SELECTOR = "cmm-cookie-banner[class='hydrated']";
    private static final String HOSTACCEPTPOLICY_SELECTOR = "[data-test='handle-accept-all-button']";
    private String acceptPolicyBtn = ".button";

    public PrivacyPolicyPopup(WebDriver driver) {
        super(driver);
    }

    public void AcceptPrivacyPolicyPopup() {
        LogHelper.logInfo(this.getClass(), "Attempting to accept privacy policy...");
        WebElement acceptPolicy = findNestedShadowDom(HOST1_SELECTOR, HOSTACCEPTPOLICY_SELECTOR, acceptPolicyBtn);
        forceAClick(acceptPolicy);
        LogHelper.logInfo(this.getClass(), "Privacy policy accepted.");
    }
}
