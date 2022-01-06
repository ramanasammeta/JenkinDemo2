package com.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    WebDriver driver;


     @BeforeMethod
    public void setup()  {
         if (System.getProperty("browser").equalsIgnoreCase("Chrome")){
             WebDriverManager.chromedriver().setup();
             driver=new ChromeDriver();
         } else if (System.getProperty("browser").equalsIgnoreCase("Firefox")){
             WebDriverManager.firefoxdriver().setup();
             driver=new FirefoxDriver();
         } else if (System.getProperty("browser").equalsIgnoreCase("IE")){
             WebDriverManager.edgedriver().operatingSystem(OperatingSystem.WIN).setup();
             driver=new EdgeDriver();
         }
         driver.manage().window().maximize();
        driver.get(System.getProperty("url"));
         try {
             Thread.sleep(2000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         driver.findElement(By.xpath("//*[contains(text(), 'I agree')]")).click();
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
