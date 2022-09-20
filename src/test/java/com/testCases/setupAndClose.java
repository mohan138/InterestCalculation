package com.testCases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

public class setupAndClose {
    public WebDriver driver;
    public Logger log;
    @BeforeClass()
    @Parameters(value = "browser")
    public void setup(String browser){
        System.setProperty("log4j.configurationFile","./log4j2.xml");
        log = LogManager.getLogger(setupAndClose.class.getName());
        switch (browser){
            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver", "./Browser drivers/geckodriver");
                driver = new FirefoxDriver();
                log.info("Firefox browser opened");
            }
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", "./Browser drivers/chromedriver");
                driver = new ChromeDriver();
                log.info("Chrome browser opened");
            }
            case "safari" -> {
                driver = new SafariDriver();
                log.info("Safari browser opened");
            }
        }
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
