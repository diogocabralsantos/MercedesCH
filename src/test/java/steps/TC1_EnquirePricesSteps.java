package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.PreOwnedCarSearchResultsPage;
import pages.VehicleDetailsPage;
import pages.VehicleSearchPage;
import utils.ConfigReader;
import utils.DataTableHelper;

import java.util.Map;

public class TC1_EnquirePricesSteps {

    private final VehicleSearchPage searchPage;
    private final PreOwnedCarSearchResultsPage preOwnedCarSearchResultsPage;
    private final VehicleDetailsPage vehicleDetailsPage;

    WebDriver driver = Hooks.driver;

    public TC1_EnquirePricesSteps() {

        searchPage = new VehicleSearchPage(driver);
        preOwnedCarSearchResultsPage = new PreOwnedCarSearchResultsPage(driver);
        vehicleDetailsPage = new VehicleDetailsPage(driver);
    }

    @Given("I open the Mercedes-Benz Shop used cars in Australia")
    public void goToMercedesBenzShop() throws InterruptedException {
        String url = ConfigReader.getProperty("url");
        Hooks.driver.get(url); // Accessing the driver from Hooks

        Thread.sleep(10000);

        searchPage.clickAcceptPrivacyPolicy();
    }

    @And("I fill in the location details with:")
    public void fillInTheLocationDetails(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        Map<String, String> locationDetails = DataTableHelper.convertDataTableToMap(dataTable);
        String state = locationDetails.get("State");
        String postalCode = locationDetails.get("Postal Code");
        String purpose = locationDetails.get("Purpose");

        searchPage.selectState(state);
        searchPage.enterPostalCode(postalCode);
        searchPage.clickPurposeRadioBtn(purpose);
        searchPage.clickContinueBtn();
    }

    @When("I click the filter button")
    public void clickFilterButton() throws InterruptedException {
        searchPage.clickFilterBtn();
        searchPage.clickPreOwnedBtn();
    }

    @And("I apply the following filters:")
    public void applyFilters(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        Map<String, String> Color = DataTableHelper.convertDataTableToMap(dataTable);
        String colorName = Color.get("Color");
        Thread.sleep(8000);
        searchPage.clickAcceptPrivacyPolicy();
        preOwnedCarSearchResultsPage.clickColorDropDown();
        preOwnedCarSearchResultsPage.setColorDropDown(colorName);
    }

    @Then("I sort by the most expensive cars first")
    public void SortByMostExpensiveCar(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        Map<String, String> sortBy = DataTableHelper.convertDataTableToMap(dataTable);
        String sort = sortBy.get("Sorting");

        preOwnedCarSearchResultsPage.selectSortingDropDown(sort);
    }

    @When("I navigate to Vehicle Details page")
    public void navigateToVehicleDetailsPage() throws InterruptedException {
        preOwnedCarSearchResultsPage.clickVehicleCard();
    }

    @Then("I can save the vehicle details to a file")
    public void CanSaveTheFollowingVehicleDetailsPage() throws InterruptedException {
        Thread.sleep(4000);
        vehicleDetailsPage.getEngineSize();
        vehicleDetailsPage.getModelYear();
    }

    @When("I click enquire now")
    public void ClickEnquireNow() throws InterruptedException {
        vehicleDetailsPage.clickSpeakToExpertBtn();
    }

    @And("I fill the contact details and Account creation form with invalid data")
    public void fillTheContactDetails(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        Map<String, String> contactDetails = DataTableHelper.convertDataTableToMap(dataTable);
        String firstName = contactDetails.get("First Name");
        String lastName = contactDetails.get("Last Name");
        String postcode = contactDetails.get("Postcode");
        String mobile = contactDetails.get("Mobile Number");
        String email = contactDetails.get("E-mail");

        Thread.sleep(5000);
        vehicleDetailsPage.enterFirstName(firstName);
        vehicleDetailsPage.enterLastName(lastName);
        vehicleDetailsPage.enterZipCode(postcode);
        vehicleDetailsPage.enterMobilePhone(mobile);
        vehicleDetailsPage.enterEmail(email);

        vehicleDetailsPage.clickSubmitBtn();
    }

    @Then("I can´t proceed because one or more details are incorrect")
    public void receiveAMessageThatOneOrMoreFieldsAreIncorrect() throws InterruptedException {
        vehicleDetailsPage.getEmailErrorMsg();
    }
}
