package com.epam.autotesting.test;

import com.epam.autotesting.driver.WebDriverConnector;
import com.epam.autotesting.util.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class RequiredConditions {
    public static WebDriver driver;

    @BeforeMethod()
    public void setUp() {       
        driver = WebDriverConnector.getDriver();
    }

//    @AfterMethod(alwaysRun = true)
//    public void stopBrowser() {
//        WebDriverConnector.closeDriver();
//    }
}
