package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;
import utils.LogHelper;
import utils.WaitHelper;
import org.testng.Assert;

public class PreOwnedCarSearchResultsPage extends BasePage {

    @FindBy(css = "[data-test-id='emh-filters-content__accordion-item__color']")
    private WebElement colorDropDown;

    @FindBy(xpath = "//input[@type='checkbox' and @name='Red']/following-sibling::div")
    private WebElement redCheckbox;

    @FindBy(css = "input[type='checkbox'][name='Black']/following-sibling::div")
    private WebElement blackCheckbox;

    @FindBy(css = "input[type='checkbox'][name='White']following-sibling::div")
    private WebElement whiteCheckbox;

    @FindBy(css = "select[data-test-id='emh-ui-dropdown__select']")
    private WebElement selectSortingDropDown;

    @FindBy(xpath = "(//*[@id=\"emh-srp\"]//article[contains(@class,'emh-vehicle-tile')])[1]")
    private WebElement VehicleCard;

    private final WaitHelper waitHelper;

    public PreOwnedCarSearchResultsPage(WebDriver driver) {
        super(driver);
        this.waitHelper = new WaitHelper(driver);  // Initialize the WaitHelper
    }

    public void clickColorDropDown() {
        LogHelper.logInfo(this.getClass(), "Waiting for color dropdown to be clickable...");
        waitHelper.waitForElementToBeClickable(colorDropDown);  // Wait for the dropdown to be clickable
        LogHelper.logInfo(this.getClass(), "Clicking color dropdown...");
        colorDropDown.click();
        LogHelper.logInfo(this.getClass(), "Color dropdown clicked.");
    }

    public void setColorDropDown(String color) throws InterruptedException {
        LogHelper.logInfo(this.getClass(), "Setting color dropdown to: " + color);
        switch (color) {
            case "Red":
                waitHelper.waitForElementToBeClickable(redCheckbox);  // Wait for the checkbox to be clickable
                new Actions(driver).scrollToElement(redCheckbox).perform();
                redCheckbox.click();
                break;
            case "Black":
                waitHelper.waitForElementToBeClickable(blackCheckbox);  // Wait for the checkbox to be clickable
                new Actions(driver).scrollToElement(blackCheckbox).perform();
                blackCheckbox.click();
                break;
            case "White":
                waitHelper.waitForElementToBeClickable(whiteCheckbox);  // Wait for the checkbox to be clickable
                new Actions(driver).scrollToElement(whiteCheckbox).perform();
                whiteCheckbox.click();
                break;
        }
        LogHelper.logInfo(this.getClass(), "Color dropdown set to: " + color);
    }

    public void selectSortingDropDown(String sort) {
        LogHelper.logInfo(this.getClass(), "Waiting for sorting dropdown to be visible...");
        waitHelper.waitForElementToBeClickable(selectSortingDropDown);  // Wait for the dropdown to be visible
        LogHelper.logInfo(this.getClass(), "Selecting sorting option: " + sort);
        Select stateDropdown = new Select(selectSortingDropDown);
        Assert.assertTrue(selectSortingDropDown.isDisplayed(), "Dropdown is not displayed");
        stateDropdown.selectByVisibleText(sort);
        LogHelper.logInfo(this.getClass(), "Sorting option selected: " + sort);
    }

    public void clickVehicleCard() {
        LogHelper.logInfo(this.getClass(), "Waiting for vehicle card to be clickable...");
        waitHelper.waitForElementToBeClickable(VehicleCard);  // Wait for the vehicle card to be clickable
        LogHelper.logInfo(this.getClass(), "Clicking vehicle card...");
        VehicleCard.click();
        LogHelper.logInfo(this.getClass(), "Vehicle card clicked.");
    }
}
