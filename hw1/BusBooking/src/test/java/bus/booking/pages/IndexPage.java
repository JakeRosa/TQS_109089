package bus.booking.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPage {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/nav/div/a")
    private WebElement checkReservationButton;

    @FindBy(xpath = "//*[@id=\"origin\"]")
    private WebElement originSelect;

    @FindBy(xpath = "//*[@id=\"destination\"]")
    private WebElement destinationSelect;

    @FindBy(xpath = "//*[@id=\"date\"]")
    private WebElement dateInput;

    @FindBy(xpath = "/html/body/div/div/form/button")
    private WebElement searchButton;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://localhost:8080/");
        PageFactory.initElements(driver, this);
    }

    public void clickCheckReservationButton() {
        checkReservationButton.click();
    }

    public void selectOrigin(String origin) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(originSelect));
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
