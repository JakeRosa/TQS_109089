package bus.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;

import bus.booking.pages.CheckoutPage;
import bus.booking.pages.IndexPage;
import bus.booking.pages.ReservationPage;
import bus.booking.pages.ResultsPage;
import bus.booking.pages.SuccessPage;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class SeleniumTest {
    @Test
    void test(FirefoxDriver driver) {
        IndexPage indexPage = new IndexPage(driver);

        indexPage.selectOrigin("Lisboa");
        assertEquals("Lisboa", indexPage.getOrigin());

        indexPage.selectDestination("Porto");
        assertEquals("Porto", indexPage.getDestination());

        indexPage.inputDate("2024-05-05");
        assertEquals("2024-05-05", indexPage.getDate());

        indexPage.clickSearchButton();

        ResultsPage resultsPage = new ResultsPage(driver);

        assertEquals("Lisboa", resultsPage.getOrigin());
        assertEquals("Porto", resultsPage.getDestination());
        assertEquals("BUS007", resultsPage.getBusId());

        resultsPage.clickBuyButton();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        assertEquals("Checkout", checkoutPage.getCheckoutTitle());
        assertEquals("20.0 EUR", checkoutPage.getTotalPrice());

        checkoutPage.inputName("John Smith Carabina");
        checkoutPage.inputAddress("minha casa");
        checkoutPage.inputCity("Aveiro");
        checkoutPage.inputState("Portugal");
        checkoutPage.inputZip("3800-000");
        checkoutPage.inputEmail("john.smith.carabina@gmail.com");
        checkoutPage.inputPhone("123456789");
        checkoutPage.selectCardType("Visa");
        checkoutPage.inputCardNumber("4111111111111111");
        checkoutPage.inputExpiryMonth("12");
        checkoutPage.inputExpiryYear("2024");

        assertEquals("John Smith Carabina", checkoutPage.getName());
        assertEquals("minha casa", checkoutPage.getAddress());
        assertEquals("Aveiro", checkoutPage.getCity());
        assertEquals("Portugal", checkoutPage.getState());
        assertEquals("3800-000", checkoutPage.getZip());
        assertEquals("john.smith.carabina@gmail.com", checkoutPage.getEmail());
        assertEquals("123456789", checkoutPage.getPhone());
        assertEquals("visa", checkoutPage.getCardType());
        assertEquals("4111111111111111", checkoutPage.getCardNumber());
        assertEquals("12", checkoutPage.getExpiryMonth());
        assertEquals("2024", checkoutPage.getExpiryYear());

        checkoutPage.clickPayNowButton();

        SuccessPage successPage = new SuccessPage(driver);

        assertEquals("Purchase Successful", successPage.getSuccessMessage());

        String reservationId = successPage.getReservationId();

        successPage.clickCheckReservationButton();

        ReservationPage reservationPage = new ReservationPage(driver);

        assertEquals("Check Reservation", reservationPage.getReservationTitle());

        reservationPage.inputReservationCode(reservationId);

        reservationPage.clickCheckReservationButton();

        assertEquals("Reservation Details", reservationPage.getReservationDetailsTitle());
        assertEquals("20.0 EUR", reservationPage.getOriginalPrice());

        assertEquals("Trip Details", reservationPage.getTripDetailsTitle());
        assertEquals("BUS007", reservationPage.getBusId());
    }
}
