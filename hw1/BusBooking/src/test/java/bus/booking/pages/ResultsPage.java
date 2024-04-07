package bus.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultsPage {
    @FindBy(xpath = "/html/body/nav/div/a")
    private WebElement checkReservationButton;

    @FindBy(xpath = "/html/body/div/div/h1/span[1]")
    private WebElement originSpan;

    @FindBy(xpath = "/html/body/div/div/h1/span[2]")
    private WebElement destinationSpan;

    @FindBy(xpath = "/html/body/div/div/a")
    private WebElement backToSearchButton;

    @FindBy(xpath = "//*[@id=\"currency\"]")
    private WebElement currencySelect;

    @FindBy(xpath = "/html/body/div/div/div[2]/table/tbody/tr[2]/td[1]")
    private WebElement busId;

    @FindBy(xpath = "/html/body/div/div/div[2]/table/tbody/tr[2]/td[2]")
    private WebElement departureTime;

    @FindBy(xpath = "/html/body/div/div/div[2]/table/tbody/tr[2]/td[3]")
    private WebElement arrivalTime;

    @FindBy(xpath = "/html/body/div/div/div[2]/table/tbody/tr[2]/td[4]")
    private WebElement duration;

    @FindBy(xpath = "/html/body/div/div/div[2]/table/tbody/tr[2]/td[5]")
    private WebElement availableSeats;

    @FindBy(xpath = "/html/body/div/div/div[2]/table/tbody/tr[2]/td[6]")
    private WebElement price;

    @FindBy(xpath = "/html/body/div/div/div[2]/table/tbody/tr[2]/td[7]/a")
    private WebElement buyButton;

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickCheckReservationButton() {
        checkReservationButton.click();
    }

    public String getOrigin() {
        return originSpan.getText();
    }

    public String getDestination() {
        return destinationSpan.getText();
    }

    public void clickBackToSearchButton() {
        backToSearchButton.click();
    }

    public void selectCurrency(String currency) {
        currencySelect.sendKeys(currency);
    }

    public String getBusId() {
        return busId.getText();
    }

    public String getDepartureTime() {
        return departureTime.getText();
    }

    public String getArrivalTime() {
        return arrivalTime.getText();
    }

    public String getDuration() {
        return duration.getText();
    }

    public String getAvailableSeats() {
        return availableSeats.getText();
    }

    public String getPrice() {
        return price.getText();
    }

    public void clickBuyButton() {
        buyButton.click();
    }
}
