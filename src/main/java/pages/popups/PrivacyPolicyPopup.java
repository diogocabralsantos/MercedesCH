package pages.popups;

import org.openqa.selenium.WebElement;
import pages.base.BasePage;

public class PrivacyPolicyPopup extends BasePage {
    private static final String HOST1_SELECTOR = "cmm-cookie-banner[class='hydrated']";
    private static final String HOSTACCEPTPOLICY_SELECTOR = "[data-test='handle-accept-all-button']";

    private String acceptPolicyBtn = ".button";


    public void AcceptPrivacyPolicyPopup() {
        WebElement acceptPolicy = findNestedShadowDom(HOST1_SELECTOR, HOSTACCEPTPOLICY_SELECTOR, acceptPolicyBtn);
        forceAClick(acceptPolicy);
    }




}
