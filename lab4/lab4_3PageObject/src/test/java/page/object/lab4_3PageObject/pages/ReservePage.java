package page.object.lab4_3PageObject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReservePage {

    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[2]/h3")
    private WebElement title;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td[3]")
    private WebElement airline;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td[1]/input")
    private WebElement chooseFlight;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td[6]")
    private WebElement price;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td[4]")
    private WebElement departureTime;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td[5]")
    private WebElement arrivalTime;

    public ReservePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getTitle() {
        return title;
    }

    public WebElement getAirline() {
        return airline;
    }

    public WebElement getChooseFlight() {
        return chooseFlight;
    }

    public WebElement getPrice() {
        return price;
    }

    public WebElement getDepartureTime() {
        return departureTime;
    }

    public WebElement getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(WebElement arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTitleText() {
        return title.getText();
    }

    public String getAirlineText() {
        return airline.getText();
    }

    public String getPriceText() {
        return price.getText();
    }

    public String getDepartureTimeText() {
        return departureTime.getText();
    }

    public String getArrivalTimeText() {
        return arrivalTime.getText();
    }

    public void clickChooseFlight() {
        chooseFlight.click();
    }

}
