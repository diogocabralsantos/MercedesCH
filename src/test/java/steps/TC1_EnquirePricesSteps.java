package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.PreOwnedCarSearchResultsPage;
import pages.VehicleDetailsPage;
import pages.VehicleSearchPage;
import pages.popups.PrivacyPolicyPopup;
import utils.DataTableHelper;
import utils.DriverManager;

import java.util.Map;


public class TC1_EnquirePricesSteps {
    private final VehicleSearchPage searchPage;
    private final PreOwnedCarSearchResultsPage preOwnedCarSearchResultsPage;
    private final VehicleDetailsPage vehicleDetailsPage;

    public TC1_EnquirePricesSteps() {
        searchPage = new VehicleSearchPage();
        preOwnedCarSearchResultsPage = new PreOwnedCarSearchResultsPage();
        vehicleDetailsPage = new VehicleDetailsPage();
    }

    WebDriver driver = DriverManager.getDriver();

    @Given("I open the Mercedes-Benz Shop used cars in Australia")
    public void goToMercedesBenzShop() throws InterruptedException {
        driver.get("https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo");

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
        Thread.sleep(2000);
        searchPage.enterPostalCode(postalCode);
        Thread.sleep(2000);
        searchPage.clickPurposeRadioBtn(purpose);
        searchPage.clickContinueBtn();

    }

    @When("I click the filter button")
    public void clickFilterButton() throws InterruptedException {
        Thread.sleep(3000);
        searchPage.clickFilterBtn();
        Thread.sleep(2000);
        searchPage.clickPreOwnedBtn();
    }

    @And("I apply the following filters:")
    public void applyFilters(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        Map<String, String> Color = DataTableHelper.convertDataTableToMap(dataTable);
        String colorName = Color.get("Color");
        Thread.sleep(8000);
        searchPage.clickAcceptPrivacyPolicy();
        Thread.sleep(1000);
        preOwnedCarSearchResultsPage.clickColorDropDown();
        Thread.sleep(2000);
        preOwnedCarSearchResultsPage.setColorDropDown(colorName);
    }

    @Then("I sort by the most expensive cars first")
    public void SortByMostExpensiveCar(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        Map<String, String> sortBy = DataTableHelper.convertDataTableToMap(dataTable);
        String sort = sortBy.get("Sorting");
        Thread.sleep(2000);
        preOwnedCarSearchResultsPage.selectSortingDropDown(sort);
    }

    @When("I navigate to Vehicle Details page")
    public void navigateToVehicleDetailsPage() throws InterruptedException {
        Thread.sleep(2000);
        preOwnedCarSearchResultsPage.clickVehicleCard();
    }

    @Then("I can save the following vehicle details to a file")
    public void CanSaveTheFollowingVehicleDetailsPage() throws InterruptedException {
        Thread.sleep(4000);
        vehicleDetailsPage.getEngineNumber();
        vehicleDetailsPage.getModelYear();
    }

    @When("I click enquire now")
    public void ClickEnquireNow() throws InterruptedException {
        Thread.sleep(4000);
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
        Thread.sleep(2000);
        vehicleDetailsPage.clickSubmitBtn();

        Thread.sleep(5000);
    }

    @Then("I can´t proceed because one or more details are incorrect")
    public void receiveAMessageThatOneOrMoreFieldsAreIncorrect() throws InterruptedException {
        Thread.sleep(2000);

        vehicleDetailsPage.getEmailErrorMsg();
    }


}
