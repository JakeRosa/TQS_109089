package web.automation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import web.automation.pages.ConfirmationPage;
import web.automation.pages.HomePage;
import web.automation.pages.PurchasePage;
import web.automation.pages.ReservePage;

public class FlightPurchaseSteps {
    private WebDriver driver;
    private HomePage homePage;
    private PurchasePage purchasePage;
    private ReservePage reservePage;
    private ConfirmationPage confirmationPage;

    @Given("the user is on the Simple Travel Agency homepage")
    public void theUserIsOnTheSimpleTravelAgencyHomepage() {
        driver = WebDriverManager.firefoxdriver().create();
        driver.get("http://blazedemo.com/");
        homePage = new HomePage(driver);
    }

    @When("the user selects {string} as the departure city")
    public void theUserSelectsAsTheDepartureCity(String arg0) {
        homePage.selectFromPort(arg0);
        assertEquals(arg0, homePage.getFromPort());
    }

    @When("the user selects {string} as the destination city")
    public void theUserSelectsAsTheDestinationCity(String arg0) {
        homePage.selectToPort(arg0);
        assertEquals(arg0, homePage.getToPort());
    }

    @When("the user clicks on Find Flights")
    public void theUserClicksTheFindFlightsButton() {
        homePage.clickFindFlights();
    }

    @Then("the user is taken to the flight reservation page")
    public void theUserIsTakenToTheFlightReservationPage() {
        reservePage = new ReservePage(driver);
    }

    @When("the user selects a flight from {string} with a price of {string}")
    public void theUserSelectsAFlightFromNumberWithAPriceOf(String arg0, String arg2) {
        assertEquals(arg0, reservePage.getAirlineText());
        assertEquals(arg2, reservePage.getPriceText());
    }

    @And("the user clicks on Choose This Flight")
    public void theUserClicksTheChooseThisFlight() {
        reservePage.clickChooseFlight();
    }

    @Then("the user is taken to the flight purchase page")
    public void theUserIsTakenToTheFlightPurchasePage() {
        purchasePage = new PurchasePage(driver);
    }

    @When("the user fills out the flight purchase form with:")
    public void whenTheUserFillsTheFormWith(DataTable table) {
        Map<String, String> map = table.asMap(String.class, String.class);
        purchasePage.setFormData(map.get("Name"), map.get("Address"), map.get("City"), map.get("State"),
                map.get("Zip Code"), map.get("Card Type"), map.get("Card Number"), map.get("Card Exp. Month"),
                map.get("Card Exp. Year"), map.get("Name on Card"), map.get("Remember Me"));
    }

    @When("the user clicks on Purchase Flight")
    public void theUserClicksOnPurchaseFlight() {
        purchasePage.clickPurchaseFlight();
    }

    @Then("the user is taken to the flight purchase confirmation page")
    public void theUserIsTakenToTheFlightPurchaseConfirmationPage() {
        confirmationPage = new ConfirmationPage(driver);
        assertEquals("Thank you for your purchase today!", confirmationPage.getTitle());
    }
}
