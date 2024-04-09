package bus.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import bus.booking.pages.CheckoutPage;
import bus.booking.pages.IndexPage;
import bus.booking.pages.ReservationPage;
import bus.booking.pages.ResultsPage;
import bus.booking.pages.SuccessPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CucumberSteps {
    private WebDriver driver;
    private IndexPage indexPage;
    private ResultsPage resultsPage;
    private CheckoutPage checkoutPage;
    private SuccessPage successPage;
    private ReservationPage reservationPage;
    private String reservationId;

    @Given("the user is on the main page")
    public void theUserIsOnTheMainPage() {
        driver = WebDriverManager.firefoxdriver().create();
        driver.get("http://localhost:8080/");
        indexPage = new IndexPage(driver);
    }

    @When("the user selects {string} as the departure city")
    public void theUserSelectsAsTheDepartureCity(String origin) {
        indexPage.selectOrigin(origin);
        assertEquals(origin, indexPage.getOrigin());
    }

    @And("the user selects {string} as the arrival city")
    public void theUserSelectsAsTheArrivalCity(String destination) {
        indexPage.selectDestination(destination);
        assertEquals(destination, indexPage.getDestination());
    }

    @And("the user selects {string} as the departure date")
    public void theUserSelectsAsTheDepartureDate(String date) {
        indexPage.inputDate(date);
        assertEquals(date, indexPage.getDate());
    }

    @And("the user clicks on the Search button")
    public void theUserClicksOnTheSearchButton() {
        indexPage.clickSearchButton();
    }

    @Then("the user is taken to the results page")
    public void theUserIsTakenToTheResultsPage() {
        resultsPage = new ResultsPage(driver);
        assertEquals("Lisboa", resultsPage.getOrigin());
        assertEquals("Porto", resultsPage.getDestination());
    }

    @When("the user selects the first available bus, with the id {string} and costing {string}")
    public void theUserSelectsTheFirstAvailableBusFromToWithTheIdAndCosting(String busId, String price) {
        assertEquals(busId, resultsPage.getBusId());
        assertEquals(price, resultsPage.getPrice());
    }

    @And("the user clicks on the Buy button")
    public void theUserClicksOnTheBuyButton() {
        resultsPage.clickBuyButton();
    }

    @Then("the user is taken to the checkout page")
    public void theUserIsTakenToTheCheckoutPage() {
        checkoutPage = new CheckoutPage(driver);
        assertEquals("Checkout", checkoutPage.getCheckoutTitle());
    }

    @When("the user fills in the required information with:")
    public void theUserFillsRequiredInfoWith(DataTable table) {
        Map<String, String> data = table.asMap(String.class, String.class);
        checkoutPage.inputName(data.get("Name"));
        checkoutPage.inputAddress(data.get("Address"));
        checkoutPage.inputCity(data.get("City"));
        checkoutPage.inputState(data.get("State"));
        checkoutPage.inputZip(data.get("Zip Code"));
        checkoutPage.inputEmail(data.get("Email"));
        checkoutPage.inputPhone(data.get("Phone"));
        checkoutPage.selectCardType(data.get("Card Type"));
        checkoutPage.inputCardNumber(data.get("Card Nr"));
        checkoutPage.inputExpiryMonth(data.get("Exp Month"));
        checkoutPage.inputExpiryYear(data.get("Exp Year"));

        assertEquals(data.get("Name"), checkoutPage.getName());
        assertEquals(data.get("Address"), checkoutPage.getAddress());
        assertEquals(data.get("City"), checkoutPage.getCity());
        assertEquals(data.get("State"), checkoutPage.getState());
        assertEquals(data.get("Zip Code"), checkoutPage.getZip());
        assertEquals(data.get("Email"), checkoutPage.getEmail());
        assertEquals(data.get("Phone"), checkoutPage.getPhone());
        assertEquals(data.get("Card Type").toLowerCase(), checkoutPage.getCardType());
        assertEquals(data.get("Card Nr"), checkoutPage.getCardNumber());
        assertEquals(data.get("Exp Month"), checkoutPage.getExpiryMonth());
        assertEquals(data.get("Exp Year"), checkoutPage.getExpiryYear());
    }

    @And("the user clicks on the Pay Now button")
    public void theUserClicksOnThePayNowButton() {
        checkoutPage.clickPayNowButton();
    }

    @Then("the user is taken to the purchase successfull page")
    public void theUserIsTakenToThePurchaseSuccessfullPage() {
        successPage = new SuccessPage(driver);
        assertEquals("Purchase Successful", successPage.getSuccessMessage());
    }

    @And("the user is given a reservation code")
    public void theUserIsGivenAReservationCode() {
        reservationId = successPage.getReservationId();
        assertEquals(16, reservationId.length());
    }

    @When("the user clicks on the Check Reservation button")
    public void theUserClicksOnTheCheckReservationButton() {
        successPage.clickCheckReservationButton();
    }

    @Then("the user is taken to the reservation page")
    public void theUserIsTakenToTheReservationPage() {
        reservationPage = new ReservationPage(driver);
        assertEquals("Check Reservation", reservationPage.getReservationTitle());
    }

    @When("the user paste the reservation code in the input field")
    public void theUserPasteTheReservationCodeInTheInputField() {
        reservationPage.inputReservationCode(reservationId);
    }

    @And("the user clicks on the Check button")
    public void theUserClicksOnTheCheckButton() {
        reservationPage.clickCheckReservationButton();
    }

    @Then("the user sees the reservation details")
    public void theUserSeesTheReservationDetails() {
        assertEquals("Reservation Details", reservationPage.getReservationDetailsTitle());
        assertEquals("20.0 EUR", reservationPage.getOriginalPrice());
        assertEquals("Trip Details", reservationPage.getTripDetailsTitle());
        assertEquals("BUS007", reservationPage.getBusId());
    }
}
