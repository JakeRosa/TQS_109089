package bus.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessPage {
    @FindBy(xpath = "/html/body/nav/div/a[2]")
    private WebElement checkReservationButton;

    @FindBy(xpath = "/html/body/div/div/h1")
    private WebElement successMessage;

    @FindBy(xpath = "/html/body/div/div/div[1]/p[2]/span")
    private WebElement reservationId;

    @FindBy(xpath = "/html/body/div/div/div[2]/a[1]")
    private WebElement backToSearchButton;

    @FindBy(xpath = "/html/body/div/div/div[2]/a[2]")
    private WebElement checkReservationButton2;

    public SuccessPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickCheckReservationButton() {
        checkReservationButton.click();
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public String getReservationId() {
        return reservationId.getText();
    }

    public void clickBackToSearchButton() {
        backToSearchButton.click();
    }

    public void clickCheckReservationButton2() {
        checkReservationButton2.click();
    }
}
