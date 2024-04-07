package bus.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import bus.booking.pages.CheckoutPage;
import bus.booking.pages.IndexPage;
import bus.booking.pages.ReservationPage;
import bus.booking.pages.ResultsPage;
import bus.booking.pages.SuccessPage;
import io.github.bonigarcia.seljup.BrowserType;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class SeleniumDockerBrowserTest {
    @Test
    void test(@DockerBrowser(type = BrowserType.CHROME) WebDriver driver) {
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
        checkoutPage.inputEmail("john.smith.carabina@gmail.com");
        checkoutPage.inputPhone("123456789");
        checkoutPage.selectCardType("Visa");

        assertEquals("John Smith Carabina", checkoutPage.getName());
        assertEquals("john.smith.carabina@gmail.com", checkoutPage.getEmail());
        assertEquals("123456789", checkoutPage.getPhone());
        assertEquals("Visa", checkoutPage.getCardType());

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
