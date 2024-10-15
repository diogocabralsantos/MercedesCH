package pages.base;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;

import java.util.Map;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    //Method to send text more slowly
    public void sendKeysSlowly(WebElement element, String text, int delayInMillis) {
        for (char ch : text.toCharArray()) {
            element.sendKeys(String.valueOf(ch));  // Sends one character at a time
            try {
                Thread.sleep(delayInMillis);  // Pause between each character
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to obtain shadowRoot from SearchContext
    public SearchContext getShadowRoot(WebElement shadowHost) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowHost);
    }

    //Method to navigate in multiple levels of shadow doms
    public WebElement findNestedShadowDom(String... selectors) {
        WebElement currentElement = driver.findElement(By.cssSelector(selectors[0])); // FirstLevel
        for (int i = 1; i < selectors.length - 1; i++) {
            //Use SearchContext for ShadowRoot to find the next element
            currentElement = getShadowRoot(currentElement).findElement(By.cssSelector(selectors[i]));
        }
        // Last selector is the one that we want to interact
        return getShadowRoot(currentElement).findElement(By.cssSelector(selectors[selectors.length - 1]));
    }

    //Generic method to convert DataTables in Map<String, String>
    public Map<String, String> convertDataTableToMap(DataTable dataTable) {
        return dataTable.asMap(String.class, String.class);
    }

    public void scrollToElement(WebElement element) {
        new Actions(driver).scrollToElement(element).perform();
    }

    public void forceAClick(WebElement element) {
        scrollToElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

    }
}
