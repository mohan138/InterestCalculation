package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Elements extends Actions {
    private final WebDriver driver;

    public Elements(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "wzrk-cancel")
    public WebElement notificationCancel;
    @FindBy(id = "principal")
    public WebElement principal;
    @FindBy(id = "interest")
    public WebElement interest;
    @FindBy(id = "tenure")
    public WebElement tenure;

     public Select tenurePeriod() {
        return new Select(driver.findElement(By.id("tenurePeriod")));
    }
    public Select frequency() {
        return new Select(driver.findElement(By.id("frequency")));
    }

    @FindBy(id = "resp_matval")
    public WebElement maturityValue;
    @FindBy(xpath = "//*[@id=\"fdMatVal\"]/div[2]/a[1]/img")
    public WebElement calculate;
    @FindBy(xpath = "//*[@id=\"fdMatVal\"]/div[2]/a[2]/img")
    public WebElement clear;


}


