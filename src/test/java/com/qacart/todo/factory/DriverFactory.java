package com.qacart.todo.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverFactory {

    public WebDriver initializeDriver(){
//        mvn clean test -Dbrowser=FIREFOX
        String browser = System.getProperty("browser","CHROME");
        WebDriver webDriver;
        switch (browser){
            case "CHROME"->{
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                 webDriver = new ChromeDriver(chromeOptions);
            }
            case "FIREFOX" ->{
                 webDriver = new FirefoxDriver();
            }
            case "SAFARI" ->{
                 webDriver = new SafariDriver();
            }
            default -> {
                throw new RuntimeException("Browser not supported");
            }
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return webDriver;
    }
}
