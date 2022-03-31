package com.epam.autotesting.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class WebDriverConnector{

    private static WebDriver driver;

    public static synchronized WebDriver getDriver(){
        if (null == driver){
            switch (System.getProperty("browser")){
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                case "msedge": {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                }
                default: {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    WebDriverManager.chromedriver().driverVersion("99.0.4844.51").setup();
                  driver = new ChromeDriver(chromeOptions);
                }
            }
           driver.manage().window().maximize();
        }
        return driver;
    }
    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}