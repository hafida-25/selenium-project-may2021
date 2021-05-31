package com.peoplentech.selenium;

import com.sun.xml.internal.ws.util.xml.CDATA;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.IDataProviderInterceptor;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.Test;

public class DriverLaunch3 {
    private static WebDriver driver;

   // public static void setupBrowserForamazon() {
     //   System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
       //  driver = new ChromeDriver();
      //  driver.get("https://www.amazon.com");

   // }

    public static void setupBrowser( String browserName, String url ) {
        if (browserName.equalsIgnoreCase("chrome")) {
             System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }else {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.get(url);

    }
    public static void waitFor(int seconds){
        try{
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void closeBrowser() //throws InterruptedException
    {
       waitFor(5);
        driver.quit();

    }

    public void clickOnLinkTest(String lnkTxt){

        driver.findElement(By.linkText(lnkTxt)).click();
    }

    public void clickOnXpath(String xpath){
        driver.findElement(By.xpath(xpath)).click();
    }

    public static void clickOnId(String id){
         driver.findElement(By.id("id")).click();
    }

    public void typeOnXpath(String xpath, String data) {
        driver.findElement(By.xpath(xpath)).sendKeys(data);
    }


    public static void navigateBack(){
        driver.navigate().back();
    }

    @Test
    public void validateUserCanClickOnRegisterButton() throws InterruptedException {
       setupBrowser("chrome","https://www.ebay.com");
       // driver.findElement(By.linkText("register")).click();
        clickOnLinkTest("register");
        navigateBack();
        closeBrowser();

    }

    @Test
    public void validateUserCanClickOnSignInButton() throws InterruptedException {
        setupBrowser("chrome", "https://www.ebay.com");
        clickOnLinkTest("Sign in");
        closeBrowser();
    }
   @Test
           public void validateUserCanSearchForItemsInAmazon() throws InterruptedException {
       setupBrowser("firefox","https://www.amazon.com");
        waitFor(2);
       typeOnXpath("//input[@id='twotabsearchtextbox']","java books");
       closeBrowser();
        }


    public void validateUserCanSearchForItemsInEbay() throws InterruptedException {
        setupBrowser("firefox","https://www.ebay.com");
        waitFor(2);
        typeOnXpath("//input[@id='gh-ac']","java books");
        clickOnId("gh-btn");
        closeBrowser();
    }


}

//input[@class='gLFyF gsfi']
        // //  --> always strats with double slash
        // input -->tag name
        // [@key='value']
        //inside the test






