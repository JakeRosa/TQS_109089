package page.object.lab4_3PageObject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "/html/body/div[3]/form/select[1]")
    private WebElement fromPortSelect;

    @FindBy(xpath = "/html/body/div[3]/form/div/input")
    private WebElement findFlightsButton;

    @FindBy(xpath = "/html/body/div[3]/form/select[2]")
    private WebElement toPortSelect;

    public HomePage(WebDriver driver) {
        String URL = "https://blazedemo.com";
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public void selectFromPort(String fromPort) {
        fromPortSelect.sendKeys(fromPort);
    }

    public void selectToPort(String toPort) {
        toPortSelect.sendKeys(toPort);
    }

    public String getFromPort() {
        return fromPortSelect.getAttribute("value");
    }

    public String getToPort() {
        return toPortSelect.getAttribute("value");
    }

    public void clickFindFlights() {
        findFlightsButton.click();
    }

}
