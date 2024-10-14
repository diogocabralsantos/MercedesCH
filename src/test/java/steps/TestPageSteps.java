package steps;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ser.Serializers;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DriverManager;


public class TestPageSteps  {
    //private static WebDriver driver;
    public final static int TIMEOUT = 5;


    WebDriver driver = DriverManager.getDriver();

    @Given("I open the Mercedes-Benz Shop used cars in Australia")
    public void goToMercedesBenzShop() throws InterruptedException {
        driver.get("https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo");

        Thread.sleep(10000);


    }

    @And("I fill in the location details")
    public void fillInTheLocationDetails() {
        WebElement location = driver.findElement(By.xpath("/html/body/div/div[1]/header/div/div[4]/div[1]/div/div[1]/div/wb-select-control/wb-select/select"));


        Select objSelect = new Select(location);
        Assert.assertTrue(location.isDisplayed());
        objSelect.selectByVisibleText("New South Wales");
        System.out.println("PRINTIIIIIIIIIIIIIIIIIIII:" + objSelect.getOptions());
    }
}
