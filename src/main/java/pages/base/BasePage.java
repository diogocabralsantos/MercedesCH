package pages.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import utils.LogHelper;
import utils.WaitHelper;

public class BasePage {
    protected WebDriver driver;
    private WaitHelper waitHelper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        waitHelper = new WaitHelper(driver);
    }

    // Method to send text more slowly
    public void sendKeysSlowly(WebElement element, String text, int delayInMillis) {
        LogHelper.logInfo(this.getClass(), "Sending keys slowly...");
        for (char ch : text.toCharArray()) {
            element.sendKeys(String.valueOf(ch));  // Sends one character at a time
            try {
                Thread.sleep(delayInMillis);  // Pause between each character
            } catch (InterruptedException e) {
                LogHelper.logError(this.getClass(), "Interrupted during sendKeysSlowly", e);
            }
        }
        LogHelper.logInfo(this.getClass(), "Keys sent successfully.");
    }

    // Method to obtain shadowRoot from SearchContext
    // Method to obtain shadowRoot from SearchContext with a wait
    public SearchContext getShadowRoot(WebElement shadowHost) {
        LogHelper.logInfo(this.getClass(), "Waiting for shadow host to be visible...");
        waitHelper.waitForPageLoad();
        LogHelper.logInfo(this.getClass(), "Getting shadow root...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowHost);
    }

    // Method to navigate in multiple levels of shadow doms
    public WebElement findNestedShadowDom(String... selectors) {
        LogHelper.logInfo(this.getClass(), "Navigating through shadow DOMs...");
        waitHelper.waitForPageLoad();

        WebElement currentElement = driver.findElement(By.cssSelector(selectors[0])); // FirstLevel
        for (int i = 1; i < selectors.length - 1; i++) {
            currentElement = getShadowRoot(currentElement).findElement(By.cssSelector(selectors[i]));
        }
        LogHelper.logInfo(this.getClass(), "Reached final element in shadow DOM.");
        return getShadowRoot(currentElement).findElement(By.cssSelector(selectors[selectors.length - 1]));
    }

    public void scrollToElement(WebElement element) {
        LogHelper.logInfo(this.getClass(), "Waiting for element to be visible before scrolling...");
        waitHelper.waitForElementToBeVisible(element);  // Ensure the element is visible before scrolling
        LogHelper.logInfo(this.getClass(), "Scrolling to element...");
        new Actions(driver).scrollToElement(element).perform();
        LogHelper.logInfo(this.getClass(), "Scroll complete.");
    }

    public void forceAClick(WebElement element) {
        LogHelper.logInfo(this.getClass(), "Waiting for element to be clickable...");
        waitHelper.waitForElementToBeClickable(element);  // Wait until the element is clickable
        LogHelper.logInfo(this.getClass(), "Forcing click on element...");
        scrollToElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        LogHelper.logInfo(this.getClass(), "Click performed successfully.");
    }
}
