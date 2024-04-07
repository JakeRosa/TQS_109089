package bus.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    @FindBy(xpath = "/html/body/nav/div/a")
    private WebElement checkReservationButton;

    @FindBy(xpath = "/html/body/div/div/a")
    private WebElement backToSearchButton;

    @FindBy(xpath = "/html/body/div/div/h1")
    private WebElement checkoutTitle;

    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement nameInput;

    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@id=\"phone\"]")
    private WebElement phoneInput;

    @FindBy(xpath = "//*[@id=\"card-type\"]")
    private WebElement cardTypeSelect;

    @FindBy(xpath = "/html/body/div/div/div[2]/p")
    private WebElement totalPrice;

    @FindBy(xpath = "//*[@id=\"pay-now\"]")
    private WebElement payNowButton;

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickCheckReservationButton() {
        checkReservationButton.click();
    }

    public void clickBackToSearchButton() {
        backToSearchButton.click();
    }

    public void inputName(String name) {
        nameInput.sendKeys(name);
    }

    public String getName() {
        return nameInput.getAttribute("value");
    }

    public void inputEmail(String email) {
        emailInput.sendKeys(email);
    }

    public String getEmail() {
        return emailInput.getAttribute("value");
    }

    public void inputPhone(String phone) {
        phoneInput.sendKeys(phone);
    }

    public String getPhone() {
        return phoneInput.getAttribute("value");
    }

    public void selectCardType(String cardType) {
        cardTypeSelect.sendKeys(cardType);
    }

    public String getCardType() {
        return cardTypeSelect.getAttribute("value");
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void clickPayNowButton() {
        payNowButton.click();
    }

    public String getCheckoutTitle() {
        return checkoutTitle.getText();
    }
}
