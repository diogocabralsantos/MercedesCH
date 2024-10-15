package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.base.BasePage;
import utils.DriverManager;

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

    public void clickColorDropDown() {
        colorDropDown.click();
    }

    public void setColorDropDown(String color) throws InterruptedException {

        switch(color) {
            case "Red":
                new Actions(driver).scrollToElement(redCheckbox).perform();
                redCheckbox.click();
                break;
            case "Black":
                new Actions(driver).scrollToElement(blackCheckbox).perform();
                blackCheckbox.click();
                break;
            case "White":
                new Actions(driver).scrollToElement(whiteCheckbox).perform();
                whiteCheckbox.click();
                break;
        }
    }
    public void selectSortingDropDown(String sort) {
        Select stateDropdown = new Select(selectSortingDropDown);
        Assert.assertTrue(selectSortingDropDown.isDisplayed(), "Dropdown is not displayed");
        stateDropdown.selectByVisibleText(sort);
    }

    public void clickVehicleCard() {
        VehicleCard.click();
    }
}
