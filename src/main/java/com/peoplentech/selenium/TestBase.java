package com.peoplentech.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestBase {
    private static Logger logger = Logger.getLogger(TestBase.class);
    public static WebDriver driver;

    public static void setupBrowser(String browserName, String url) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(url);

    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void closeBrowser() //throws InterruptedException
    {
        waitFor(5);
        driver.quit();

    }

    public void clickOnLinkTest(String lnkTxt) {
        driver.findElement(By.linkText(lnkTxt)).click();
    }

    public void clickOnXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public static void clickOnId(String id) {
        driver.findElement(By.id("id")).click();
    }

    public void typeOnXpath(String xpath, String data) {
        driver.findElement(By.xpath(xpath)).sendKeys(data);
    }

    public static void navigateBack() {
        driver.navigate().back();
    }


    @Test
    public void validateUserCanClickOnRegisterButton() throws InterruptedException {
        setupBrowser("chrome", "https://www.ebay.com");
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
        setupBrowser("firefox", "https://www.amazon.com");
        waitFor(2);
        typeOnXpath("//input[@id='twotabsearchtextbox']", "java books");
        closeBrowser();
    }

    @Test
    public void validateUserCanSearchForItemsInEbay() {
        setupBrowser("chrome", "https://www.ebay.com");
        logger.info("browser opened and ebay.com launched");

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.ebay.com";

        Assert.assertEquals(actualUrl, expectedUrl, "url didn't match");
        logger.info("ebay homepage url validated");

        WebElement ebayLogo = driver.findElement(By.id("gh-l-h1"));
        // boolean result =ebayLogo.isDisplayed();
        // Assert.assertEquals(result,true,"result is not true"); -->
        Assert.assertTrue(ebayLogo.isDisplayed(), "result is not true");
        logger.info("ebay logo has been displayed");

        waitFor(5);
        typeOnXpath("//input[@id='gh-ac']", "java books");
        logger.info("typed java books in the search bar");

        clickOnId("gh-btn");
        logger.info("search button has been clicked");

        WebElement result = driver.findElement(By.xpath("//h1[@class='srp-controls__count-heading']"));

        String log = result.getText();
        Assert.assertTrue(log.contains("java books"), "log didn't contain java books");
        logger.info(log + ":has been displayed");


        closeBrowser();
    }


}

//input[@class='gLFyF gsfi']
// //  --> always strats with double slash
// input -->tag name
// [@key='value']
//inside the test






