package steps;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.PreOwnedCarSearchResultsPage;
import pages.VehicleDetailsPage;
import pages.VehicleSearchPage;
import utils.DriverManager;


public class TestPageSteps  {
    private VehicleSearchPage searchPage;
    private PreOwnedCarSearchResultsPage preOwnedCarSearchResultsPage;
    private VehicleDetailsPage vehicleDetailsPage;

    public TestPageSteps() {
        searchPage = new VehicleSearchPage();
        preOwnedCarSearchResultsPage = new PreOwnedCarSearchResultsPage();
        vehicleDetailsPage = new VehicleDetailsPage();


    }

    WebDriver driver = DriverManager.getDriver();

    @Given("I open the Mercedes-Benz Shop used cars in Australia")
    public void goToMercedesBenzShop() throws InterruptedException {
        driver.get("https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo");

        Thread.sleep(10000);


    }

    @And("I fill in the location details with:")
    public void fillInTheLocationDetails(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {

        // Extrair os dados da tabela
        String state = dataTable.cell(0, 1);
        String postalCode = dataTable.cell(1, 1);
        String purpose = dataTable.cell(2, 1);

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
        String color = dataTable.cell(0, 1);
        Thread.sleep(8000);
        preOwnedCarSearchResultsPage.clickColorDropDown();
        Thread.sleep(2000);
        preOwnedCarSearchResultsPage.setColorDropDown(color);
    }

    @Then("I sort by the most expensive cars first")
    public void SortByMostExpensiveCar(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        String sort = dataTable.cell(0, 1);
        Thread.sleep(2000);
        preOwnedCarSearchResultsPage.selectSortingDropDown(sort);
    }

    @When("I navigate to Vehicle Details page")
    public void navigateToVehicleDetailsPage() throws InterruptedException {
        Thread.sleep(2000);
        preOwnedCarSearchResultsPage.clickVehicleCard();
    }

    @Then("I can save the following vehicle details to a file")
    public void iCanSaveTheFollowingVehicleDetailsPage() throws InterruptedException {
        Thread.sleep(2000);
        vehicleDetailsPage.getEngineNumber();
        vehicleDetailsPage.getModelYear();
    }

    @When("I click 'Enquire Now'")
    public void iClickEnquireNow() throws InterruptedException {
        Thread.sleep(2000);

    }
}
