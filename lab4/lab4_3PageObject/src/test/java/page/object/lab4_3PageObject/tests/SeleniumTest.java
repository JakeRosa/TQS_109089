package page.object.lab4_3PageObject.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import page.object.lab4_3PageObject.pages.ConfirmationPage;
import page.object.lab4_3PageObject.pages.HomePage;
import page.object.lab4_3PageObject.pages.PurchasePage;
import page.object.lab4_3PageObject.pages.ReservePage;

@ExtendWith(SeleniumJupiter.class)
public class SeleniumTest {

    @Test
    void test(FirefoxDriver driver) {
        HomePage homePage = new HomePage(driver);

        homePage.selectFromPort("Boston");
        assertEquals("Boston", homePage.getFromPort());

        homePage.selectToPort("London");
        assertEquals("London", homePage.getToPort());

        homePage.clickFindFlights();

        ReservePage reservePage = new ReservePage(driver);

        assertEquals("Flights from Boston to London:", reservePage.getTitle().getText());

        reservePage.getChooseFlight().click();

        PurchasePage purchasePage = new PurchasePage(driver);

        assertEquals("Your flight from TLV to SFO has been reserved.", purchasePage.getTitle());

        assertEquals("Flight Number: UA954", purchasePage.getFlightNumber());

        assertEquals("Total Cost: 914.76", purchasePage.getTotalCost());

        purchasePage.setFormData("Jake", "minha casa", "Aveiro", "Portugal", "3800-180", "Visa", "2939482842", "11",
                "2024", "JOHN SMITH CARABINA",
                "1");

        assertEquals("Jake", purchasePage.getName());
        assertEquals("minha casa", purchasePage.getAddress());
        assertEquals("Aveiro", purchasePage.getCity());
        assertEquals("Portugal", purchasePage.getState());
        assertEquals("3800-180", purchasePage.getZipCode());
        assertEquals("visa", purchasePage.getCardType());
        assertEquals("2939482842", purchasePage.getCreditCardNumber());
        assertEquals("11", purchasePage.getCreditCardMonth());
        assertEquals("2024", purchasePage.getCreditCardYear());
        assertEquals("JOHN SMITH CARABINA", purchasePage.getNameOnCard());
        assertEquals("on", purchasePage.getRememberMe());

        purchasePage.clickPurchaseFlight();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);

        assertEquals("Thank you for your purchase today!", confirmationPage.getTitle());
    }
}
