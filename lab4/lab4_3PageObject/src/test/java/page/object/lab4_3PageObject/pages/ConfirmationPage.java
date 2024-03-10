package page.object.lab4_3PageObject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

    @FindBy(xpath = "/html/body/div[2]/div/h1")
    private WebElement title;

    @FindBy(xpath = "/html/body/div[2]/div/table/tbody/tr[1]/td[2]")
    private WebElement id;

    @FindBy(xpath = "/html/body/div[2]/div/table/tbody/tr[2]/td[2]")
    private WebElement status;

    @FindBy(xpath = "/html/body/div[2]/div/table/tbody/tr[3]/td[2]")
    private WebElement amount;

    public ConfirmationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getId() {
        return id.getText();
    }

    public String getStatus() {
        return status.getText();
    }

    public String getAmount() {
        return amount.getText();
    }
}
