package bus.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
    @FindBy(xpath = "/html/body/nav/div/a[2]")
    private WebElement checkReservationButton;

    @FindBy(name = "origin")
    private WebElement originSelect;

    @FindBy(xpath = "//*[@id=\"destination\"]")
    private WebElement destinationSelect;

    @FindBy(xpath = "//*[@id=\"date\"]")
    private WebElement dateInput;

    @FindBy(xpath = "/html/body/div/div/form/button")
    private WebElement searchButton;

    public IndexPage(WebDriver driver) {
        driver.get("http://localhost:8080/");
        PageFactory.initElements(driver, this);
    }

    public void clickCheckReservationButton() {
        checkReservationButton.click();
    }

    public void selectOrigin(String origin) {
        originSelect.sendKeys(origin);
    }

    public String getOrigin() {
        return originSelect.getAttribute("value");
    }

    public void selectDestination(String destination) {
        destinationSelect.sendKeys(destination);
    }

    public String getDestination() {
        return destinationSelect.getAttribute("value");
    }

    public void inputDate(String date) {
        dateInput.sendKeys(date);
    }

    public String getDate() {
        return dateInput.getAttribute("value");
    }

    public void clickSearchButton() {
        searchButton.click();
    }
}
