package steps;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ser.Serializers;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DriverManager;

import java.time.Duration;


public class TestPageSteps  {
    //private static WebDriver driver;
    public final static int TIMEOUT = 5;


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
        //String purpose = dataTable.cell(2, 1);

        WebElement location = driver.findElement(By.xpath("//label[contains(text(), '* Your state')]/following::select[1]"));

        Select stateDropdown = new Select(location);
        Assert.assertTrue(location.isDisplayed());
        stateDropdown.selectByVisibleText("New South Wales");

        Thread.sleep(3000);
        // Preencher o código postal
        WebElement postalCodeField = driver.findElement(By.xpath("//*[@aria-labelledby='postal-code-hint']"));

        postalCodeField.click();
        Thread.sleep(2000);
        postalCodeField.sendKeys(postalCode);

        Thread.sleep(3000);
        // Preencher o propósito
        WebElement purposeRadioBtn = driver.findElement(By.xpath("//input[@name='registration-type' and @value='P']/following-sibling::div"));
        purposeRadioBtn.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement continueBtn = driver.findElement(By.cssSelector("[data-test-id='state-selected-modal__close']"));
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        //wait.until(driver -> driver.findElement(By.cssSelector("[data-test-id='state-selected-modal__close']")).getAttribute("disabled") == null);

        continueBtn.click();
    }




}
