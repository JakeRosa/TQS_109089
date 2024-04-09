package bus.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    @FindBy(xpath = "/html/body/nav/div/a[2]")
    private WebElement checkReservationButton;

    @FindBy(xpath = "/html/body/div/div/a")
    private WebElement backToSearchButton;

    @FindBy(xpath = "/html/body/div/div/h1")
    private WebElement checkoutTitle;

    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement nameInput;

    @FindBy(xpath = "//*[@id=\"address\"]")
    private WebElement addressInput;

    @FindBy(xpath = "//*[@id=\"city\"]")
    private WebElement cityInput;

    @FindBy(xpath = "//*[@id=\"state\"]")
    private WebElement stateInput;

    @FindBy(xpath = "//*[@id=\"zip\"]")
    private WebElement zipInput;

    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@id=\"phone\"]")
    private WebElement phoneInput;

    @FindBy(xpath = "//*[@id=\"card-type\"]")
    private WebElement cardTypeSelect;

    @FindBy(xpath = "//*[@id=\"card-number\"]")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//*[@id=\"expiry-month\"]")
    private WebElement expiryMonthInput;

    @FindBy(xpath = "//*[@id=\"expiry-year\"]")
    private WebElement expiryYearInput;

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

    public void inputAddress(String address) {
        addressInput.sendKeys(address);
    }

    public String getAddress() {
        return addressInput.getAttribute("value");
    }

    public void inputCity(String city) {
        cityInput.sendKeys(city);
    }

    public String getCity() {
        return cityInput.getAttribute("value");
    }

    public void inputState(String state) {
        stateInput.sendKeys(state);
    }

    public String getState() {
        return stateInput.getAttribute("value");
    }

    public void inputZip(String zip) {
        zipInput.sendKeys(zip);
    }

    public String getZip() {
        return zipInput.getAttribute("value");
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

    public void inputCardNumber(String cardNumber) {
        cardNumberInput.sendKeys(cardNumber);
    }

    public String getCardNumber() {
        return cardNumberInput.getAttribute("value");
    }

    public void inputExpiryMonth(String expiryMonth) {
        expiryMonthInput.sendKeys(expiryMonth);
    }

    public String getExpiryMonth() {
        return expiryMonthInput.getAttribute("value");
    }

    public void inputExpiryYear(String expiryYear) {
        expiryYearInput.sendKeys(expiryYear);
    }

    public String getExpiryYear() {
        return expiryYearInput.getAttribute("value");
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
