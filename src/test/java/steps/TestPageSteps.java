package steps;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ser.Serializers;
import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.VehicleSearchPage;
import utils.DriverManager;

import java.time.Duration;


public class TestPageSteps  {
    //private static WebDriver driver;
    public final static int TIMEOUT = 5;
    private VehicleSearchPage searchPage;

    public TestPageSteps() {
        searchPage = new VehicleSearchPage();
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
        Thread.sleep(5000);
        searchPage.clickFilterBtn();
    }




}
