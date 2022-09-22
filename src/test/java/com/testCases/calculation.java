package com.testCases;

import com.pageObjects.Elements;
import com.utilities.ReadingExel;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.*;

import java.io.IOException;

public  class calculation extends setupAndClose{
    @Test(dataProvider = "data")
    public void calculationPage(String principalAmount, String rateOfInterest, String tenure, String frequency) {
        log = LogManager.getLogger(calculation.class.getName());
        driver.manage().window().maximize();
        driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
        Elements elements = new Elements(driver);
            try {
                if (elements.notificationCancel.isDisplayed()) {
                    elements.notificationCancel.click();
                    elements.moveByOffset(25, 25).perform();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        elements.principal.sendKeys(principalAmount);
        log.info(driver+ " entered "+principalAmount+" as principal amount");
        elements.interest.sendKeys(rateOfInterest);
        log.info(driver+ " entered "+rateOfInterest+" as rate of interest");
        elements.tenure.sendKeys(tenure);
        log.info(driver+ " entered "+tenure+"as tenure");
        elements.tenurePeriod().selectByVisibleText("year(s)");
        log.info(driver+" selected year(s) as tenure period");
        elements.frequency().selectByVisibleText(frequency);
        log.info(driver+ " Selected "+frequency+" as frequency");
        elements.calculate.click();
        log.info(driver+ " Clicked calculate");
        System.out.println(elements.maturityValue.getText());
        log.info(driver+ " Got Maturity value of "+elements.maturityValue.getText());
        elements.clear.click();
        log.info(driver+ " Cleared");
    }

    @DataProvider(name = "data")
    public Object[][] dataProvider() throws IOException {
        ReadingExel excel = new ReadingExel("./Files/Data.xlsx","Sheet1");
        return excel.getData();
    }
}
