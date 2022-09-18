import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Elements extends Actions {
    private WebDriver driver;

    public Elements(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "wzrk-cancel")
    WebElement notificationCancel;
    @FindBy(id = "principal")
    WebElement principal;
    @FindBy(id = "interest")
    WebElement interest;
    @FindBy(id = "tenure")
    WebElement tenure;

    public Select tenurePeriod() {
        return new Select(driver.findElement(By.id("tenurePeriod")));
    }

    public Select frequency() {
        return new Select(driver.findElement(By.id("frequency")));
    }

    @FindBy(id = "resp_matval")
    WebElement maturityValue;
    @FindBy(xpath = "//*[@id=\"fdMatVal\"]/div[2]/a[1]/img")
    WebElement calculate;
    @FindBy(xpath = "//*[@id=\"fdMatVal\"]/div[2]/a[2]/img")
    WebElement clear;

}


