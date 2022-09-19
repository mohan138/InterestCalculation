package com.testCases;

import com.pageObjects.Elements;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.IOException;

public  class calculationPageTest extends setupAndClose{
    @Test(dataProvider = "data")
    public void calculationPage(String principalAmount, String rateOfInterest, String tenure, String frequency){
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
        log.info("entered "+principalAmount+" as principal amount");
        elements.interest.sendKeys(rateOfInterest);
        log.info("entered "+rateOfInterest+" as rate of interest");
        elements.tenure.sendKeys(tenure);
        log.info("entered "+tenure+"as tenure");
        elements.tenurePeriod().selectByVisibleText("year(s)");
        log.info("selected year(s) as tenure period");
        elements.frequency().selectByVisibleText(frequency);
        log.info("Selected "+frequency+" as frequency");
        elements.calculate.click();
        log.info("Clicked calculate");
        System.out.println(elements.maturityValue.getText());
        log.info("Got Maturity value of "+elements.maturityValue.getText());
        elements.clear.click();
        log.info("Cleared");
    }

    @DataProvider(name = "data")
    public Object[][] dataProvider() throws IOException{
        DataFormatter dataFormatter = new DataFormatter();
        Object[][] inputs = new Object[5][4];
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("./Files/Data.xlsx"));
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        for (int row = 1; row <= 5; row++) {
            for (int column = 0; column <= 3; column++) {
                inputs[row-1][column] = dataFormatter.formatCellValue(sheet.getRow(row).getCell(column));
            }
        }
        return inputs;
    }
}
