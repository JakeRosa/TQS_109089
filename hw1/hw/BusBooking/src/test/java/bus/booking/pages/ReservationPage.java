package bus.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReservationPage {
    @FindBy(xpath = "/html/body/div/h1")
    private WebElement reservationTitle;

    @FindBy(xpath = "//*[@id=\"reservation-code\"]")
    private WebElement reservationCodeInput;

    @FindBy(xpath = "/html/body/div/form/button")
    private WebElement checkReservationButton;

    @FindBy(xpath = "/html/body/div/div/h2[1]")
    private WebElement reservationDetailsTitle;

    @FindBy(xpath = "/html/body/div/div/table[1]/tbody/tr[7]/td")
    private WebElement originalPrice;

    @FindBy(xpath = "/html/body/div/div/h2[2]")
    private WebElement tripDetailsTitle;

    @FindBy(xpath = "/html/body/div/div/table[2]/tbody/tr[1]/td")
    private WebElement busId;

    public ReservationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getReservationTitle() {
        return reservationTitle.getText();
    }

    public void inputReservationCode(String reservationCode) {
        reservationCodeInput.sendKeys(reservationCode);
    }

    public void clickCheckReservationButton() {
        checkReservationButton.click();
    }

    public String getReservationDetailsTitle() {
        return reservationDetailsTitle.getText();
    }

    public String getOriginalPrice() {
        return originalPrice.getText();
    }

    public String getTripDetailsTitle() {
        return tripDetailsTitle.getText();
    }

    public String getBusId() {
        return busId.getText();
    }

}
