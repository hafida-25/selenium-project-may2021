package com.peoplentech.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverLunch1 {

    public static void main(String[] args) throws InterruptedException {

        //setup the env property
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

        // created obj of chromedriver
        WebDriver driver  = new ChromeDriver ();

        //interface object = new constructor of the chromedriver class

        //open the browser
        driver.get("https://www.google.com");

        driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("Selenium Books");

        //wait
        Thread.sleep(5000);
        // click on search button
        driver.findElement(By.xpath("//input[@aria-label='Google Search']")).click();

        // wait
        Thread.sleep(5000);

        // quit the browser
        driver.quit();

        //quit the browser
       driver.quit();
    }
}
   // public void closeBrowser


