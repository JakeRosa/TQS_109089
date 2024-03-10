package page.object.lab4_3PageObject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {

    @FindBy(xpath = "/html/body/div[2]/h2")
    private WebElement title;

    @FindBy(xpath = "/html/body/div[2]/p[2]")
    private WebElement flightNumber;

    @FindBy(xpath = "/html/body/div[2]/p[5]")
    private WebElement totalCost;

    @FindBy(xpath = "//*[@id=\"inputName\"]")
    private WebElement name;

    @FindBy(xpath = "//*[@id=\"address\"]")
    private WebElement address;

    @FindBy(xpath = "//*[@id=\"city\"]")
    private WebElement city;

    @FindBy(xpath = "//*[@id=\"state\"]")
    private WebElement state;

    @FindBy(xpath = "//*[@id=\"zipCode\"]")
    private WebElement zipCode;

    @FindBy(xpath = "//*[@id=\"cardType\"]")
    private WebElement cardType;

    @FindBy(xpath = "//*[@id=\"creditCardNumber\"]")
    private WebElement creditCardNumber;

    @FindBy(xpath = "//*[@id=\"creditCardMonth\"]")
    private WebElement creditCardMonth;

    @FindBy(xpath = "//*[@id=\"creditCardYear\"]")
    private WebElement creditCardYear;

    @FindBy(xpath = "//*[@id=\"nameOnCard\"]")
    private WebElement nameOnCard;

    @FindBy(xpath = "//*[@id=\"rememberMe\"]")
    private WebElement rememberMe;

    @FindBy(xpath = "/html/body/div[2]/form/div[11]/div/input")
    private WebElement purchaseFlightButton;

    public PurchasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getFlightNumber() {
        return flightNumber.getText();
    }

    public String getTotalCost() {
        return totalCost.getText();
    }

    public void setFormData(String name, String address, String city, String state, String zipCode, String cardType,
            String creditCardNumber, String creditCardMonth, String creditCardYear, String nameOnCard,
            String rememberMe) {
        this.creditCardMonth.clear();
        this.creditCardYear.clear();
        this.name.sendKeys(name);
        this.address.sendKeys(address);
        this.city.sendKeys(city);
        this.state.sendKeys(state);
        this.zipCode.sendKeys(zipCode);
        this.cardType.sendKeys(cardType);
        this.creditCardNumber.sendKeys(creditCardNumber);
        this.creditCardMonth.sendKeys(creditCardMonth);
        this.creditCardYear.sendKeys(creditCardYear);
        this.nameOnCard.sendKeys(nameOnCard);
        this.rememberMe.sendKeys(rememberMe);
    }

    public String getName() {
        return name.getAttribute("value");
    }

    public String getAddress() {
        return address.getAttribute("value");
    }

    public String getCity() {
        return city.getAttribute("value");
    }

    public String getState() {
        return state.getAttribute("value");
    }

    public String getZipCode() {
        return zipCode.getAttribute("value");
    }

    public String getCardType() {
        return cardType.getAttribute("value");
    }

    public String getCreditCardNumber() {
        return creditCardNumber.getAttribute("value");
    }

    public String getCreditCardMonth() {
        return creditCardMonth.getAttribute("value");
    }

    public String getCreditCardYear() {
        return creditCardYear.getAttribute("value");
    }

    public String getNameOnCard() {
        return nameOnCard.getAttribute("value");
    }

    public String getRememberMe() {
        return rememberMe.getAttribute("value");
    }

    public void clickPurchaseFlight() {
        purchaseFlightButton.click();
    }

}
