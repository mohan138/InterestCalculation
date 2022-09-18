import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public  class calculationPageTest{

    WebDriver driver;

    @BeforeClass
    @Parameters(value = "browser")
    public void setup(String browser){
        switch (browser) {
            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver", "./Browser drivers/geckodriver");
                driver = new FirefoxDriver();
            }
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", "./Browser drivers/chromedriver");
                driver = new ChromeDriver();
            }
            case "safari" -> driver = new SafariDriver();
        }
    }
    @Test(priority = 2, dataProvider = "data")
    public void calculationPage(String principalAmount, String rateOfInterest, String tenure, String frequency){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
        Elements elements = new Elements(driver);
        try{
            if(elements.notificationCancel.isDisplayed()){
                elements.notificationCancel.click();
                elements.moveByOffset(25,25).perform();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        elements.principal.sendKeys(principalAmount);
        elements.interest.sendKeys(rateOfInterest);
        elements.tenure.sendKeys(tenure);
        elements.tenurePeriod().selectByVisibleText("year(s)");
        elements.frequency().selectByVisibleText(frequency);
        elements.calculate.click();
        System.out.println(elements.maturityValue.getText());
        elements.clear.click();
    }

    @DataProvider(name = "data")
    public Object[][] dataProvider() throws IOException{
        DataFormatter dataFormatter = new DataFormatter();
        Object[][] inputs = new Object[5][4];
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("./src/test/Files/Data.xlsx"));
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        for (int row = 1; row <= 5; row++) {
            for (int column = 0; column <= 3; column++) {
                inputs[row-1][column] = dataFormatter.formatCellValue(sheet.getRow(row).getCell(column));
            }
        }
        return inputs;
    }
}
